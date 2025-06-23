package com.yc.cache.controller;

import com.msb.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
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

    @Autowired
    RedisTemplate redisTemplate;


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
    @PostMapping("/hincrBy/{key}/{field}/{increment}")
    public Long hincrBy(@PathVariable(value = "key") String key, @PathVariable(value = "field") String field, @PathVariable(value = "increment") Long increment){
        return redisClient.hIncrementBy(key, field, increment);
    }


    @PostMapping("/set/{key}")
    public void set(@PathVariable(value = "key") String key, @RequestParam(value = "value") Object value){
        redisClient.set(key, value);
    }

    @PostMapping("sadd/{key}")
    public void sadd(@PathVariable(value = "key") String key, @RequestBody Map<String,Object>... maps){
        redisClient.sAdd(key, maps);
    }

    @PostMapping("saddstr/{key}")
    public void saddstr(@PathVariable(value = "key") String key, @RequestBody String... values){
        redisClient.sAdd(key, values);
    }

    @PostMapping("sinter/{key1}/{key2}")
    public Set<Object> sinter(@PathVariable(value = "key1") String key1, @PathVariable(value = "key2") String key2,@RequestBody Object... set){
        redisClient.sAdd(key2, set);
        Set<Object> result = redisClient.sIntersect(key1,key2);
        redisClient.delete(key2);
        return result;
    }

    @GetMapping("/smembers/{key}")
    public Set<Object> smembers(@PathVariable(value = "key") String key){
        return redisClient.sMembers(key);
    }

    @DeleteMapping("delete/{key}")
    public void delete(@PathVariable(value = "key") String key){
        redisClient.delete(key);
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

    @GetMapping("get/{key}")
    public Object get(@PathVariable(value = "key") String key){
        return redisClient.get(key);
    }

    @PostMapping("/zadd/{key}/{score}/{member}")
    public Boolean zadd(@PathVariable(value = "key") String key,@PathVariable(value = "score") Long score,@PathVariable(value = "member") Object member){
        return redisClient.zAdd(key,member,score);
    }

    @GetMapping("/zrangeCount/{key}/{start}/{end}")
    public Integer zrangeCount(@PathVariable(value = "key") String key,@PathVariable(value = "start") Double start,@PathVariable(value = "end") Double end){
        Set<ZSetOperations.TypedTuple<Object>> set = redisTemplate.opsForZSet().rangeByScoreWithScores(key,start,end);
        if(set == null){
            return 0;
        }
        return set.size();
    }

    @DeleteMapping("/zdel/{key}/{member}")
    public Long zdel(@PathVariable(value = "key") String key,@PathVariable(value = "member") Object member){
        return redisClient.zRemove(key,member);
    }


}
