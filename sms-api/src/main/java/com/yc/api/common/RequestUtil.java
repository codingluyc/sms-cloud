package com.yc.api.common;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    //获取请求的真实ip
    public static String getRealIp(HttpServletRequest request) {
        String ips = request.getHeader("x-forwarded-for");
        if(!StringUtils.isEmpty(ips) && !"unknown".equalsIgnoreCase(ips)){
            int index = ips.indexOf(",");
            if(index > 0){
                return ips.substring(0, index);
            }
        }


        String ip = request.getHeader("x-real-ip");
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            //如果是apache 代理，则获取proxy-client-ip
            ip = request.getHeader("proxy-client-ip");
        }
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            //如果是WebLogic代理，则获取wl-proxy-client-ip
            ip = request.getHeader("wl-proxy-client-ip");
        }
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            //其他代理服务器
            ip = request.getHeader("http_client_ip");
        }
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            //都拿不到
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
