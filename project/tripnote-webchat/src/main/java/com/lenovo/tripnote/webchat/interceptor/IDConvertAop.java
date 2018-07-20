package com.lenovo.tripnote.webchat.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.bind.annotation.PathVariable;

import com.lenovo.tripnote.webchat.utils.EncryptionUtils;

/**
 * 使用面向aop 对传入PathVariable 为ID由字符串类型转换成整型数据 后续实现
 * 
 * @author shijy2
 *
 */
@Aspect
@Configuration
public class IDConvertAop {
	private ParameterNameDiscoverer parameterNameDiscoverer;
	
	public IDConvertAop(){
		parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
	}

	@Pointcut("execution(* com.lenovo.tripnote.webchat.controller.*.*(..))")
	private void convertId() {
		
	}

	@Around(value = "convertId()")
	public Object doConvert(ProceedingJoinPoint joinpoint) throws Throwable {
		Object[] args = joinpoint.getArgs();
		if (args != null && args.length != 0) {
			MethodSignature signature = (MethodSignature) joinpoint.getSignature();
			Method method = signature.getMethod();
			Annotation[][] parameterAnnotations = method.getParameterAnnotations(); // 获得所有注解参数
			if (parameterAnnotations != null) {
				Class<?> clazz = method.getDeclaringClass();
				String[] parameters = parameterNameDiscoverer
						.getParameterNames(clazz.getDeclaredMethod(method.getName(), method.getParameterTypes()));
				for (int p = 0; p < parameters.length; p++) {
					String parameter = parameters[p];
					if ("id".equals(parameter) && parameterAnnotations[p].length > 0) // 如果存在注解. 并且参数为ID的方法 才会进行ID解码操作
					{
						Annotation annotation = parameterAnnotations[p][0];
						if (annotation != null && annotation instanceof PathVariable) {
							String s = EncryptionUtils.deEncryption(args[p].toString());
							args[p] = s;
						}
					}
				}
			}
			return joinpoint.proceed(args);
		}
		return joinpoint.proceed();
	}

}
