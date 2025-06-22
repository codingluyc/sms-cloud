package com.yc.stratergy.filter;

import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.StrategyException;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.client.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户级别黑名单校验
 */
@Service("blackClient")
@Slf4j
public class BlackClientStrategyFilter implements StrategyFilter{

    @Autowired
    CacheClient cacheClient;
    @Override
    public void check(StandardSubmit submit) throws StrategyException {
        String black = cacheClient.get(RedisKeys.MOBILE_BLACK+submit.getClientId()+":"+submit.getMobile());
        if("1".equals(black)){
            throw new StrategyException(ExceptionEnums.MOBILE_BLACK_GLOBAL);
        }
    }

}
