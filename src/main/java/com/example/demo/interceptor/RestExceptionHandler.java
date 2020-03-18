package com.example.demo.interceptor;

import javax.validation.ValidationException;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.Msg;
import com.example.demo.common.ReturnResult;
import com.example.demo.exception.RestException;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常拦截处理器
 * 
 * @author hongtao.hao
 * @date 2019/6/27
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    /**
     * 自定义异常
     */
    @ExceptionHandler(RestException.class)
    @ResponseBody
    public String handleRestException(RestException e) {
        printLog(e);
        return JSON.toJSONString(ReturnResult.createErrorReturn(e.getMsg()));
    }

    /**
     * 参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public String handleValidationException(ValidationException e) {
        printLog(e);
        return JSON.toJSONString(ReturnResult.createErrorReturn(Msg.ParameterError.getCode(), e.getMessage()));
    }

    /**
     * 参数校验
     *
     * @param e
     * @return
     * TODO 怎么只返回注解上的value
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        printLog(e);
        return JSON.toJSONString(ReturnResult.createErrorReturn(Msg.ParameterError.getCode(), e.getMessage()));
    }

    /**
     * 参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public String handleBindException(BindException e) {
        printLog(e);
        return JSON.toJSONString(ReturnResult.createErrorReturn(Msg.ParameterError.getCode(), e.getMessage()));
    }


     //shiro 权限
//     @ExceptionHandler(AuthorizationException.class)
//     @ResponseBody
//     public ReturnResult handleAuthorizationException(AuthorizationException e){
//     logger.error(e.getMessage(), e);
//     return ReturnResult.createSuccessReturn();
//
//     }

    /**
     * 服务的异常统一归为500
     * @param e
     * @return
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        printLog(e);
        return JSON.toJSONString(ReturnResult.createErrorReturn(Msg.Error));
    }

    /**
     * 异常信息打印日志
     *
     * @param e
     */
    private void printLog(Exception e) {

        log.error("error :", e);
    }

}
