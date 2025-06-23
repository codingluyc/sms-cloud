package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientChannel {
    /** 主键 */
    // 
    //@Id
    //@GeneratedValue
    //
    //@ApiModelProperty(name = "主键",notes = "")
    private Long id ;
    /** 客户id，对应client_business表 */
    // 
    //@ApiModelProperty(name = "客户id，对应client_business表",notes = "")
    private Long clientId ;
    /** 通道id，对应channel表 */
    // 
    //@ApiModelProperty(name = "通道id，对应channel表",notes = "")
    private Long channelId ;
    /** 通道权重 */
    // 
    //@ApiModelProperty(name = "通道权重",notes = "")
    private Integer clientChannelWeight ;
    /** 客户通道短信价格（厘/条） */
    // 
    //@ApiModelProperty(name = "客户通道短信价格（厘/条）",notes = "")
    private Long clientChannelPrice ;
    /** 下发扩展号 如：通道接入号为1069886，后面可以扩展数字，最长不超过20位 */
    // 
    //@ApiModelProperty(name = "下发扩展号 如：通道接入号为1069886，后面可以扩展数字，最长不超过20位",notes = "")
    private String clientChannelNumber ;
    /** 是否启动 0-启用中 1-已停用 */
    // 
    //@ApiModelProperty(name = "是否启动 0-启用中 1-已停用",notes = "")
    private Integer isAvailable ;
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
