package com.yc.api.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RefreshScope
public class RequestUtil {
    
    @Value(value = "${headers:x-forwarded-for}")
    private String headers;
    
    private static final String UNKNOWN = "unknown";
    
    private static final String X_FORWARDED_FOR = "x-forwarded-for";
    
    //获取请求的真实ip
    public  String getRealIp(HttpServletRequest request) {
        String ip = null;
        
        for(String header: headers.split(",")){
            if(X_FORWARDED_FOR.equalsIgnoreCase( header)){
                String ips = request.getHeader("x-forwarded-for");
                if(!StringUtils.isEmpty(ips) && !UNKNOWN.equalsIgnoreCase(ips)){
                    int index = ips.indexOf(",");
                    if(index > 0){
                        return ips.substring(0, index);
                    }
                }
            }else{
                ip = request.getHeader(header);
                if(!StringUtils.isEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)){
                    return ip;
                }
            }

        }

        if(StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)){
            //都拿不到
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
