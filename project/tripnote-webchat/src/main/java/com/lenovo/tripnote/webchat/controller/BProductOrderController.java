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

import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderVo;
import com.lenovo.tripnote.webchat.service.BProductOrderService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;
@Log4j
@Controller
@RequestMapping(value = "/token/product/order")
public class BProductOrderController {
	@Resource
	private BProductOrderService bProductOrderService;

	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public ResultVo doAdd(HttpServletRequest request, @RequestBody BProductOrderVo info) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		BProductOrder bProduct = new BProductOrder();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setCreateUserId(token.getUserId());
		bProduct.setCreateUserName(token.getUserLoginName());
		bProduct.setCreateTime(new Date());
		vo.setCode(Result.SUCESSFUL);
		bProductOrderService.insert(bProduct);
		vo.setData(bProduct.getId());
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	@ResponseBody
	public ResultVo doUpdate(@PathVariable String id, @RequestBody BProductOrderVo info) {
		ResultVo vo = new ResultVo();
		BProductOrder bProduct = new BProductOrder();
		try {
			BeanUtils.copyProperties(bProduct, info);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		bProduct.setId(Integer.valueOf(id));
		bProductOrderService.update(bProduct);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	@ResponseBody
	public ResultVo doDelete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		bProductOrderService.deleteBykey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	
	/**我的订单
	 * @param request
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/doMy")
	@ResponseBody
	public ResultVo doMy(HttpServletRequest request,BProductOrderSearchVo search) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		search.setUserId(token.getUserId());
		if(search.getName()!=null){
			search.setName("%"+search.getName()+"%");
		}
		vo.setData(bProductOrderService.search(search));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}

	/**我的客户订单 指发布产品对应的订单
	 * @param request
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/customer/doMy")
	@ResponseBody
	public ResultVo doCustomer(HttpServletRequest request,BProductOrderSearchVo search) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		search.setUserId(token.getUserId());
		if(search.getName()!=null){
			search.setName("%"+search.getName()+"%");
		}
		vo.setData(bProductOrderService.searchCustomer(search));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}


}
