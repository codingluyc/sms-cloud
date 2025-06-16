package com.yc.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "mobile")
@Slf4j
public class MobileCheckFilter implements CheckFilter{

    @Override
    public void check(Object obj) {
        log.info("mobile check");
    }
}
