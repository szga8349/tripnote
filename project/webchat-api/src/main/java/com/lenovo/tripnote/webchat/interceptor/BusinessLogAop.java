package com.lenovo.tripnote.webchat.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lenovo.tripnote.webchat.common.BusinessLog;

/**统一日志处理
 * @author shijy2
 *
 */
@Aspect
@Configuration
public class BusinessLogAop {

	@Pointcut("@annotation(com.lenovo.tripnote.webchat.common.BusinessLog)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 异步保存日志
		System.out.println("调用接口消耗时间:"+time);
		//saveLog(point, time);
		return result;
	}

	@SuppressWarnings("unused")
	public void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		// LogDO sysLog = new LogDO();
		BusinessLog syslog = method.getAnnotation(BusinessLog.class);
		/*
		 * if (syslog != null) { // 注解上的描述 sysLog.setOperation(syslog.value());
		 * }
		 */
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		// sysLog.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		// 获取request
		HttpServletRequest request = getHttpServletRequest();
		// 设置IP地址

	}

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

}
