package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.MobileArea;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MobileAreaMapper {

    @Select("select * from mobile_area")
    List<MobileArea> selectAll();
}
