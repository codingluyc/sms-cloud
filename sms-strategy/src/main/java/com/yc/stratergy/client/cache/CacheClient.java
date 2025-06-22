package com.yc.stratergy.client.cache;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

@FeignClient(name = "sms-cache", path = "/cache" ,value = "sms-cache")
public interface CacheClient {

    /**
     * 读hash
     * @param key
     * @return
     */
    @GetMapping("/hgetAll/{key}")
    Map hgetAll(@PathVariable String key);

    @GetMapping("/hget/{key}/{field}")
    Object hget(@PathVariable String key, @PathVariable String field);

    @GetMapping("/hget/{key}/{field}")
    String hgetStr(@PathVariable String key, @PathVariable String field);

    @GetMapping("sget/{key}")
    Set<Map> sget(@PathVariable(value = "key") String key);

    @GetMapping("get/{key}")
    String get(@PathVariable(value = "key") String key);


    @PostMapping("sinter/{key1}/{key2}")
    Set<Object> sinter(@PathVariable(value = "key1") String key1, @PathVariable(value = "key2") String key2,@RequestBody Object... set);
}
