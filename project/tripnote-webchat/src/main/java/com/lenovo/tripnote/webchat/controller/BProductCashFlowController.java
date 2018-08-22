package com.lenovo.tripnote.webchat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowSearchVo;
import com.lenovo.tripnote.webchat.service.BProductCashFlowService;
import com.lenovo.tripnote.webchat.vo.ProfitTypeVo;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;
@Controller
@RequestMapping(value = "/token/cashflow")
public class BProductCashFlowController {
	@Resource
	private BProductCashFlowService bProductCashFlowService;

	@RequestMapping(value = "/doSearch")
	@ResponseBody
	public ResultVo doSearch(HttpServletRequest request,   BProductCashFlowSearchVo searchVo) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		searchVo.setUserId(token.getUserId());
		//searchVo.setCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchCashFlow(searchVo));
		return vo;
	}
	@RequestMapping(value = "/share/doSearch")
	@ResponseBody
	public ResultVo doShareSearch(HttpServletRequest request,  BProductCashFlowSearchVo searchVo) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		searchVo.setUserId(token.getUserId());
		//设置分享收益类型
		searchVo.setProfitType(ProfitTypeVo.SHARE.index());
		//bProduct.setCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchShareProfit(searchVo));
		return vo;
	}
	@RequestMapping(value = "/product/doSearch")
	@ResponseBody
	public ResultVo doProductSearch(HttpServletRequest request,  BProductCashFlowSearchVo searchVo) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		searchVo.setUserId(token.getUserId());
		//设置订单收益类型
		searchVo.setProfitType(ProfitTypeVo.ORDER.index());
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchProductProfit(searchVo));
		return vo;
	}
	@RequestMapping(value = "/balance")
	@ResponseBody
	public ResultVo dobalanceh(HttpServletRequest request) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductCashFlowService.searchBalance(token.getUserId()));
		return vo;
	}
}
