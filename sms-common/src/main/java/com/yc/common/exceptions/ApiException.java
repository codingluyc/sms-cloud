package com.yc.common.exceptions;

import com.yc.common.enums.ExceptionEnums;
import lombok.Data;

@Data
public class ApiException extends Exception{
    private Integer code;
    public ApiException(String message,Integer code) {
        super(message);
        this.code = code;
    }

    public ApiException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMsg());
        this.code = exceptionEnums.getCode();
    }



}
