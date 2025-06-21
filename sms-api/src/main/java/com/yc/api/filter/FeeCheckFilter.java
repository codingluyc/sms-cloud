package com.yc.api.filter;


import com.yc.api.client.cache.CacheClient;
import com.yc.api.common.ApiConstants;
import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.ApiException;
import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "fee")
@Slf4j
public class FeeCheckFilter implements CheckFilter{

    /**
     * 只要短信内容的文字长度小于等于70个字，按照一条计算
     */
    private final int MAX_LENGTH = 70;

    /**
     * 如果短信内容的文字长度超过70，67字/条计算
     */
    private final int LOOP_LENGTH = 67;

    /**
     * 缓存中存储的余额字段名称
     */
    private static final String BALANCE = "balance";


    @Autowired
    CacheClient cacheClient;

    @Override
    public void check(StandardSubmit submit) throws ApiException {
        //1、从submit中获取到短信内容
        int length = submit.getText().length();

        //2、判断短信内容的长度，如果小于等于70，算作一条，如果大于70字，按照67字/条，算出来当前短信的费用
        if(length <= MAX_LENGTH){
            // 当前短信内容是一条
            submit.setFee(ApiConstants.SINGLE_FEE);
        }else{

            int strip = length % LOOP_LENGTH == 0 ? length / LOOP_LENGTH : length / LOOP_LENGTH + 1;
            submit.setFee(ApiConstants.SINGLE_FEE * strip);
        }

        //3、从Redis中查询出客户剩余的金额
        Long balance = ((Integer) cacheClient.hget(RedisKeys.client_balance + submit.getClientId(), BALANCE)).longValue();

        //4、判断金额是否满足当前短信费用\
        if(balance >= submit.getFee()){
            log.info("【接口模块-校验客户余额】   用户金额充足！！");
            return;
        }

        //5、不满足就抛出异常
        log.info("【接口模块-校验客户余额】   客户余额不足");
        throw new ApiException(ExceptionEnums.CLIENT_BALANCE_NOT_ENOUGH);
    }
}
