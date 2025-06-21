package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.entity.ClientBusiness;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ClientBusinessMapperTest {
    @Resource
    ClientBusinessMapper clientBusinessMapper;

    @Autowired
    CacheClient cacheClient;


    ObjectMapper objectMapper = getObjectMapper();

    @Test
    public void selectById() throws JsonProcessingException {
        ClientBusiness clientBusiness = clientBusinessMapper.selectById(1L);

        if(clientBusiness != null) {
            log.info("clientBusiness:{}", clientBusiness);
            Map<String, Object> map = toMap(clientBusiness, objectMapper);
            map.put("clientFilters","black,dirtyword,phase,route");
            map.put("ipAddress","127.0.0.1");
            cacheClient.hmset("client_business:" + clientBusiness.getApikey(), map);
        }else {
            log.error("clientBusiness为空");
        }
    }


    /**
     * 获取ObjectMapper
     * @return
     */
    ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // jdk8日期格式支持
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Module timeModule = new JavaTimeModule()
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(timeFormatter))
                .addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter))
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(timeFormatter));
        objectMapper.registerModule(timeModule);
        return objectMapper;
    }

    /**
     * 通过object mapper将对象转换为map
     */
    Map<String, Object> toMap(Object object, ObjectMapper objectMapper) throws JsonProcessingException {
        Map<String, Object> map = objectMapper.readValue(objectMapper.writeValueAsString(object),Map.class);
        return map;
    }
}