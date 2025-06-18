package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.ClientSign;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientSignMapper {

    /**
     * 通过Id 查询ClientSign
     */
    @Select(value = "select * from client_sign where id = #{id}")
    ClientSign selectById(@Param("id") Long id);

    /**
     * 根据clientId查询
     */
    @Select(value = "select * from client_sign where client_id = #{clientId}")
    List<ClientSign> selectByClientId(@Param("clientId") Long clientId);
}
