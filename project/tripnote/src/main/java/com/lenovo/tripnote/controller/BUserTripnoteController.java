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
import com.lenovo.tripnote.entity.BUserTripnote;
import com.lenovo.tripnote.entity.vo.BUserTripnoteVo;
import com.lenovo.tripnote.service.BUserTripnoteService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**定制师笔记
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/user/tripnote")
public class BUserTripnoteController {
	@Resource
	private BUserTripnoteService bUserTripnoteService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addBUserTripnote(BUserTripnoteVo tripnoteScheduleVo){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BUserTripnote schedule = new BUserTripnote();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setCreateUserid(account.getId());
		schedule.setCreateUsername(account.getLoginName());
		bUserTripnoteService.insert(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo updateBUserTripnote(BUserTripnoteVo tripnoteScheduleVo,@PathVariable String id){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BUserTripnote schedule = new BUserTripnote();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setId(Integer.valueOf(id));
		schedule.setCreateUserid(account.getId());
		schedule.setCreateUsername(account.getLoginName());
		bUserTripnoteService.update(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo deleteBUserTripnote(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bUserTripnoteService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
}
