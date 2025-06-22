package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MobileBlack {
    /** 主键 */
    // 
    //@Id
    //@GeneratedValue
    //
    //@ApiModelProperty(name = "主键",notes = "")
    private Long id ;
    /** 黑名单手机号 */
    // 
    //@ApiModelProperty(name = "黑名单手机号",notes = "")
    private String blackNumber ;
    /** 录入方式 0-手动导入 1-第三方API 2-用户退订 */
    // 
    //@ApiModelProperty(name = "录入方式 0-手动导入 1-第三方API 2-用户退订",notes = "")
    private Integer blackType ;
    /** 黑名单类型 0-全局黑名单  其他-客户黑名单 */
    // 
    //@ApiModelProperty(name = "黑名单类型 0-全局黑名单  其他-客户黑名单",notes = "")
    private Integer clientId ;
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
