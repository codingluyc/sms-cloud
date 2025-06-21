package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 路由校驗
 */
@Service("route")
@Slf4j
public class RouteStrategyFilter implements StrategyFilter{
    @Override
    public void check(StandardSubmit submit) {

    }
}
