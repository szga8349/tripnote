package com.lenovo.tripnote.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;
@Log4j
public class AuthenticationFilter  extends FormAuthenticationFilter {
	
	public AuthenticationFilter (){
		ConvertUtils.register(new StringToDateConverter(null), Date.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
	}
	
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		return createToken(username, password, request, response);
	}
	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		// TODO Auto-generated method stub
		return super.onPreHandle(request, response, mappedValue);
	}

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled())
					log.trace("Login submission detected.  Attempting to execute login.");
				return executeLogin(request, response);
			}
			if (log.isTraceEnabled())
				log.trace("Login page view.");
			return true;
		}
		if (log.isTraceEnabled())
			log.trace((new StringBuilder())
					.append("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [")
					.append(getLoginUrl()).append("]").toString());
		this.saveRequest(request);
		try {
			JSONObject result = new JSONObject();
			response.setContentType("application/json;charset=UTF-8");
			//response.setHeader("Content-type", "application/json;charset=UTF-8");
			OutputStream ps = response.getOutputStream();
			// 这句话的意思，使得放入流的数据是utf8格式
			result.put("code", "-2");
			result.put("message", "登录已超时请重新登录");
			ps.write(result.toString().getBytes("UTF-8"));
			ps.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e.fillInStackTrace());
		}
		return false;
	}
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		
		return super.onLoginSuccess(token, subject, request, response);
	}

	

}
