package com.sample.LibraryService.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * This AOP prints log before and after each method invocation in
 * LibraryServiceImpl
 * 
 * @author Sahana
 *
 */
@Aspect
@Component
public class LibraryServiceAspect {

	@Before(value = "execution(* com.sample.LibraryService.service.impl.LibraryServiceImpl.*(..)) ")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("Before method:" + joinPoint.getSignature());
	}

	@After(value = "execution(* com.sample.LibraryService.service.impl.LibraryServiceImpl.*(..)) ")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("After method:" + joinPoint.getSignature());
	}

}
