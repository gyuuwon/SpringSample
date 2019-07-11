package net.koreate.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;

@Controller
@Aspect
public class AOPAdvice {

	@Around("execution(void net.koreate.service.UserService*.*(..))")
	public void checkUserService(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("AOP checkUserService START");
		
		System.out.println(Arrays.toString(pjp.getArgs()));
		pjp.proceed();
		
		System.out.println("AOP checkUserService END");
	}
	
	@Around("execution(void net.koreate.service.UserService*.*(..))")
	public Object checkUserServiceObject(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("AOP checkUserServiceObject START");
		
		System.out.println(Arrays.toString(pjp.getArgs()));
		Object o = pjp.proceed();
		System.out.println(o);
		
		System.out.println("AOP checkUserServiceObject END");
		
		return o;
	}
}
