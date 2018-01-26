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
import com.lenovo.tripnote.entity.TTripnoteScheduleTraffic;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTrafficVo;
import com.lenovo.tripnote.service.TTripnoteScheduleTrafficService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**日程交通
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/tripnote/scheduletraffic")
public class TTripnoteScheduleTrafficController {
	@Resource
	private TTripnoteScheduleTrafficService tTripnoteScheduleTrafficService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addScheduletripTraffic(TTripnoteScheduleTrafficVo tripnoteScheduleVo){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TTripnoteScheduleTraffic schedule = new TTripnoteScheduleTraffic();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setCreateUserid(account.getId());
		schedule.setCreateuserName(account.getLoginName());
		tTripnoteScheduleTrafficService.insert(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo updateScheduletripTraffic(TTripnoteScheduleTrafficVo tripnoteScheduleVo,@PathVariable String id){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TTripnoteScheduleTraffic schedule = new TTripnoteScheduleTraffic();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setId(Integer.valueOf(id));
		schedule.setCreateUserid(account.getId());
		tTripnoteScheduleTrafficService.update(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo deleteScheduletripTraffic(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteScheduleTrafficService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo detailScheduletripTraffic(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripnoteScheduleTrafficService.getByKey(Integer.valueOf(id)));
		return vo;
	}
}
