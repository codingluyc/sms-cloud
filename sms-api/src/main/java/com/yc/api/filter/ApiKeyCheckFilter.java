package com.yc.api.filter;

import com.yc.api.client.cache.CacheClient;
import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.ApiException;
import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * api key 校验过滤器
 */
@Service(value = "apikey")
@Slf4j
public class ApiKeyCheckFilter implements CheckFilter{

    @Autowired
    private CacheClient cacheClient;
    @Override
    public void check(StandardSubmit obj) throws ApiException {

        Map map = cacheClient.hgetAll(RedisKeys.client_business + obj.getApiKey());
        if (map == null || map.isEmpty()) {
            log.error("api key error {}", obj.getApiKey());
            throw new ApiException(ExceptionEnums.ILLEGAL_APIKEY);
        }

        //封装正常数据
        obj.setClientId(Long.parseLong( map.get("id")+""));
        log.info("查询到用户信息: {}",map);
    }

}
