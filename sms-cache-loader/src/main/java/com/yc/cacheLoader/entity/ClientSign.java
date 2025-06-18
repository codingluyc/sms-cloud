package com.yc.cacheLoader.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientSign {
    /** 主键 */
    // @Id
    //@GeneratedValue
    // @ApiModelProperty(name = "主键",notes = "")
    private Long id ;
    /** 客户id，对应client_business表 */
    // @ApiModelProperty(name = "客户id，对应client_business表",notes = "")
    private Long clientId ;
    /** 短信签名内容 */
    // @ApiModelProperty(name = "短信签名内容",notes = "")
    private String signInfo ;
    /** 审核是否通过 0-审核中 1-审核不通过 2-审核已通过 */
    // @ApiModelProperty(name = "审核是否通过 0-审核中 1-审核不通过 2-审核已通过",notes = "")
    private Integer signState ;
    /** 模板类型 0-验证码，通知类，1-营销类 */
    // @ApiModelProperty(name = "模板类型 0-验证码，通知类，1-营销类",notes = "")
    private Integer signType ;
    /** 业务网址与场景 */
    // @ApiModelProperty(name = "业务网址与场景",notes = "")
    private String businessWeb ;
    /** 证明文件描述 如：公司营业执照，APP：应用商店APP管理后台截屏，网站名：ICP备案证明，公众号、小程序：微信公众平台管理页面截图，商标：商标注册证书、商标软著权 */
    // @ApiModelProperty(name = "证明文件描述 如：公司营业执照，APP：应用商店APP管理后台截屏，网站名：ICP备案证明，公众号、小程序：微信公众平台管理页面截图，商标：商标注册证书、商标软著权",notes = "")
    private String proveDescr ;
    /** 证明文件图片链接，多个以,隔开 */
    // @ApiModelProperty(name = "证明文件图片链接，多个以,隔开",notes = "")
    private String proveFile ;
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
