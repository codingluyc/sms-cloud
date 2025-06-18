package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientBusiness {
    /**  */
    // @Id
    //@GeneratedValue
    // @ApiModelProperty(name = "",notes = "")
    private Long  id ;
    /** 公司名 */
    // @ApiModelProperty(name = "公司名",notes = "")
    private String corpname ;
    /** HTTP接入的密码 */
    // @ApiModelProperty(name = "HTTP接入的密码",notes = "")
    private String apikey ;
    /** HTTP客户端的IP白名单（多个用,隔开） */
    // @ApiModelProperty(name = "HTTP客户端的IP白名单（多个用,隔开）",notes = "")
    private String ipAddress ;
    /** 状态报告是否返回：0 不返回 1 返回 */
    // @ApiModelProperty(name = "状态报告是否返回：0 不返回 1 返回",notes = "")
    private Integer isCallback ;
    /** 客户接收状态报告的URL地址 */
    // @ApiModelProperty(name = "客户接收状态报告的URL地址",notes = "")
    private String callbackUrl ;
    /** 联系人 */
    // @ApiModelProperty(name = "联系人",notes = "")
    private String clientLinkname ;
    /** 密保手机 */
    // @ApiModelProperty(name = "密保手机",notes = "")
    private String clientPhone ;
    /** 策略校验顺序动态实现规则 */
    // @ApiModelProperty(name = "策略校验顺序动态实现规则",notes = "")
    private String clientFilters ;
    /** 创建时间，默认系统时间 */
    // @ApiModelProperty(name = "创建时间，默认系统时间",notes = "")
    private LocalDateTime created ;
    /** 创建人id */
    // @ApiModelProperty(name = "创建人id",notes = "")
    private Long createId ;
    /** 修改时间，默认系统时间 */
    // @ApiModelProperty(name = "修改时间，默认系统时间",notes = "")
    private LocalDateTime updated ;
    /** 修改人id */
    // @ApiModelProperty(name = "修改人id",notes = "")
    private Long updateId ;
    /** 是否删除 0-未删除 ， 1-已删除 */
    // @ApiModelProperty(name = "是否删除 0-未删除 ， 1-已删除",notes = "")
    private Integer isDelete ;
    /** 备用字段1 */
    // @ApiModelProperty(name = "备用字段1",notes = "")
    private String extend1 ;
    /** 备用字段2 */
    // @ApiModelProperty(name = "备用字段2",notes = "")
    private String extend2 ;
    /** 备用字段3 */
    // @ApiModelProperty(name = "备用字段3",notes = "")
    private String extend3 ;
    /** 备用字段4 */
    // @ApiModelProperty(name = "备用字段4",notes = "")
    private String extend4 ;


}
