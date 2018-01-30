package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/price/doDetail/{id}")
	public @ResponseBody ResultVo getTTripNotePrimaryKey(@PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripnoteService.getTravelQuotationByKey(Integer.valueOf(id)));
		return vo;
	}
}
