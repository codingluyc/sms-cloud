package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Channel {
    /** 主键 */
    // 
    //@Id
    //@GeneratedValue
    //
    //@ApiModelProperty(name = "主键",notes = "")
    private Long id ;
    /** 通道名称 如：北京移动、上海联通、深圳电信 */
    // 
    //@ApiModelProperty(name = "通道名称 如：北京移动、上海联通、深圳电信",notes = "")
    private String channelName ;
    /** 通道类型：0-三网 1-移动 2-联通 3-电信 */
    // 
    //@ApiModelProperty(name = "通道类型：0-三网 1-移动 2-联通 3-电信",notes = "")
    private Integer channelType ;
    /** 通道地区 如：北京 北京、湖北 荆门 */
    // 
    //@ApiModelProperty(name = "通道地区 如：北京 北京、湖北 荆门",notes = "")
    private String channelArea ;
    /** 地区号段 */
    // 
    //@ApiModelProperty(name = "地区号段",notes = "")
    private String channelAreaCode ;
    /** 通道短信成本价格（厘/条） */
    // 
    //@ApiModelProperty(name = "通道短信成本价格（厘/条）",notes = "")
    private Long channelPrice ;
    /** 通道协议类型 1-cmpp、2-sgip、3-smgp */
    // 
    //@ApiModelProperty(name = "通道协议类型 1-cmpp、2-sgip、3-smgp",notes = "")
    private Integer channelProtocal ;
    /** 通道IP地址 */
    // 
    //@ApiModelProperty(name = "通道IP地址",notes = "")
    private String channelIp ;
    /** 通道端口号 */
    // 
    //@ApiModelProperty(name = "通道端口号",notes = "")
    private Integer channelPort ;
    /** 通道账号 */
    // 
    //@ApiModelProperty(name = "通道账号",notes = "")
    private String channelUsername ;
    /** 通道密码 */
    // 
    //@ApiModelProperty(name = "通道密码",notes = "")
    private String channelPassword ;
    /** 账户接入号，如1069777、10684376 */
    // 
    //@ApiModelProperty(name = "账户接入号，如1069777、10684376",notes = "")
    private String channel_number ;
    /** 是否启动 0-已停用 1-启用中 */
    // 
    //@ApiModelProperty(name = "是否启动 0-已停用 1-启用中",notes = "")
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
