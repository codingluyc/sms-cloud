package com.yc.stratergy.filter;

import com.yc.common.exceptions.StrategyException;
import com.yc.common.model.StandardSubmit;

import java.io.IOException;

/*
 * @description: 策略过滤器
 */
public interface StrategyFilter {

    /**
     * 校验
     * @param submit
     */
    void check(StandardSubmit submit) throws IOException, StrategyException;
}
