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
import com.lenovo.tripnote.entity.TTripnoteSchedule;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleVo;
import com.lenovo.tripnote.service.TTripNoteScheduleService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**线路
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/tripnote/schedule")
public class TTripNoteScheduleController {
	
	@Resource
	private TTripNoteScheduleService tTripNoteScheduleService;
	
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addSchedule(TTripNoteScheduleVo tripnoteScheduleVo){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TTripnoteSchedule schedule = new TTripnoteSchedule();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setCreateUserid(account.getId());
		schedule.setCreateuserName(account.getLoginName());
		tTripNoteScheduleService.insert(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	/**前端传入行程ID和一时间段 根据这时间段默认生成多条记录，并返回主键值
	 * @param tripnoteVo
	 * @param tripnoteId
	 * @return
	 */
	@RequestMapping(value = "/doAdd/{tripnoteId}")
	public @ResponseBody ResultVo addRangSchedule(TTripNoteScheduleVo tripnoteVo,@PathVariable String tripnoteId){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripNoteScheduleService.addRangeSchedule(Integer.valueOf(tripnoteId), tripnoteVo.getIndexdate(),(BAccount)subject.getPrincipal()));
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripNoteScheduleService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(@PathVariable String id,TTripNoteScheduleVo tripnoteScheduleVo) throws IllegalAccessException, InvocationTargetException{
		ResultVo vo = new ResultVo();
		TTripnoteSchedule t = new TTripnoteSchedule();
		BeanUtils.copyProperties(t, tripnoteScheduleVo);
		t.setId(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		tTripNoteScheduleService.update(t);
		return vo;
	}
	/**根据定制日程主键值查询定制日程详细信息
	 * @param scheduleId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/doDeail/{scheduleId}")
	public @ResponseBody ResultVo getDetail(@PathVariable String scheduleId) throws IllegalAccessException, InvocationTargetException{
		ResultVo vo = new ResultVo();
		TTripNoteScheduleResultVo result = tTripNoteScheduleService.getDetailByKey(Integer.valueOf(scheduleId));
		vo.setCode(Result.SUCESSFUL);
		vo.setData(result);
		return vo;
	}
}
