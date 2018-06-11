package com.lenovo.tripnote.webchat.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.BProductImage;
import com.lenovo.tripnote.webchat.entity.vo.BProductImageVo;
import com.lenovo.tripnote.webchat.service.BProductImageService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;

import lombok.extern.log4j.Log4j;
@Log4j
@Controller
@RequestMapping(value = "/token/product/image")
public class BProductImageController {
	@Resource
	private BProductImageService bProductImageService;

	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public ResultVo doAdd(HttpServletRequest request, @RequestBody BProductImageVo info) {
		ResultVo vo = new ResultVo();
		BProductImage bProduct = new BProductImage();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductImageService.insert(bProduct));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	@ResponseBody
	public ResultVo doUpdate(@PathVariable String id, @RequestBody BProductImageVo info) {
		ResultVo vo = new ResultVo();
		BProductImage bProduct = new BProductImage();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setId(Integer.valueOf(id));
		bProductImageService.update(bProduct);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	@ResponseBody
	public ResultVo doDelete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		bProductImageService.deleteBykey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}

}
