package com.ku.people.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.ku.people.repository.*.*(..))")
    public void callAtUserRepository() {
    }

    @AfterReturning(value = "callAtUserRepository()")
    public void recordSuccessfulExecution(JoinPoint joinPoint) {
        String message = "Successfully executed method - %s, class- %s\n";
        log.info(String.format(message,
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName()));
    }

    @AfterThrowing(value = "callAtUserRepository()", throwing = "exception")
    public void recordFailedExecution(JoinPoint joinPoint, Exception exception) {
        String message = "Method - %s, class- %s, was terminated with an exception - %s\n";
        log.info(String.format(message,
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception));
    }
}
