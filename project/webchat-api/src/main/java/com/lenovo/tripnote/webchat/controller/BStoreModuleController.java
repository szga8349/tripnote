package com.lenovo.tripnote.webchat.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.BStoreModule;
import com.lenovo.tripnote.webchat.entity.vo.BStoreModuleVo;
import com.lenovo.tripnote.webchat.service.BStoreModuleService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = "/token/storemodule")
public class BStoreModuleController {
	@Resource
	private BStoreModuleService bStoreModuleService;

	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public ResultVo doAdd(HttpServletRequest request, @RequestBody BStoreModuleVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		BStoreModule bProduct = new BStoreModule();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setCreateUserId(token.getUserId());
		bProduct.setCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bStoreModuleService.insert(bProduct));
		return vo;
	}
	@RequestMapping(value = "/doList")
	@ResponseBody
	public ResultVo doList(HttpServletRequest request,Integer storeId) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bStoreModuleService.searchBStoreModule(storeId));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	@ResponseBody
	public ResultVo doUpdate(@PathVariable String id, @RequestBody BStoreModuleVo info) {
		ResultVo vo = new ResultVo();
		BStoreModule bProduct = new BStoreModule();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setId(Integer.valueOf(id));
		bStoreModuleService.update(bProduct);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	@ResponseBody
	public ResultVo doDelete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		bStoreModuleService.deleteBykey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
}
