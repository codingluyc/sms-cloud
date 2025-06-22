package com.yc.cacheLoader.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MobileDirtywordMapper {

    @Select("select dirtyword from mobile_dirtyword")
    List<String> queryAll();
}
