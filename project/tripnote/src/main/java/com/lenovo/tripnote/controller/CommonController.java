package com.lenovo.tripnote.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.service.CommonService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/common")
@Log4j
public class CommonController {

	@Resource
	private CommonService commonService;

	@RequestMapping(value = "/upload/{model}/image", method = RequestMethod.POST)
	public @ResponseBody ResultVo uploadImage(@PathVariable String model, HttpServletRequest request) {
		ResultVo vo = new ResultVo();
		try {
			Subject subject = SecurityUtils.getSubject();
			BAccount account = (BAccount) subject.getPrincipal();
			MultipartHttpServletRequest multipartRequest = null;
			if(request instanceof MultipartHttpServletRequest){
				 multipartRequest = (MultipartHttpServletRequest)request;
			}
			else if(request instanceof ShiroHttpServletRequest){
				ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest) request;
				CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
				 multipartRequest = commonsMultipartResolver
						.resolveMultipart((HttpServletRequest) shiroRequest.getRequest());
				
			}
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			vo.setCode(Result.SUCESSFUL);
			return commonService.upload(account, files,model);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			vo.setCode(Result.FAUL);
			vo.setMessage(e.getMessage());
		}
		return vo;

	}

}
