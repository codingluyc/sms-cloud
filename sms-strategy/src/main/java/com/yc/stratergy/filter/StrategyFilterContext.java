package com.yc.stratergy.filter;


import com.yc.common.constants.RedisKeys;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.client.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class StrategyFilterContext {


    @Autowired
    private CacheClient cacheClient;

    @Autowired
    private Map<String,StrategyFilter> stringStrategyFilterMap;

    public void strategy(StandardSubmit submit){

        //获取过滤器
        String filters = cacheClient.hgetStr(RedisKeys.client_business + submit.getApiKey(),RedisKeys.client_filters );

        String[] filterArray ;

        if(StringUtils.isNotEmpty( filters) && (filterArray = filters.split(",")).length > 0){
            for(String strategy : filterArray){
                StrategyFilter strategyFilter = stringStrategyFilterMap.get(strategy);
                if (strategyFilter != null){
                    strategyFilter.check(submit);
                }else{
                    log.error("没有找到对应的过滤器: {}",strategy);
                }
            }
        }

    }

}
