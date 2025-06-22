package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.MobileBlack;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MobileBlackMapper {

    @Select("select * from mobile_black")
    List<MobileBlack> queryAll();
}
