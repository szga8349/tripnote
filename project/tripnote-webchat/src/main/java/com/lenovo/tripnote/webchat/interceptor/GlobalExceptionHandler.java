package com.lenovo.tripnote.webchat.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)  
    @ResponseBody
    public ResultVo allExceptionHandler(HttpServletRequest request,  
            Exception exception) throws Exception  
    {  
    	ResultVo vo = new ResultVo();
    	vo.setCode(Result.FAUL);
    	vo.setMessage(exception instanceof NullPointerException ? "空指针异常" : exception.getMessage());
		return vo;
    }  

}
