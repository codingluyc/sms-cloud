package com.yc.api.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "fee")
@Slf4j
public class FeeCheckFilter implements CheckFilter{

    @Override
    public void check(Object obj) {
        log.info("fee check");
    }
}
