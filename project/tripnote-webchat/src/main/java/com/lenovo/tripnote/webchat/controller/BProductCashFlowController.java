package com.lenovo.tripnote.webchat.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.BClassify;
import com.lenovo.tripnote.webchat.entity.vo.BClassifyVo;
import com.lenovo.tripnote.webchat.service.BProductCashFlowService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = "/token/cashflow")
public class BProductCashFlowController {
	@Resource
	private BProductCashFlowService bProductCashFlowService;

	@RequestMapping(value = "/doSearch")
	@ResponseBody
	public ResultVo doSearch(HttpServletRequest request, @RequestBody BClassifyVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		BClassify bProduct = new BClassify();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setCreateUserId(token.getUserId());
		bProduct.setCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchCashFlow());
		return vo;
	}
	@RequestMapping(value = "/share/doSearch")
	@ResponseBody
	public ResultVo doShareSearch(HttpServletRequest request, @RequestBody BClassifyVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		BClassify bProduct = new BClassify();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setCreateUserId(token.getUserId());
		bProduct.setCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchShareProfit());
		return vo;
	}
	@RequestMapping(value = "/product/doSearch")
	@ResponseBody
	public ResultVo doProductSearch(HttpServletRequest request, @RequestBody BClassifyVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		BClassify bProduct = new BClassify();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setCreateUserId(token.getUserId());
		bProduct.setCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchProductProfit());
		return vo;
	}
	@RequestMapping(value = "/balance")
	@ResponseBody
	public ResultVo dobalanceh(HttpServletRequest request, @RequestBody BClassifyVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchBalance(token.getUserId()));
		return vo;
	}
}
