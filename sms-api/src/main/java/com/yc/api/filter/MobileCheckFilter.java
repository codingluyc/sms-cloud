package com.yc.api.filter;

import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "mobile")
@Slf4j
public class MobileCheckFilter implements CheckFilter{

    @Override
    public void check(StandardSubmit obj) {
        log.info("mobile check");
    }
}
