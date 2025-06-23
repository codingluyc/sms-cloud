package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.ClientChannel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientChannelMapper {

    @Select("select * from client_channel")
    List<ClientChannel> queryAll();

    @Select("select * from client_channel where client_id = #{clientId} and is_delete = 0")
    List<ClientChannel> queryByClientId(Long clientId);
}
