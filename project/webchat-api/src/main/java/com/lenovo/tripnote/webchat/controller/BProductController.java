package com.lenovo.tripnote.webchat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductUpdateStatusVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductVo;
import com.lenovo.tripnote.webchat.service.BProductOrderCollageService;
import com.lenovo.tripnote.webchat.service.BProductOrderService;
import com.lenovo.tripnote.webchat.service.BProductService;
import com.lenovo.tripnote.webchat.vo.BatchVo;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

/**
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/token/product")
public class BProductController {
	@Resource
	private BProductService bProductService;
	@Resource
	private BProductOrderCollageService bProductOrderCollageService;
	@Resource
	private BProductOrderService bProductOrderService;

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
		bProductService.updateProduct(info,Integer.valueOf(id));
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
	@RequestMapping(value = "/doSearch")
	@ResponseBody
	public ResultVo myProduct(HttpServletRequest request,BProductSearchVo searchVo) {
		ResultVo vo = new ResultVo();
		TokenVo token = (TokenVo) request.getAttribute("token");
		if(searchVo.getIsMy())
			searchVo.setUserId(token.getUserId());
		if(searchVo.getName()!=null){
			searchVo.setName("%"+searchVo.getName()+"%");
		}
		vo.setData(bProductService.searchProduct(searchVo));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	/*@RequestMapping(value = "/doDetail/{id}")
	@ResponseBody
	public ResultVo doDetail(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductService.detail(Integer.valueOf(id)));
		return vo;
	}*/
	@RequestMapping(value = "/status/doUpdate")
	@ResponseBody
	public ResultVo doStatusDetail(@RequestBody BProductUpdateStatusVo statuses) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bProductService.updateStatusBatch(statuses);
		return vo;
	}
	
	@RequestMapping(value = "/collage/{id}")
	@ResponseBody
	public ResultVo doListCollage(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductOrderCollageService.listCollage(Integer.valueOf(id)));
		return vo;
	}
	@RequestMapping(value = "/order/{id}")
	@ResponseBody
	public ResultVo doListOrder(@PathVariable String id,BProductOrderSearchVo search) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		search.setProductId(Integer.valueOf(id));
		vo.setData(bProductOrderService.listByProduct(search));
		return vo;
	}
	/**验证订单是否有效 在支付时进行检查
	 * @param id
	 * @param priceType
	 * @return
	 */
	@RequestMapping(value = "/order/{id}/check")
	@ResponseBody
	public ResultVo checkOrder(@PathVariable String id,Integer priceType) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductService.checkProduct(Integer.valueOf(id),priceType));
		return vo;
	}

}
