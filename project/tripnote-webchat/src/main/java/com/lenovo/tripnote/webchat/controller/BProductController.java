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

import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductVo;
import com.lenovo.tripnote.webchat.service.BProductService;
import com.lenovo.tripnote.webchat.vo.BatchVo;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;

/**
 * @author shijy2
 *
 */
@Log4j
@Controller
@RequestMapping(value = "/token/product")
public class BProductController {
	@Resource
	private BProductService bProductService;

	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public ResultVo doAdd(HttpServletRequest request, @RequestBody BProductVo info) {
		TokenVo token = (TokenVo) request.getAttribute("token");
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductService.addProduct(info, token));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	@ResponseBody
	public ResultVo doUpdate(@PathVariable String id, @RequestBody BProductVo info) {
		ResultVo vo = new ResultVo();
		BProduct bProduct = new BProduct();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setId(Integer.valueOf(id));
		bProduct.setLastUpdateTime(new Date());
		bProductService.update(bProduct);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	@ResponseBody
	public ResultVo doDelete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		bProductService.deleteBykey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doDelete")
	@ResponseBody
	public ResultVo doBatchDelete(@RequestBody BatchVo batchVo) {
		ResultVo vo = new ResultVo();
		bProductService.deleteBatch(batchVo);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doMy")
	@ResponseBody
	public ResultVo myProduct(HttpServletRequest request,BProductSearchVo searchVo) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		searchVo.setUserId(token.getUserId());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductService.searchProduct(searchVo));
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	@ResponseBody
	public ResultVo doDetail(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductService.detail(Integer.valueOf(id)));
		return vo;
	}

}
