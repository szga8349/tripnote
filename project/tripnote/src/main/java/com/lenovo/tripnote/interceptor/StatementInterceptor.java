/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   StatementInterceptor.java

package com.lenovo.tripnote.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import ibator.dialect.Dialect;
import ibator.dialect.tool.ReflectUtil;  
  
@Intercepts({@Signature(  
        type= StatementHandler.class,   
        method = "prepare", args = {Connection.class,Integer.class})})  
public class StatementInterceptor implements Interceptor {  
      
    public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statement = (RoutingStatementHandler) invocation.getTarget();
		PreparedStatementHandler handler = (PreparedStatementHandler) ReflectUtil.getFieldValue(statement, "delegate");
		RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(handler, "rowBounds");
		if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < 2147483647) {
			BoundSql boundSql = statement.getBoundSql();
			String sql = boundSql.getSql();
			Dialect dialect = (Dialect) Class.forName(DIALECT).newInstance();
			Object obj = statement.getParameterHandler().getParameterObject();
			if ("ibator.dialect.SQLServerDialect".equalsIgnoreCase(DIALECT)) {
				if (obj == null)
					throw new RuntimeException("SQL2000 \u7B2C\u4E00\u4E2A\u53C2\u6570\u4E0D\u80FD\u4E3A\u7A7A");
				dialect.setExample(obj);
			}
			sql = dialect.getLimitString(sql, rowBounds.getOffset(), rowBounds.getLimit());
			if ("ibator.dialect.SQLServerDialect".equalsIgnoreCase(DIALECT)) {
				ReflectUtil.setFieldValue(boundSql, "additionalParameters", null);
				ReflectUtil.setFieldValue(boundSql, "metaParameters", null);
				ReflectUtil.setFieldValue(boundSql, "parameterMappings", null);
				ReflectUtil.setFieldValue(boundSql, "parameterObject", null);
			}
			ReflectUtil.setFieldValue(boundSql, "sql", sql);
		}
		return invocation.proceed();
	}
    public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		DIALECT = properties.getProperty("dialect");
	}

	private String DIALECT;
}  

