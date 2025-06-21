package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;
import org.springframework.stereotype.Service;

/**
 * 黑名單校驗
 */
@Service("black")
public class BlackStrategyFilter implements StrategyFilter{
    @Override
    public void check(StandardSubmit submit) {

    }
}
