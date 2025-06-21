package com.yc.cache.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    @Autowired
    private RedisClient redisClient;


    @GetMapping("/hget/{key}/{field}")
    public Object hget(@PathVariable String key, @PathVariable String field) {
        return redisClient.hGet(key, field);
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

    @PostMapping("/hmset/{key}")
    public void hmset(@PathVariable(value = "key") String key, @RequestBody Map<String,Object> map){
        redisClient.hSet(key, map);
    }

    @PostMapping("/set/{key}")
    public void set(@PathVariable(value = "key") String key, @RequestParam(value = "value") Object value){
        redisClient.set(key, value);
    }

    @PostMapping("sadd/{key}")
    public void sadd(@PathVariable(value = "key") String key, @RequestBody Map<String,Object>... maps){
        redisClient.sAdd(key, maps);
    }

    @PostMapping("/pipelineStr")
    public void pipelineStr( @RequestBody Map<String,Object> map){
        log.info("pipelineStr size: {}",map.size());
        redisClient.pipelined(new Consumer<RedisOperations<String, Object>>() {
            @Override
            public void accept(RedisOperations<String, Object> stringObjectRedisOperations) {
                for(Map.Entry<String,Object> entry:map.entrySet()){
                    stringObjectRedisOperations.opsForValue().set(entry.getKey(),entry.getValue());
                }
            }
        });
    }

    @GetMapping("sget/{key}")
    public Set<Object> sget(@PathVariable(value = "key") String key){
        return redisClient.sMembers(key);
    }
}
