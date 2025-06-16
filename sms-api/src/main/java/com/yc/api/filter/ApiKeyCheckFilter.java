package com.yc.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * api key 校验过滤器
 */
@Service(value = "apikey")
@Slf4j
public class ApiKeyCheckFilter implements CheckFilter{
    @Override
    public void check(Object obj) {
        log.info("api key check");
    }

}
