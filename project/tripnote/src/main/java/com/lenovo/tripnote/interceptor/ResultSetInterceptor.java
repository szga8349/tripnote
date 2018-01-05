/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   ResultSetInterceptor.java

package com.lenovo.tripnote.interceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import ibator.dialect.tool.ReflectUtil;

// Referenced classes of package ibator.dialect.tool:
//            ReflectUtil
@Intercepts({@Signature(  
        type= ResultSetHandler.class,   
        method = "handleResultSets", args = {Statement.class})})  
public class ResultSetInterceptor implements Interceptor {  
      
      
    public Object intercept(Invocation invocation) throws Throwable {  
        DefaultResultSetHandler resultSet = (DefaultResultSetHandler)invocation.getTarget();  
    	RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(resultSet, "rowBounds");
		if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < 2147483647)
			ReflectUtil.setFieldValue(resultSet, "rowBounds", new RowBounds());
		return invocation.proceed();
    }  
  
    public Object plugin(Object target) {  
        return Plugin.wrap(target, this);  
    }  
  
    public void setProperties(Properties properties) {  
    }  
}  
/*
	DECOMPILATION REPORT

	Decompiled from: D:\source\tripnote\project\tripnote\src\main\webapp\WEB-INF\lib\dialect.jar
	Total time: 15 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/