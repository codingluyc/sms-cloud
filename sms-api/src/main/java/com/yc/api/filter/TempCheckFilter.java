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

import java.util.Map;
import java.util.Set;

@Service(value = "template")
@Slf4j
public class TempCheckFilter implements CheckFilter{
    @Autowired
    CacheClient cacheClient;

    private static final String template_text = "templateText";
    private static final String template_placeholder = "#";

    @Override
    public void check(StandardSubmit obj) throws ApiException {
        //找到模板
        Set<Map> templates = cacheClient.sget(RedisKeys.CLIENT_TEMPLATE +obj.getSignId());
        if (templates == null || templates.size() == 0) {
            log.error("模板不存在");
            throw new ApiException(ExceptionEnums.NO_TEMPLATE);
        }
        String sign = obj.getSignature();
        String text = obj.getText();
        text = text.replace(ApiConstants.sign_prefix+sign+ApiConstants.sign_suffix, "");
        for(Map template:templates) {

            String templateText = template.get(template_text).toString();
            if(text.equals(template.get(template_text))){
                //短信内容和模板完全一致
                return;
            }

            if(templateText != null
                    && templateText.contains(template_placeholder)
                    && templateText.length() == (templateText.replaceAll(template_placeholder, "").length()+2)){
                //模板部位空，模板包含且仅包含一个变量
                //获取模板去掉占位符之后的前缀和后缀
                String templateTextPrefix = templateText.substring(0, templateText.indexOf(template_placeholder));
                String templateTextSuffix = templateText.substring(templateText.lastIndexOf(template_placeholder)+1);
                //判断短信的具体内容是否匹配前缀和后缀
                if(text.startsWith(templateTextPrefix) && text.endsWith(templateTextSuffix)){
                    return;
                }
            }
        }
        log.error("模板不存在");
        throw new ApiException(ExceptionEnums.NO_TEMPLATE);
    }
}
