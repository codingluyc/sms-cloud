package com.yc.cacheLoader.mapper;

import com.yc.cacheLoader.entity.ClientTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientTemplateMapper {
    /**
     * 通过signId查询
     */
    @Select("select * from client_template where sign_id = #{signId}")
    List<ClientTemplate> queryBySignId(@Param("signId") Long signId);
}
