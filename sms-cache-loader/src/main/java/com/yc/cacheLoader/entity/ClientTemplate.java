package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientTemplate {
    /** 主键 */
    // @Id
    //@GeneratedValue
    // @ApiModelProperty(name = "主键",notes = "")
    private Long id ;
    /** 签名id，对应client_sign */
    // @ApiModelProperty(name = "签名id，对应client_sign",notes = "")
    private Long signId ;
    /** 模板内容 */
    // @ApiModelProperty(name = "模板内容",notes = "")
    private String templateText ;
    /** 模板类型 0-验证码类，1-通知类，2-营销类 */
    // @ApiModelProperty(name = "模板类型 0-验证码类，1-通知类，2-营销类",notes = "")
    private Integer templateType ;
    /** 审核是否通过 0-审核中 1-审核不通过 2-审核已通过 */
    // @ApiModelProperty(name = "审核是否通过 0-审核中 1-审核不通过 2-审核已通过",notes = "")
    private Integer templateState ;
    /** 使用场景 0-网站 1-APP 2-微信 */
    // @ApiModelProperty(name = "使用场景 0-网站 1-APP 2-微信",notes = "")
    private Integer useId ;
    /** 网站地址（防轰炸，验证码截图） */
    // @ApiModelProperty(name = "网站地址（防轰炸，验证码截图）",notes = "")
    private String useWeb ;
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
