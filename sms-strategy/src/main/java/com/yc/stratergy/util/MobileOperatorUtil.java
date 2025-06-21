package com.yc.stratergy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 移动运营商工具类
 */
@Component
public class MobileOperatorUtil {
    @Autowired
    RestTemplate restTemplate;


    @Autowired
    ObjectMapper objectMapper;

    //360号段查询url
    private final String URL = "https://cx.shouji.360.cn/phonearea.php?number=";
    private final String CODE = "code";
    private final String DATA = "data";
    private final String PROVINCE = "province";
    private final String CITY = "city";
    private final String SP = "sp";
    private final String SPACE = " ";
    private final String SEPARATOR = ",";

    /**
     * 获取手机号运营商
     * @param mobile 手机号前7位
     * @return
     */
    public String getMobileLocation(String mobile) throws JsonProcessingException {
        String result = restTemplate.getForObject(URL + mobile, String.class);
        Map<String, Object> map = objectMapper.readValue(result, Map.class);
        Integer code = (Integer) map.get(CODE);
        if(code != 0){
            return null;
        }
        Map<String,String> areaOperator = (Map<String, String>) map.get(DATA);
        String province = areaOperator.get(PROVINCE);
        String city = areaOperator.get(CITY);
        String sp = areaOperator.get(SP);

        return province+SPACE+city+SEPARATOR+sp;
    }
}
