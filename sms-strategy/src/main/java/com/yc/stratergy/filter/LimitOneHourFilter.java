package com.yc.stratergy.filter;

import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.StrategyException;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.client.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 小时重复规则
 * 一小时内不允许超过三条
 */
@Service("limitOneHour")
@Slf4j
public class LimitOneHourFilter implements StrategyFilter{
    private static final String SEP = ":";

    private static final Long ONE_HOUR = 60*60*1000L;

    private static final int reTryCount = 2;

    @Autowired
    CacheClient cacheClient;


    @Override
    public void check(StandardSubmit submit) throws IOException, StrategyException {
        if(submit.getState() != 0){
            return;
        }

        LocalDateTime sendTime = submit.getSendTime();
        //东八区
        Long score = sendTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        submit.setOneHourLimitMilli(score);

        boolean result = cacheClient.zadd(RedisKeys.LIMIT_HOUR+submit.getClientId()+SEP+submit.getMobile(),submit.getOneHourLimitMilli(),submit.getOneHourLimitMilli());
        if (!result) {
            //并发插入失败，重试两次
            for(int i=0;i<reTryCount;i++){
                //重置时间，避免缓存重复
                submit.setOneHourLimitMilli(System.currentTimeMillis());
                result = cacheClient.zadd(RedisKeys.LIMIT_HOUR+submit.getClientId()+SEP+submit.getMobile(),submit.getOneHourLimitMilli(),submit.getOneHourLimitMilli());
                if(result){
                    break;
                }
            }
        }

        if (!result) {
            //并发插入失败，则说明重试也失败了
            throw new StrategyException(ExceptionEnums.LIMIT_HOURS);
        }

        long start = submit.getOneHourLimitMilli() - ONE_HOUR;
        String key = RedisKeys.LIMIT_HOUR+submit.getClientId()+SEP+submit.getMobile();
        int count = cacheClient.zrangeCount(key,Double.parseDouble(start+""),Double.parseDouble(submit.getOneHourLimitMilli()+""));

        //队列中数量超过3（大于等于4）
        if(count > 3){
            //删除发送失败的数据
            cacheClient.zdel(key,submit.getOneHourLimitMilli());
            throw new StrategyException(ExceptionEnums.LIMIT_HOURS);
        }else{
            log.info("通过一小时限流规则");
        }



    }
}
