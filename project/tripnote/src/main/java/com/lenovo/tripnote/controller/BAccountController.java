package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.vo.BAccountVo;
import com.lenovo.tripnote.service.BAccountService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

@Controller
@RequestMapping(value = "/user")
public class BAccountController {
	@Resource
	private BAccountService bAccountService;
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(BAccountVo bpoiVo, @PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BAccount t = new BAccount();
		BeanUtils.copyProperties(t, bpoiVo);
		t.setId(Integer.valueOf(id));
		bAccountService.update(t);
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo detail(@PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bAccountService.getDetail(Integer.valueOf(id)));
		return vo;
	}
	@RequestMapping(value = "/doCheckPasswd")
	public @ResponseBody ResultVo checkPasswd(String passwd)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		if(!StringUtils.equals(account.getLoginPassword(),passwd)){
			vo.setCode(Result.FAUL);
			vo.setMessage("密码验证失败");
			return vo;
		}
		return vo;
	}
}
