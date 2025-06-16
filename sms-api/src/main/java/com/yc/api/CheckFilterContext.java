package com.yc.api;

import com.yc.api.filter.CheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RefreshScope
public class CheckFilterContext {


    /**
     * spring ioc会将对象全部放到Map集合中
     * 基于Spring4.x提供的反射注解，将CheckFilter接口的实现类全部放到Map集合中
     */
    @Autowired
    private Map<String, CheckFilter> checkFilters;


    @Value(value = "${filters:apikey,ip}")
    private String filters;

    public void check(Object obj) {
        String[] filterArray = filters.split(",");
        for (String filter : filterArray) {
            CheckFilter checkFilter = checkFilters.get(filter);
//            log.info("当前使用的过滤器是：{}", checkFilter.getClass().getName());
            if (checkFilter == null) {
                log.error("没有找到对应的过滤器: {}",filter);
            }else {
                checkFilter.check(obj);
            }
        }

    }
}
