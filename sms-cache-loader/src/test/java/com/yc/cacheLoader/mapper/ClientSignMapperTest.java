package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.entity.ClientSign;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static com.yc.cacheLoader.common.ObjectMapperTests.getObjectMapper;
import static com.yc.cacheLoader.common.ObjectMapperTests.toMap;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ClientSignMapperTest {
    @Resource
    ClientSignMapper clientSignMapper;

    @Autowired
    CacheClient cacheClient;


    ObjectMapper objectMapper = getObjectMapper();

    @Test
    public void selectByClientId() throws JsonProcessingException {
        List<ClientSign> signs = clientSignMapper.selectByClientId(1L);
        Map[] maps = new Map[signs.size()];
        for(ClientSign sign : signs){
            maps[signs.indexOf(sign)] = toMap(sign, objectMapper);
        }
        cacheClient.sadd("client_sign:1", maps);
    }
}