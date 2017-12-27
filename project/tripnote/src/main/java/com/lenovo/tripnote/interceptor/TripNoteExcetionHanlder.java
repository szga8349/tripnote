package com.lenovo.tripnote.interceptor;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;

/**
 * 统一异常处理
 * 
 * @author shijy2
 *
 */
@Log4j
public class TripNoteExcetionHanlder implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest httpservletrequest, HttpServletResponse response,
			Object obj, Exception exception) {
		try {
			JSONObject result = new JSONObject();
			response.setHeader("Content-type", "application/json;charset=UTF-8");
			OutputStream ps = response.getOutputStream();
			// 这句话的意思，使得放入流的数据是utf8格式
			result.put("code", "-1");
			result.put("messge", exception instanceof NullPointerException ? "空指针异常" : exception.getMessage());
			ps.write(result.toString().getBytes("UTF-8"));
			ps.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e.fillInStackTrace());
		}
		return null;
	}

}
