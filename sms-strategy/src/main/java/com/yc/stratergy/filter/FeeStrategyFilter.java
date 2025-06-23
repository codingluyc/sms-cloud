package com.yc.stratergy.filter;

import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.StrategyException;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.client.cache.CacheClient;
import com.yc.stratergy.util.ClientBalanceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 扣费策略
 */
@Service("fee")
@Slf4j
public class FeeStrategyFilter implements StrategyFilter{

    @Autowired
    CacheClient cacheClient;

    @Override
    public void check(StandardSubmit submit) throws IOException, StrategyException {

        //获取客户余额
        Long amount = ((Integer)cacheClient.hget(RedisKeys.CLIENT_BALANCE + submit.getClientId(), RedisKeys.FEE_FIELD)).longValue();
        Long balance = cacheClient.hincrBy(RedisKeys.CLIENT_BALANCE + submit.getClientId(), RedisKeys.FEE_FIELD, -submit.getFee());

        Long amountLimit = ClientBalanceUtil.getClientAmountLimit(submit.getClientId());

        if(balance < amountLimit){
            //把扣除的费用加回去
            cacheClient.hincrBy(RedisKeys.CLIENT_BALANCE + submit.getClientId(), RedisKeys.FEE_FIELD, submit.getFee());
            throw new StrategyException(ExceptionEnums.CLIENT_BALANCE_NOT_ENOUGH);
        }else{
            log.info("扣费成功 原余额：{}，扣费：{} 余额：{}", amount, submit.getFee(), balance);
        }
    }
}
