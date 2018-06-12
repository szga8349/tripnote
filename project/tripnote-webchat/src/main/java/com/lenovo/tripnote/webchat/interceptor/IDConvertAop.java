package com.lenovo.tripnote.webchat.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;

/**使用面向aop 对传入PathVariable 为ID由字符串类型转换成整型数据  后续实现
 * @author shijy2
 *
 */
@Aspect
@Configuration
public class IDConvertAop {
	@Pointcut("execution(* com.lenovo.tripnote.webchat.controller.*.*(..))")
	private void convertId() {

	}

	@Around(value = "convertId()") 
	public Object doDetail(ProceedingJoinPoint joinpoint) throws Throwable { 
		Object[] args = joinpoint.getArgs(); 
		MethodSignature signature = (MethodSignature) joinpoint.getSignature();
		Method method = signature.getMethod();
		Parameter[] parameters = method.getParameters();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations(); // 获得所有注解参数
		if (parameterAnnotations != null) {
			for (int p = 0; p < parameters.length; p++) {
				if (parameterAnnotations[p].length > 0) // 如果存在注解.
				{
					Annotation annotation = parameterAnnotations[p][0]; 
					if (annotation != null && annotation instanceof PathVariable) {
						// do someting
					}
				}

			}
		}
		return joinpoint.proceed(args); 
	}
}
