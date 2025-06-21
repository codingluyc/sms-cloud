package com.yc.cacheLoader.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.cacheLoader.client.cache.CacheClient;
import com.yc.cacheLoader.common.ObjectMapperTests;
import com.yc.cacheLoader.entity.MobileArea;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MobileAreaMapperTest {

    @Autowired
    private MobileAreaMapper mobileAreaMapper;

    @Autowired
    CacheClient cacheClient;

    ObjectMapper objectMapper = ObjectMapperTests.getObjectMapper();

    @Test
    public void selectAll() throws InterruptedException {
        List<MobileArea> mobileAreas = mobileAreaMapper.selectAll();

        //由于数据很多，所以采取多线程的方式执行
//        Map<String,Object> map = new HashMap<>();
//        for (MobileArea mobileArea : mobileAreas) {
//            map.put("phase:"+mobileArea.getMobileNumber(),mobileArea.getMobileArea()+","+mobileArea.getMobileType());
//        }
//        cacheClient.pipelineStr(map);


        log.info("mobileAreas size: {}",mobileAreas.size());
        //将集合进行分页，一页一万条，然后使用多线程执行
        int pageSize = 10000;
        int pageCount = mobileAreas.size()/pageSize;
        if(mobileAreas.size()%pageSize != 0){
            pageCount++;
        }
        //需要所有的线程都执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(pageCount);
        //控制并发线程量
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < pageCount; i++) {
            int start = i*pageSize;
            int end = (i+1)*pageSize;
            if(end > mobileAreas.size()){
                end = mobileAreas.size();
            }
            List<MobileArea> mobileAreas1 = mobileAreas.subList(start, end);
           new PipelineThread(cacheClient,mobileAreas1, countDownLatch, semaphore).start();
        }

        countDownLatch.await();
        log.info("finished!!!");


    }

    static class PipelineThread extends Thread{
        private CacheClient cacheClient;
        private List<MobileArea> mobileAreas;
        private CountDownLatch countDownLatch;

        private Semaphore  semaphore;
        public PipelineThread(CacheClient cacheClient, List<MobileArea> mobileAreas,CountDownLatch countDownLatch,Semaphore semaphore) {
            this.cacheClient = cacheClient;
            this.mobileAreas = mobileAreas;
            this.countDownLatch = countDownLatch;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            log.info(" thread :{} start. pipelineThread size: {}",this.getName(),mobileAreas.size());
            try {
                semaphore.acquire();
                Map<String,Object> map = new HashMap<>();
                for (MobileArea mobileArea : mobileAreas) {
                    map.put("phase:"+mobileArea.getMobileNumber(),mobileArea.getMobileArea()+","+mobileArea.getMobileType());
                }
                cacheClient.pipelineStr(map);
                log.info("thread:{} end,size:{}",this.getName(),mobileAreas.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                this.countDownLatch.countDown();
                this.semaphore.release();
            }

        }
    }
}