package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MobileDirtyword {
    /** 主键 */
    // 
    //@Id
    //@GeneratedValue
    //
    //@ApiModelProperty(name = "主键",notes = "")
    private Long id ;
    /** 敏感词 */
    // 
    //@ApiModelProperty(name = "敏感词",notes = "")
    private String dirtyword ;
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
