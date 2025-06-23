package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.common.ObjectMapperTests;
import com.yc.cacheLoader.entity.Channel;
import com.yc.cacheLoader.entity.ClientChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ChannelMapperTest {
    @Resource
    ChannelMapper mapper;

    @Autowired
    CacheClient cacheClient;


    ObjectMapper objectMapper = ObjectMapperTests.getObjectMapper();
    @Test
    public void queryAll() throws JsonProcessingException {
        List<Channel> list = mapper.queryAll();
        for(Channel channel : list){
            Map map = ObjectMapperTests.toMap(channel,objectMapper);
            cacheClient.hmset("channel:"+channel.getId(),map);
        }
    }
}