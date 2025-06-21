package com.yc.cacheLoader.client.cache;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "sms-cache", path = "/cache" )
public interface CacheClient {

    @PostMapping("/hmset/{key}")
    void hmset(@PathVariable(value = "key") String key, @RequestBody Map<String,Object> map);

    @PostMapping("/set/{key}")
    void set(@PathVariable(value = "key") String key, @RequestParam(value = "value") Object value);

    @PostMapping("/sadd/{key}")
    void sadd(@PathVariable(value = "key") String key, @RequestBody Map<String,Object>... maps);

    @PostMapping("/pipelineStr")
    void pipelineStr( @RequestBody Map<String,Object> map);

}
