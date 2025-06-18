package com.yc.api.advice;

import com.yc.api.common.R;
import com.yc.api.vo.ResultVO;
import com.yc.common.exceptions.ApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(ApiException.class)
    public ResultVO handle(ApiException e) {
        return R.error(e.getMessage(),e.getCode());
    }
}
