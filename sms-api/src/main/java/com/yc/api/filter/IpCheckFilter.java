package com.yc.api.filter;


import com.yc.api.client.cache.CacheClient;
import com.yc.common.contasts.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.ApiException;
import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "ip")
@Slf4j
public class IpCheckFilter implements CheckFilter{
    @Autowired
    private CacheClient cacheClient;
    @Override
    public void check(StandardSubmit obj) throws ApiException {

        String ipAddress = cacheClient.hgetStr(RedisKeys.client_business + obj.getApiKey(),"ipAddress");
        obj.setIp(ipAddress);
        if (StringUtils.isEmpty(ipAddress) || ipAddress.contains(obj.getRealIp())) {
            return;
        }else{
            log.error("ip不在白名单中");
            throw new ApiException(ExceptionEnums.IP_NOT_IN_WHITE_LIST);
        }


    }
}
