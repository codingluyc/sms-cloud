package com.yc.common.exceptions;

import com.yc.common.enums.ExceptionEnums;
import lombok.Data;

@Data
public class StrategyException extends Exception{
    private Integer code;

    public StrategyException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public StrategyException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMsg());
        this.code = exceptionEnums.getCode();
    }


}
