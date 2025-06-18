package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.ClientBalance;
import org.apache.ibatis.annotations.Select;

public interface ClientBalanceMapper {

    /**
     * 通过clientId查询
     */
    @Select("select * from client_balance where client_id = #{clientId}")
    ClientBalance selectByClientId(Long clientId);
}
