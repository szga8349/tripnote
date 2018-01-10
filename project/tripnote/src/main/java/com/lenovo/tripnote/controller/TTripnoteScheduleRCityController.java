package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.TTripnoteScheduleRCity;
import com.lenovo.tripnote.service.TTripnoteScheduleRCityService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**定制日程与城市关联操作
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/tripnote/schedulecity")
public class TTripnoteScheduleRCityController {
	@Resource
	private TTripnoteScheduleRCityService tTripnoteScheduleRCityService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo add(TTripnoteScheduleRCity rcity) throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteScheduleRCityService.insert(rcity);
		return vo;
	}

	@RequestMapping(value = "/doDelete")
	public @ResponseBody ResultVo delete(TTripnoteScheduleRCity rcity) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteScheduleRCityService.deleteCondition(rcity);
		return vo;
	}
}
