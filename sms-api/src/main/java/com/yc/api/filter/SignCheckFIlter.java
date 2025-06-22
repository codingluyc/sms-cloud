package com.yc.api.filter;
import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.api.client.cache.CacheClient;
import com.yc.api.common.ApiConstants;
import com.yc.common.exceptions.ApiException;
import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service(value = "sign")
@Slf4j
public class SignCheckFIlter implements CheckFilter{

    private static final int sign_start_index = 1;

    private static final String client_sign_info = "signInfo";
    @Autowired
    CacheClient  cacheclient;

    @Override
    public void check(StandardSubmit obj) throws ApiException {
        String text = obj.getText();
        if(!text.startsWith(ApiConstants.sign_prefix) || !text.contains(ApiConstants.sign_suffix)){
            log.error("签名格式错误,{}",text);
            throw new ApiException(ExceptionEnums.NO_SIGN);
        }
        String sign = text.substring(sign_start_index,text.indexOf(ApiConstants.sign_suffix));
        if(StringUtils.isEmpty(sign)){
            log.error("签名为空");
            throw new ApiException(ExceptionEnums.NO_SIGN);
        }

        //查询缓存签名
        Set<Map> signSet = cacheclient.sget(RedisKeys.CLIENT_SIGN + obj.getClientId());
        if(signSet == null || signSet.isEmpty()){
            log.error("用户未绑定签名");
            throw new ApiException(ExceptionEnums.NO_SIGN);
        }
        for(Map map : signSet){
            if(sign.equals(map.get(client_sign_info))){
                obj.setSignature( sign);
                obj.setSignId(Long.parseLong(String.valueOf(map.get("id"))) );
                return;
            }
        }
        log.error("用户没有匹配的签名");
        throw new ApiException(ExceptionEnums.NO_SIGN);
    }
}
