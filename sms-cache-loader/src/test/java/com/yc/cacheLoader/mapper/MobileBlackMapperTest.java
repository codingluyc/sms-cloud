package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.entity.MobileBlack;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static com.yc.cacheLoader.common.ObjectMapperTests.getObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MobileBlackMapperTest {

    @Resource
    MobileBlackMapper mapper;

    @Autowired
    CacheClient cacheClient;


    ObjectMapper objectMapper = getObjectMapper();

    @Test
    public void queryAll() {

        List<MobileBlack> list = mapper.queryAll();
        for(MobileBlack mobileBlack : list){
            if(mobileBlack.getClientId() == 0){
                //平台级别
                cacheClient.set("black:"+mobileBlack.getBlackNumber(),"1");
            }else{
                //客户级别
                cacheClient.set("black:"+mobileBlack.getClientId()+":"+mobileBlack.getBlackNumber(),"1");

            }
        }
    }
}