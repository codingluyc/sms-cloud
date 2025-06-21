package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;
import org.springframework.stereotype.Service;

/**
 * 路由校驗
 */
@Service("route")
public class RouteStrategyFilter implements StrategyFilter{
    @Override
    public void check(StandardSubmit submit) {

    }
}
