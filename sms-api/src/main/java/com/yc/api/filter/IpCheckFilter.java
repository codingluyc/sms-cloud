package com.yc.api.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "ip")
@Slf4j
public class IpCheckFilter implements CheckFilter{

    @Override
    public void check(Object obj) {
        log.info("ip check");
    }
}
