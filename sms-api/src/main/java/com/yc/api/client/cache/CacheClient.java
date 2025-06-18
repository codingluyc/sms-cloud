package com.yc.api.client.cache;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "sms-cache", path = "/cache" ,value = "sms-cache")
public class CacheClient {
}
