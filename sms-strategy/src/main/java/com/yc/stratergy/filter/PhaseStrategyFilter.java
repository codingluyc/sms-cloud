package com.yc.stratergy.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yc.common.constants.RabbitMQConstants;
import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.MobileOperatorEnum;
import com.yc.common.model.MobileAreaOperator;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.client.cache.CacheClient;
import com.yc.stratergy.util.MobileOperatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 号段补全：获取手机号的运营商和归属地
 */
@Service("phase")
@Slf4j
public class PhaseStrategyFilter implements StrategyFilter{
    private final int mobile_start = 0;
    private final int mobile_end = 7;
    private final String UNKNOWN = "未知";

    @Autowired
    CacheClient cacheClient;

    @Autowired
    MobileOperatorUtil mobileOperatorUtil;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public void check(StandardSubmit submit) {
        String mobile = submit.getMobile().substring(mobile_start, mobile_end);
        String areaInfo = cacheClient.get(RedisKeys.PHASE +mobile);
        if(null == areaInfo){
            //请求第三方
            try {
                areaInfo = mobileOperatorUtil.getMobileLocation(mobile);
                if(null != areaInfo){
                    //从第三方获取到了手机号段信息，将其通过消息通知后台管理模块
                    rabbitTemplate.convertAndSend(RabbitMQConstants.MOBILE_AREA_OPERATOR,new MobileAreaOperator(mobile,areaInfo));
                }
            } catch (JsonProcessingException e) {
                areaInfo = UNKNOWN;
                throw new RuntimeException(e);
            }
        }

        if(!UNKNOWN.equals(areaInfo)) {
            String[] areaInfoArray = areaInfo.split(",");
            if (areaInfoArray.length == 2) {
                submit.setMobileLocation(areaInfoArray[0]);
                submit.setOperatorId(MobileOperatorEnum.getByName(areaInfoArray[1]).getCode());
            } else {
                areaInfo = UNKNOWN;
            }
        }

        if(!UNKNOWN.equals(areaInfo)){
            log.error("mobile:{} mobileLocation: {}, operatorId: {}", submit.getMobile(),submit.getMobileLocation(), submit.getOperatorId());
        }



    }


}
