package net.koreate.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import net.koreate.vo.MessageVO;

@Component
@Aspect
public class AOPAdvice {
	
	// point cut 실행 이전
	@Before("execution(* net.koreate.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("test start");
		System.out.println("----------------------------------");
		System.out.println("-------- START LOG BEGIN--------");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String date = format.format(new Date());
		System.out.println("startTime : " + date);
		System.out.println("target : " + jp.getTarget());
		System.out.println("type : " + jp.getKind());
		System.out.println("parameters : "+Arrays.toString(jp.getArgs()));
		System.out.println("----------------------------------");
		System.out.println("-------- START LOG END--------");		
	}
	
	
	@After("execution(* net.koreate.service.MessageService*.*(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("test end");
		System.out.println("----------------------------------");
		System.out.println("-------- END LOG BEGIN--------");
		System.out.println("target : " + jp.getTarget());
		System.out.println("type : " + jp.getKind());
		System.out.println("parameters : "+Arrays.toString(jp.getArgs()));
		System.out.println("name : " + jp.getSignature().getName());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String date = format.format(new Date());
		System.out.println("endTime : " + date);
		System.out.println("----------------------------------");
		System.out.println("-------- END LOG END--------");		
	}
	// 실행되기 전 후 
	@Around("execution(* net.koreate.service.MessageServiceImpl.readMessage(..))")
	public Object timeLlog(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("---- start around -------");
		long startTime = System.currentTimeMillis();
		
		Object o = pjp.proceed();
		
		MessageVO message = (MessageVO)o;
		message.setMessage("이제는 우리가 헤어져야할 시간...");
		System.out.println("around : " + o.toString());
		long endTime = System.currentTimeMillis();
		System.out.println(pjp.getSignature().getName()+" 걸린시간 : "+(endTime-startTime));
		System.out.println("---- end around -------");
		return message;
	}
	
}
