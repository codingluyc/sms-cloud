package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;
import org.springframework.stereotype.Service;

/**
 * 敏感詞校驗
 */
@Service("dirty")
public class DirtyStrategyFilter implements StrategyFilter{
    @Override
    public void check(StandardSubmit submit) {

    }
}
