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
import com.lenovo.tripnote.entity.TCustomerCatogry;
import com.lenovo.tripnote.entity.vo.TCustemAddVo;
import com.lenovo.tripnote.entity.vo.TCustomerCatogryResultVo;
import com.lenovo.tripnote.entity.vo.TCustomerCatogryVo;
import com.lenovo.tripnote.service.TCustomerCatogryService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**客户分组controller
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/customer/catogry")
public class TCustomerCatogryController {
	    @Resource
	    private TCustomerCatogryService tCustomerCatogryService;
		
		@RequestMapping(value = "/doAdd")
		public @ResponseBody ResultVo add(TCustomerCatogryVo tripnoteScheduleVo){
			Subject subject = SecurityUtils.getSubject();
			ResultVo vo = new ResultVo();
			BAccount account = (BAccount) subject.getPrincipal();
			List<TCustomerCatogry> result = tCustomerCatogryService.search(tripnoteScheduleVo,account, true);
			if(result!=null && !result.isEmpty()){//分组重复 直接返回true
				vo.setCode(Result.FAUL);
				vo.setMessage("分组已经存在");
				return vo;
			}
			vo.setCode(Result.SUCESSFUL);
			TCustomerCatogry schedule = new TCustomerCatogry();
			try {
				BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			schedule.setCreateUserId(account.getId());
			tCustomerCatogryService.insert(schedule);
			vo.setData(schedule.getId());
			return vo;
		}
		
		@RequestMapping(value = "/doDelete/{id}")
		public @ResponseBody ResultVo delete(@PathVariable String id){
			ResultVo vo = new ResultVo();
			vo.setCode(Result.SUCESSFUL);
			TCustomerCatogry t = new TCustomerCatogry();
			Subject subject = SecurityUtils.getSubject();
			BAccount account = (BAccount) subject.getPrincipal();
			t.setId(Integer.valueOf(id));
			t.setCreateUserId(account.getId());
			tCustomerCatogryService.deleteCondition(t);
			return vo;
		}
		
		@RequestMapping(value = "/doUpdate/{id}")
		public @ResponseBody ResultVo update(@PathVariable String id,TCustomerCatogryVo tripnoteScheduleVo) throws IllegalAccessException, InvocationTargetException{
			Subject subject = SecurityUtils.getSubject();
			ResultVo vo = new ResultVo();
			BAccount account = (BAccount) subject.getPrincipal();
			tripnoteScheduleVo.setId(Integer.valueOf(id));
			List<TCustomerCatogry> result = tCustomerCatogryService.search(tripnoteScheduleVo,account, true);
			if(result!=null && !result.isEmpty()){//分组重复 直接返回true
				vo.setCode(Result.FAUL);
				vo.setMessage("分组已经存在");
				return vo;
			}
			vo.setCode(Result.SUCESSFUL);
			TCustomerCatogry t = new TCustomerCatogry();
			BeanUtils.copyProperties(t, tripnoteScheduleVo);
			t.setId(Integer.valueOf(id));
			vo.setCode(Result.SUCESSFUL);
			tCustomerCatogryService.update(t);
			return vo;
		}

		@RequestMapping(value = "/doDetail/{id}")
		public @ResponseBody ResultVo getDetail(@PathVariable String id) throws IllegalAccessException, InvocationTargetException{
			ResultVo vo = new ResultVo();
			TCustomerCatogry result = tCustomerCatogryService.getByKey(Integer.valueOf(id));
			vo.setCode(Result.SUCESSFUL);
			vo.setData(result);
			return vo;
		}
		
		@RequestMapping(value = "/doSearch")
		public @ResponseBody ResultVo searchDetail(TCustomerCatogryVo search) throws IllegalAccessException, InvocationTargetException{
			ResultVo vo = new ResultVo();
			Subject subject = SecurityUtils.getSubject();
			BAccount account = (BAccount) subject.getPrincipal();
			List<TCustomerCatogry> result = tCustomerCatogryService.search(search,account, false);
			vo.setCode(Result.SUCESSFUL);
			vo.setData(result);
			return vo;
		}
		@RequestMapping(value = "/doStatistic")
		public @ResponseBody ResultVo statistics(TCustemAddVo searchVo) throws IllegalAccessException, InvocationTargetException{
			ResultVo vo = new ResultVo();
			Subject subject = SecurityUtils.getSubject();
			BAccount account = (BAccount) subject.getPrincipal();
			searchVo.setCreateUserId(account.getId());
			List<TCustomerCatogryResultVo> result = tCustomerCatogryService.statistics(searchVo);
			vo.setCode(Result.SUCESSFUL);
			vo.setData(result);
			return vo;
		}
}
