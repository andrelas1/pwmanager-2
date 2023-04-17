package com.as1.pwmanager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HostServiceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(HostServiceAspect.class);
    
    
    @Before("execution(* com.as1.pwmanager.service.impl.HostServiceImpl.findById(Long))")
    public void before(JoinPoint joinPoint) {
        LOG.info("Searching Project with Id {}", joinPoint.getArgs()[0]);
    }
}

