package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.common.ObjectMapperTests;
import com.yc.cacheLoader.entity.ClientBalance;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yc.cacheLoader.common.ObjectMapperTests.toMap;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ClientBalanceMapperTest {
    @Resource
    ClientBalanceMapper clientBalanceMapper;

    @Autowired
    CacheClient cacheClient;


    ObjectMapper objectMapper = ObjectMapperTests.getObjectMapper();


    @Test
    public void selectBySignId() throws JsonProcessingException {
        ClientBalance balance = clientBalanceMapper.selectByClientId(1L);
        Map<String, Object> map = toMap(balance, objectMapper);
        cacheClient.hmset("client_balance:1", map);

    }
}