package com.lenovo.tripnote.webchat.common;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**业务日志 用于用户行为分析
 * @author shijy2
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessLog {
	String value() default "";
}
