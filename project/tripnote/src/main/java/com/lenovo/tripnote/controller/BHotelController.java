package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.vo.BHotelSearchVo;
import com.lenovo.tripnote.entity.vo.BHotelVo;
import com.lenovo.tripnote.service.BHotelService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

@Controller
@RequestMapping(value = "/hotel")
public class BHotelController {
    @Resource
    private BHotelService bHotelService;
	
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addHotel(BHotelVo tripnoteScheduleVo){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BHotel schedule = new BHotel();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setCreateUserId(account.getId());
		bHotelService.insert(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BHotel t = new BHotel();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		t.setId(Integer.valueOf(id));
		t.setCreateUserId(account.getId());
		bHotelService.deleteCondition(t);
		return vo;
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(@PathVariable String id,BHotelVo tripnoteScheduleVo) throws IllegalAccessException, InvocationTargetException{
		ResultVo vo = new ResultVo();
		BHotel t = new BHotel();
		BeanUtils.copyProperties(t, tripnoteScheduleVo);
		t.setId(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		bHotelService.update(t);
		return vo;
	}

	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo getDetail(@PathVariable String id) throws IllegalAccessException, InvocationTargetException{
		ResultVo vo = new ResultVo();
		BHotel result = bHotelService.getByKey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		vo.setData(result);
		return vo;
	}
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo searchDetail(BHotelSearchVo search) throws IllegalAccessException, InvocationTargetException{
		ResultVo vo = new ResultVo();
		List<BHotel> result = bHotelService.searchHotels(search);
		vo.setCode(Result.SUCESSFUL);
		vo.setData(result);
		return vo;
	}
}
