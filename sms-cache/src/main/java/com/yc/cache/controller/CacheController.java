package com.yc.cache.controller;

import com.msb.framework.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cache")
public class CacheController {

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisClient redisClient;


    /**
     * 写hash
     * @param key
     * @param map
     * @return
     */
    @PostMapping("/hset/{key}")
    public String set(@PathVariable String key, @RequestBody Map map) {
        redisClient.hSet(key, map);
        return "success";
    }

    /**
     * 读hash
     * @param key
     * @return
     */
    @GetMapping("/hgetAll/{key}")
    public Map hgetAll(@PathVariable String key) {
        return redisClient.hGetAll(key);
    }
}
