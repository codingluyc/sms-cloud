package com.yc.stratergy.filter;

import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 敏感詞校驗
 */
@Service("dirtyword")
@Slf4j
public class DirtyWordStrategyFilter implements StrategyFilter{
    @Override
    public void check(StandardSubmit submit) {

    }
}
