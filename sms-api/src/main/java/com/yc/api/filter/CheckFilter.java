package com.yc.api.filter;

import com.yc.common.exceptions.ApiException;
import com.yc.common.model.StandardSubmit;

/**
 * 策略模式父接口
 * @author luyc
 */
public interface CheckFilter {

    void check(StandardSubmit obj) throws ApiException;
}
