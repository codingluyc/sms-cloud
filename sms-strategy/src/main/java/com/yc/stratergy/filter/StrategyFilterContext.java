package com.yc.stratergy.filter;


import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class StrategyFilterContext {

    private Map<String,StrategyFilter> stringStrategyFilterMap;

    public void strategy(StandardSubmit submit){

    }

}
