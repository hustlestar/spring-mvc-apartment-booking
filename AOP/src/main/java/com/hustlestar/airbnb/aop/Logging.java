package com.hustlestar.airbnb.aop;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

/**
 * Created by Yauheni_Malashchytsk on 3/21/2017.
 */

@Aspect
@Component
@ContextConfiguration(classes = SpringContextAOP.class)
public class Logging {

    private static final Logger logger = LogManager.getRootLogger();

    @Before("execution(* com.hustlestar.airbnb.dao..*(..))")
    public void before(JoinPoint joinPoint) {
        logger.log(Level.INFO, "Before " + joinPoint.getSignature().getName() +
                " called with " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.hustlestar.airbnb.dao..*(..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        logger.log(Level.INFO, "After " + joinPoint.getSignature().getName() +
                " result is " + result);
    }
}

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.hustlestar.airbnb"})
class SpringContextAOP {
}


