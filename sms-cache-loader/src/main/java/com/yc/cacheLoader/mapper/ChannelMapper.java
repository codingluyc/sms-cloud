package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.Channel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChannelMapper {

    @Select("select * from channel")
    List<Channel> queryAll();


}
