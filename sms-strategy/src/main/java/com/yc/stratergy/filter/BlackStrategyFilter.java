package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 黑名单校验
 */
@Service("black")
@Slf4j
public class BlackStrategyFilter implements StrategyFilter{
    @Override
    public void check(StandardSubmit submit) {
        log.info("黑名单校验");

    }
}
