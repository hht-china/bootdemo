package com.example.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.SysLog;
import com.example.demo.util.LogUtil;

/**
 * @author hongtao.hao
 * @date 2019/6/27
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect extends HandlerInterceptorAdapter {

    // 定义Pointcut，此方法不能有返回值，该方法只是一个标示
    // @annotation 指定自定义注解：在自定义注解出现的位置切入代码
    @Pointcut("execution(* com.example.demo.rpc.*.*(..))")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        SysLog sysLog = LogUtil.getSysLog(joinPoint);

        log.info(JSON.toJSONString(sysLog));
    }

    @AfterReturning(returning = "result", pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        SysLog sysLog = LogUtil.getSysLog(joinPoint);

        log.info(" {} return : {}", JSON.toJSONString(sysLog), result);
    }
}
