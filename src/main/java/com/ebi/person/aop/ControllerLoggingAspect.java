package com.ebi.person.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.ebi.person.constant.PersonParameter;

@Component
@Aspect
@Slf4j
public class ControllerLoggingAspect {
	
	/*
	 * Adding logs around any controller has been invoked
	 * */
    @Around(PersonParameter.CONTROLLERS)
    public Object controllerAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object obj = null;
        String methodName = signature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        log.info("Controller::" + className + "." + methodName + " Requested");
        try {
            obj = joinPoint.proceed();
        } finally {
            if (obj instanceof ResponseEntity) {
                ResponseEntity response = (ResponseEntity) obj;
                log.info("Controller::" + className + "." + methodName + " Responded With Code (" + response.getStatusCode() + ") in [" + (System.currentTimeMillis() - startTime) + "] ms");
            }
        }
        return obj;
    }
}
