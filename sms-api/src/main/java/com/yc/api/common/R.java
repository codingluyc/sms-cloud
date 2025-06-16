package com.yc.api.common;

import com.yc.api.vo.ResultVO;

public class R {
    public static ResultVO ok(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(String message,Integer code) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }
}
