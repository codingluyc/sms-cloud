package com.yc.cache.controller;

import com.msb.framework.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private RedisClient redisClient;


    /**
     * 读hash
     * @param key
     * @return
     */
    @GetMapping("/hgetAll/{key}")
    public Map hgetAll(@PathVariable String key) {
        return redisClient.hGetAll(key);
    }

    @PostMapping("/hmset/{key}")
    public void hmset(@PathVariable(value = "key") String key, @RequestBody Map<String,Object> map){
        redisClient.hSet(key, map);
    }

    @PostMapping("/set/{key}")
    public void set(@PathVariable(value = "key") String key, @RequestParam(value = "value") Object value){
        redisClient.set(key, value);
    }

    @PostMapping("sadd/{key}")
    void sadd(@PathVariable(value = "key") String key, @RequestBody Map<String,Object>... maps){
        redisClient.sAdd(key, maps);
    }
}
