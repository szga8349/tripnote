package com.lenovo.tripnote.webchat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;
import com.lenovo.tripnote.webchat.service.BProductService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
@Controller
@RequestMapping()
public class IndexController {
	@Resource
	private BProductService bProductService;
	@RequestMapping(value = "/index")
	@ResponseBody
	public ResultVo myProduct(HttpServletRequest request,BProductSearchVo searchVo) {
		ResultVo vo = new ResultVo();
		if(searchVo.getName()!=null){
			searchVo.setName("%"+searchVo.getName()+"%");
		}
		vo.setData(bProductService.searchProduct(searchVo));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/product/doDetail/{id}")
	@ResponseBody
	public ResultVo doDetail(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductService.detail(Integer.valueOf(id)));
		return vo;
	}
}
