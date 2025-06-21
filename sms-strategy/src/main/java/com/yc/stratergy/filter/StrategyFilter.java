package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;

/*
 * @description: 策略过滤器
 */
public interface StrategyFilter {

    /**
     * 校验
     * @param submit
     */
    void check(StandardSubmit submit);
}
