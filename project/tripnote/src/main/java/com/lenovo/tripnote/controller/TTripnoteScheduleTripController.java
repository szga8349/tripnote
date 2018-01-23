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
import com.lenovo.tripnote.entity.TTripnoteScheduleTrip;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripVo;
import com.lenovo.tripnote.service.TTripnoteScheduleTripService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**日程行程 
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/tripnote/scheduletrip")
public class TTripnoteScheduleTripController {
	
	@Resource
	private TTripnoteScheduleTripService tTripnoteScheduleTripService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addScheduletrip(TTripnoteScheduleTripVo tripnoteScheduleVo){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TTripnoteScheduleTrip schedule = new TTripnoteScheduleTrip();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setCreateUserId(account.getId());
		tTripnoteScheduleTripService.insert(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo updateScheduletrip(TTripnoteScheduleTripVo tripnoteScheduleVo,@PathVariable String id){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TTripnoteScheduleTrip schedule = new TTripnoteScheduleTrip();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setId(Integer.valueOf(id));
		schedule.setCreateUserId(account.getId());
		tTripnoteScheduleTripService.update(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo deleteScheduletrip(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteScheduleTripService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
}
