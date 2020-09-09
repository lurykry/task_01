package edu.parammanagment.parammanagment.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("@annotation(edu.parammanagment.parammanagment.aspect.LoggingAnnotation)")
    public void logArgsAndReturningValue(JoinPoint jp){
        LOGGER.info("method called:{}", jp.getSignature());
        LOGGER.info("args:{}", jp.getArgs());
    }
}