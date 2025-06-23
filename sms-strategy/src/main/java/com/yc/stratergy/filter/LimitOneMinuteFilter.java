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
 * 一分钟内禁止重复发送短信
 */
@Service("limitOneMinute")
@Slf4j
public class LimitOneMinuteFilter implements StrategyFilter{

    private static final String SEP = ":";

    private static final Long ONE_MINUTE = 60*1000L;

    @Autowired
    CacheClient cacheClient;

    @Override
    public void check(StandardSubmit submit) throws  StrategyException {
        LocalDateTime sendTime = submit.getSendTime();
        //东八区
        Long score = sendTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();

        boolean result = cacheClient.zadd(RedisKeys.LIMIT+submit.getClientId()+SEP+submit.getMobile(),score,score);
        if (!result) {
            //并发插入失败，则说明一分钟内已经发送过
            throw new StrategyException(ExceptionEnums.LIMIT_MINUTES);
        }
        long start = score - ONE_MINUTE;
        String key = RedisKeys.LIMIT+submit.getClientId()+SEP+submit.getMobile();
        int count = cacheClient.zrangeCount(key,Double.parseDouble(start+""),Double.parseDouble(score+""));

        //队列中数量超过1（大于等于2）
        if(count > 1){
            //删除发送失败的数据
            cacheClient.zdel(key,score);
            throw new StrategyException(ExceptionEnums.LIMIT_MINUTES);
        }else{
            log.info("通过一分钟限流规则");
        }


    }
}
