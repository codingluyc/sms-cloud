package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 号段补全：获取手机号的运营商和归属地
 */
@Service("phase")
@Slf4j
public class PhaseStrategyFilter implements StrategyFilter{

    @Override
    public void check(StandardSubmit submit) {

    }
}
