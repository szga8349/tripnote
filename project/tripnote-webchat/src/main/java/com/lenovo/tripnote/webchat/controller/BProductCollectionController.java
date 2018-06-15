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

import com.lenovo.tripnote.webchat.entity.BProductCollection;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollectionSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollectionVo;
import com.lenovo.tripnote.webchat.service.BProductCollectionService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = "/token/product/collection")
public class BProductCollectionController {
	@Resource
	private BProductCollectionService bProductCollectionService;

	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public ResultVo doAdd(HttpServletRequest request, @RequestBody BProductCollectionVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		BProductCollection bProduct = new BProductCollection();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setCollectionUserId(token.getUserId());
		bProduct.setCollectionUserName(token.getUserLoginName());
		bProduct.setCollectionCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCollectionService.insert(bProduct));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	@ResponseBody
	public ResultVo doUpdate(@PathVariable String id, @RequestBody BProductCollectionVo info) {
		ResultVo vo = new ResultVo();
		BProductCollection bProduct = new BProductCollection();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setId(Integer.valueOf(id));
		bProductCollectionService.update(bProduct);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	@ResponseBody
	public ResultVo doDelete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		bProductCollectionService.deleteBykey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	
	@RequestMapping(value = "/doMy")
	@ResponseBody
	public ResultVo doSearch(HttpServletRequest request,BProductCollectionSearchVo search) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		search.setUserId(token.getUserId());
		vo.setData(bProductCollectionService.search(search));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
}
