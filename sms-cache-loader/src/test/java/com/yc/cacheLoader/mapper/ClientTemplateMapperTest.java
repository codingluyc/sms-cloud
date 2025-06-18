package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.common.ObjectMapperTests;
import com.yc.cacheLoader.entity.ClientBalance;
import com.yc.cacheLoader.entity.ClientTemplate;
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
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ClientTemplateMapperTest {
    @Resource
    ClientTemplateMapper clientTemplateMapper;

    @Autowired
    CacheClient cacheClient;


    ObjectMapper objectMapper = ObjectMapperTests.getObjectMapper();

    @Test
    public void queryBySignId() {
        Map[] maps = null;
        List<ClientTemplate> clientTemplates = clientTemplateMapper.queryBySignId(15L);
        if(clientTemplates.size() > 0) {
            maps = new Map[clientTemplates.size()];
            for (ClientTemplate template : clientTemplates) {
                try {
                    maps[clientTemplates.indexOf(template)] = toMap(template, objectMapper);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            cacheClient.sadd("client_template:15", maps);
        }

        clientTemplates = clientTemplateMapper.queryBySignId(24L);
        if(clientTemplates.size() > 0) {
            maps = new Map[clientTemplates.size()];
            for (ClientTemplate template : clientTemplates) {
                try {
                    maps[clientTemplates.indexOf(template)] = toMap(template, objectMapper);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            cacheClient.sadd("client_template:24", maps);
        }
    }
}