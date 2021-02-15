package com.ebi.person.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ebi.person.constant.PersonParameter;


@Component
@Aspect
@Slf4j
public class ServiceLoggingAspect {
	
	/*
	 * Adding logs around any service has been invoked
	 * */
    @Around(PersonParameter.SERVICES)
    public Object serviceAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object obj = null;
        String methodName = signature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        log.info("Service::" + className + "." + methodName + " Called");
        try {
            obj = joinPoint.proceed();
        } finally {
            log.info("Service::" + className + "." + methodName + " Finished in [" + (System.currentTimeMillis() - startTime) + "] ms");
        }
        return obj;
    }

    @AfterThrowing(pointcut = PersonParameter.SERVICES, throwing = "error")
    public void serviceAfterThrowingAdvice(JoinPoint joinPoint, Throwable error) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String errorID = String.valueOf(System.currentTimeMillis());
        log.info("Service::" + className + "." + methodName + " Throws Error, Error ID : " + errorID);
        log.error("Service::" + className + "." + methodName + " Throws Error With ID (" + errorID + ")-> ", error);
    }
}
