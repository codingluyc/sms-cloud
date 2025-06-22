package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.common.ObjectMapperTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MobileDirtywordMapperTest {
    @Autowired
    private MobileDirtywordMapper mapper;

    @Autowired
    CacheClient cacheClient;

    ObjectMapper objectMapper = ObjectMapperTests.getObjectMapper();

    @Test
    public void queryAll() {
        List<String> mobileDirtywors = mapper.queryAll();
        String[] mobileDirtyworsArray = mobileDirtywors.toArray(new String[mobileDirtywors.size()]);
        cacheClient.saddstr("mobile_dirtyword", mobileDirtyworsArray);
    }
}