package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MobileArea {
    /** 主键 */
    //
    //@Id
    //@GeneratedValue
    //
    //@ApiModelProperty(name = "主键",notes = "")
    private Long id ;
    /** 手机号前7位 */
    //
    //@ApiModelProperty(name = "手机号前7位",notes = "")
    private String mobileNumber ;
    /** 手机号区域 */
    //
    //@ApiModelProperty(name = "手机号区域",notes = "")
    private String mobileArea ;
    /** 手机号运营商 */
    //
    //@ApiModelProperty(name = "手机号运营商",notes = "")
    private String mobileType ;
    /** 地区号段 */
    //
    //@ApiModelProperty(name = "地区号段",notes = "")
    private String areaCode ;
    /** 地区邮箱 */
    //
    //@ApiModelProperty(name = "地区邮箱",notes = "")
    private String postCode ;
    /** 省号段 */
    //
    //@ApiModelProperty(name = "省号段",notes = "")
    private String provinceCode ;
    /** 创建时间，默认系统时间 */
    //
    //@ApiModelProperty(name = "创建时间，默认系统时间",notes = "")
    private LocalDateTime created ;
    /** 创建人id */
    //
    //@ApiModelProperty(name = "创建人id",notes = "")
    private Long createId ;
    /** 修改时间，默认系统时间 */
    //
    //@ApiModelProperty(name = "修改时间，默认系统时间",notes = "")
    private LocalDateTime updated ;
    /** 修改人id */
    //
    //@ApiModelProperty(name = "修改人id",notes = "")
    private Long updateId ;
    /** 是否删除 0-未删除 ， 1-已删除 */
    //
    //@ApiModelProperty(name = "是否删除 0-未删除 ， 1-已删除",notes = "")
    private Integer isDelete ;
    /** 备用字段1 */
    //
    //@ApiModelProperty(name = "备用字段1",notes = "")
    private String extend1 ;
    /** 备用字段2 */
    //
    //@ApiModelProperty(name = "备用字段2",notes = "")
    private String extend2 ;
    /** 备用字段3 */
    //
    //@ApiModelProperty(name = "备用字段3",notes = "")
    private String extend3 ;
    /** 备用字段4 */
    //
    //@ApiModelProperty(name = "备用字段4",notes = "")
    private String extend4 ;
}
