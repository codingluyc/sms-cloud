package com.yc.api.filter;

import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.ApiException;
import com.yc.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service(value = "mobile")
@Slf4j
public class MobileCheckFilter implements CheckFilter{
    /**
     * 国内手机号的正则表达式
     */
    private final static Pattern CHINA_PATTERN = Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");

    @Override
    public void check(StandardSubmit obj) throws ApiException {
        String mobile = obj.getMobile();
        if (!StringUtils.isEmpty(mobile) && !CHINA_PATTERN.matcher(mobile).matches()) {
            log.error("mobile check error");
            throw new ApiException(ExceptionEnums.PHONE_FORMAT_ERROR);
        }

    }
}
