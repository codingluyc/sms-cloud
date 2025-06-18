package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.ClientBusiness;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClientBusinessMapper {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select(value = "select * from client_business where id = #{id}")
    ClientBusiness selectById(@Param("id") Long id);

}
