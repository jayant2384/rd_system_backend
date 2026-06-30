package com.cs.rd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.cs.rd.service.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {

        log.info("Method Started : {}",
                joinPoint.getSignature().getName());
    }

    @After("execution(* com.cs.rd.service.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {

        log.info("Method Ended : {}",
                joinPoint.getSignature().getName());
    }

    @Around("execution(* com.cs.rd.service.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long startTime = System.currentTimeMillis();

        log.info("Around Start : {}",
                joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("Around End : {}",
                joinPoint.getSignature().getName());

        log.info("Execution Time : {} ms",
                (endTime - startTime));

        return result;
    }
}