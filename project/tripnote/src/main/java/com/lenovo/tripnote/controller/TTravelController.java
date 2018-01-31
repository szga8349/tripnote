package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.vo.TTravelVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferSearchVo;
import com.lenovo.tripnote.service.TTripnoteScheduleOfferService;
import com.lenovo.tripnote.service.TTripnoteService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**行程报价controller
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/travel")
public class TTravelController {
	/**根据定制日程主键值查询定制日程详细信息
	 * @param scheduleId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Resource
	private TTripnoteService tTripnoteService;
	@Resource
	private TTripnoteScheduleOfferService tTripnoteScheduleOfferService;
	
	@RequestMapping(value = "/price/doDetail/{id}")
	public @ResponseBody ResultVo getTTripNotePrimaryKey(@PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		//优先查询行程报价表 如果不存在的情况下 从行程历史数据查询
		TTripnoteScheduleOfferSearchVo offer = new TTripnoteScheduleOfferSearchVo();
		offer.setTripnoteId(Integer.valueOf(id));
		offer.setCreateUserId(account.getId());
		List<TTripnoteScheduleOfferResultVo> list = tTripnoteScheduleOfferService.search(offer);
		if(list!=null && !list.isEmpty()){
			vo.setData(list);
		}
	    vo.setData(tTripnoteService.getTravelQuotationByKey(Integer.valueOf(id)));
		return vo;
	}
	
	/**根据定制日程主键值查询定制日程详细信息  按照类型归类
	 * @param id
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/price/type/doDetail/{id}")
	public @ResponseBody ResultVo getType(@PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		//优先查询行程报价表 如果不存在的情况下 从行程历史数据查询
		TTripnoteScheduleOfferSearchVo offer = new TTripnoteScheduleOfferSearchVo();
		offer.setTripnoteId(Integer.valueOf(id));
		offer.setCreateUserId(account.getId());
		offer.setOrderby("ttso.`type` desc");
		List<TTripnoteScheduleOfferResultVo> list = tTripnoteScheduleOfferService.search(offer);
		if(list!=null && !list.isEmpty()){
			vo.setData(list);
		}
		vo.setData(tTripnoteService.getTravelQuotationGroupByTypeByKey(Integer.valueOf(id)));
		return vo;
	}
	@RequestMapping(value = "/price/doAdd")
	public @ResponseBody ResultVo addTravelPrice(@RequestBody TTravelVo ttavelVo)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripnoteScheduleOfferService.batchAdd(ttavelVo, account));
		return vo;
	}
}
