package com.stk.website.filter;

import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Royle.Huang
 * @date 2019/3/7 21:32
 * @description: 异常处理
 */
@Component
@Aspect
public class ExceptionHandler {

    @Pointcut("execution(public * com.stk.website.controller..*.*(..))")
    public void controllerMethod(){ }

    @Around("controllerMethod()")
    public Object response(ProceedingJoinPoint point) throws Throwable {
        Object res;
        try {
            res = point.proceed();
        } catch (ServiceException e) {
            res = new BaseResponse(e.getCode(), e.getMessage());
        } catch (Exception e) {
            res = new BaseResponse(500, "系统错误");
        }
        return res;
    }
}
