package com.mycompany.ecom.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /*
     * Controller Layer Logging
     */

    @Before("execution(* com.mycompany.ecom.Controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {

        log.info("==================================================");
        log.info("API Called : {}", joinPoint.getSignature().getName());
        log.info("Arguments : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "execution(* com.mycompany.ecom.Controller.*.*(..))",
            returning = "result")
    public void logAfterController(JoinPoint joinPoint, Object result) {

        log.info("API Completed : {}", joinPoint.getSignature().getName());
        log.info("Response : {}", result);
        log.info("==================================================");
    }

    /*
     * Service Layer Logging
     */

    @Before("execution(* com.mycompany.ecom.Service.Impl.*.*(..))")
    public void logBeforeService(JoinPoint joinPoint) {

        log.info("Service Method Started : {}",
                joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* com.mycompany.ecom.Service.Impl.*.*(..))",
            returning = "result")
    public void logAfterService(JoinPoint joinPoint, Object result) {

        log.info("Service Method Completed : {}",
                joinPoint.getSignature().getName());
    }

    /*
     * Exception Logging
     */

    @AfterThrowing(
            pointcut = "execution(* com.mycompany.ecom..*.*(..))",
            throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {

        log.error("Exception in Method : {}",
                joinPoint.getSignature().getName());

        log.error("Exception Message : {}",
                exception.getMessage());
    }
}