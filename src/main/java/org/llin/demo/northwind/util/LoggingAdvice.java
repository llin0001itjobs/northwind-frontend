package org.llin.demo.northwind.util;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	private static final String PACKAGE = "org.llin.demo.northwind";
	
	private Instant beforeCache;
	private Instant beforeController;
	private Instant beforeService;
	private Instant beforeUtility;
	
	
	@Before("execution (* " + PACKAGE + ".cache.*.*(..))")
    public void beforeCache(JoinPoint joinPoint)  {
		beforeCache = Instant.now();
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.trace("*=*=*=* Starting cache + method: " +  methodName + " " + className);
    }
    
    @After("execution (* " + PACKAGE + ".cache.*.*(..))")
    public void afterCache(JoinPoint joinPoint)  {    	
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
        Instant after = Instant.now();       
    	logger.trace("*=*=*=* Ending cache + method: " +  methodName + " " + className);
    	long elapsedTimeMillis = Duration.between(beforeCache, after).toSeconds();
    	logger.trace("*=*=*=* finished " + elapsedTimeMillis + " seconds");
    }
        
    @Before("execution (* " + PACKAGE + ".controller.*.*(..))")
    public void beforeController(JoinPoint joinPoint)  {
    	beforeController = Instant.now();
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.trace("*=*=*=* Starting controller + method: " +  methodName + " " + className);
    }
    
    @After("execution (* " + PACKAGE +  ".controller.*.*(..))")
    public void afterController(JoinPoint joinPoint)  {    	
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
        Instant after = Instant.now();       
        long elapsedTimeMillis = Duration.between(beforeController, after).toSeconds();
    	logger.trace("*=*=*=* Ending controller + method: " +  methodName + " " + className);
    	logger.trace("*=*=*=* finished " + elapsedTimeMillis + " seconds");
    }
        
    @Before("execution (* " + PACKAGE + ".service.*.*(..))")
    public void beforeService(JoinPoint joinPoint)  {
    	beforeService = Instant.now();
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.trace("*=*=*=* Starting service + method: " +  methodName + " " + className);
    }
    
    @After("execution (* " + PACKAGE + ".service.*.*(..))")
    public void afterService(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
        Instant after = Instant.now();  
    	logger.trace("*=*=*=* Ending service + method: " +  methodName + " " + className);
    	long elapsedTimeMillis = Duration.between(beforeService, after).toSeconds();
    	logger.trace("*=*=*=* finished " + elapsedTimeMillis + " seconds");
    }
    
    @Before("execution (* " + PACKAGE + ".util.*.*(..))")
    public void beforeUtility(JoinPoint joinPoint)  {
    	beforeUtility = Instant.now();
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.trace("*=*=*=* Starting utility + method: " +  methodName + " " + className);
    }
        
    @After("execution (* " + PACKAGE + ".util.*.*(..))")
    public void afterUtility(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();

        Instant after = Instant.now();  
    	logger.trace("*=*=*=* Ending utility + method: " +  methodName + " " + className);
    	long elapsedTimeMillis = Duration.between(beforeUtility, after).toSeconds();
    	logger.trace("*=*=*=* finished " + elapsedTimeMillis + " seconds");
    }
}
