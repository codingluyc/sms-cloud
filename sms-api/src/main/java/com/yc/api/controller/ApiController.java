package com.yc.api.controller;

import com.yc.api.CheckFilterContext;
import com.yc.api.common.R;
import com.yc.api.common.RequestUtil;
import com.yc.api.common.SmsCodeEnum;
import com.yc.api.from.SingleSendForm;
import com.yc.api.vo.ResultVO;
import com.yc.common.constants.RabbitMQConstants;
import com.yc.common.exceptions.ApiException;
import com.yc.common.model.StandardSubmit;
import com.yc.common.util.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Autowired
    private CheckFilterContext checkFilterContext;

    @Autowired
    private RequestUtil requestUtil;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/test")
    public void test() throws ApiException {
        log.info("=============");
        checkFilterContext.check(new StandardSubmit());
        log.info("=============");
    }

    /**
     * **请求路径&方式**
     *
     * **请求路径：** https://sms.beaconcloud.com/v1/sms/single_send
     *
     * **请求方式：** POST
     *
     * **请求头信息**
     *
     * | 请求头      | 请求信息                       |
     * | ----------- | ------------------------------ |
     * | Accept      | application/json;charset=utf-8 |
     * | ContentType | application/json;charset=utf-8 |
     *
     * **请求参数：**
     *
     * | 参数名 | 类型    | 是否必传 | 说明                                     | 示例                             |
     * | ------ | ------- | -------- | ---------------------------------------- | -------------------------------- |
     * | apikey | string  | 是       | 由服务方提供，可以在后台首页中查看       | 887559db54d911edba520242ac120002 |
     * | mobile | string  | 是       | 接收的手机号，仅支持单号码发送           | 18888888888                      |
     * | text   | string  | 是       | 需要发送的短信内容，需要与签名和模板匹配 | 【烽火云】 您的验证码是 1234     |
     * | uid    | string  | 否       | 您业务系统内的ID，回调时会携带此参数     | 10086                            |
     * | state  | integer | 是       | 0-验证码短信 1-通知类短信 2-营销类短信   | 0                                |
     *
     * **响应数据：**
     *
     * | 名称  | 类型    | 说明                                                         |
     * | ----- | ------- | ------------------------------------------------------------ |
     * | code  | integer | 0代表接收成功，其他code代表出错                              |
     * | msg   | string  | 例如“接收成功”，代表短信正在发送，或者是响应具体的错误信息 |
     * | count | integer | 短信的计费条数（70个字一条，超出70个字，按照67个字一条发送） |
     * | fee   | long    | 扣费的金额，单位：厘 ，RMB                                   |
     * | uid   | string  | 客户请求携带的uid信息                                        |
     * | sid   | long    | 平台内的短信id，64位整型                                     |
     *
     * **常见的状态码：**
     *
     * | 状态码 | 说明                          |
     * | ------ | ----------------------------- |
     * | 0      | 代表接收成功，短信发送ing…… |
     * | -1     | 非法的apikey                  |
     * | -2     | 请求的ip不在白名单内          |
     * | -3     | 无可用签名                    |
     * | -4     | 无可用模板                    |
     * | -5     | 手机号格式不正确              |
     * | -6     | 客户余额不足                  |
     *
     *
     * 单条验证码短信接口
     */
    @PostMapping("/sendSingle")
    public ResultVO send(@RequestBody @Validated SingleSendForm form, BindingResult result, HttpServletRequest request) throws ApiException {
        if(result.hasErrors()){
            return R.error(result.getFieldError().getDefaultMessage(), SmsCodeEnum.ERROR.getCode());
        }
        //封装数据
        StandardSubmit standardSubmit = new StandardSubmit();
        standardSubmit.setApiKey(form.getApikey());
        standardSubmit.setMobile(form.getMobile());
        standardSubmit.setText(form.getText());
        standardSubmit.setUid(form.getUid());
        standardSubmit.setState(form.getState());
        standardSubmit.setRealIp(requestUtil.getRealIp(request));
        //校验
        checkFilterContext.check(standardSubmit);
        //生成短信的唯一全局id
        standardSubmit.setSequenceId(snowFlakeUtil.nextId());
        standardSubmit.setSendTime(LocalDateTime.now());
        log.info("standardSubmit:{}", standardSubmit);
        rabbitTemplate.convertAndSend(RabbitMQConstants.PRE_SEND_QUEUE, standardSubmit,new CorrelationData(String.valueOf(standardSubmit.getSequenceId())));
        return R.ok();
    }
}
