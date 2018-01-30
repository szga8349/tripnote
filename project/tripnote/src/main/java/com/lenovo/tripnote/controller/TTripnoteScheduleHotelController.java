package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteScheduleHotel;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleHotelVo;
import com.lenovo.tripnote.service.TTripnoteScheduleHotelService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**日程关联酒店controller
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/tripnote/schedulehotel")
public class TTripnoteScheduleHotelController {
	@Resource
	private TTripnoteScheduleHotelService tTripnoteScheduleHotelService;
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo updateScheduletrip(TTripnoteScheduleHotelVo tripnoteScheduleVo,@PathVariable String id){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TTripnoteScheduleHotel schedule = new TTripnoteScheduleHotel();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setId(Integer.valueOf(id));
		schedule.setCreateUserId(account.getId());
		tTripnoteScheduleHotelService.update(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo deleteScheduletrip(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteScheduleHotelService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo detailScheduletrip(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripnoteScheduleHotelService.getByKey(Integer.valueOf(id)));
		return vo;
	}
}
