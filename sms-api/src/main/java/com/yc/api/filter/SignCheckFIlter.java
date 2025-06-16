package com.yc.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "sign")
@Slf4j
public class SignCheckFIlter implements CheckFilter{

    @Override
    public void check(Object obj) {
        log.info("sign check");
    }
}
