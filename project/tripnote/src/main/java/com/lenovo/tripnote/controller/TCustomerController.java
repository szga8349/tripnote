package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.vo.BatchIdsVo;
import com.lenovo.tripnote.entity.vo.PageResultVo;
import com.lenovo.tripnote.entity.vo.TCustemAddVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.service.TCustomerService;
import com.lenovo.tripnote.service.TTripnoteService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**
 * 客户controller
 * 
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/customer")
public class TCustomerController {
	@Resource
	private TCustomerService tCustomerService;
	@Resource
	private TTripnoteService tTripnoteService;

	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo add(TCustemAddVo tripnoteScheduleVo) {
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		BAccount account = (BAccount) subject.getPrincipal();
		List<TCustomer> result = tCustomerService.search(tripnoteScheduleVo, account, true);
		if (result != null && !result.isEmpty()) {// 分组重复 直接返回true
			vo.setCode(Result.FAUL);
			vo.setMessage("分组已经存在");
			return vo;
		}
		vo.setCode(Result.SUCESSFUL);
		TCustomer schedule = new TCustomer();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		schedule.setStatus(1);
		schedule.setCreateUserId(account.getId());
		tCustomerService.insert(schedule);
		vo.setData(schedule.getId());
		return vo;
	}

	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		TCustomer t = new TCustomer();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		t.setId(Integer.valueOf(id));
		t.setCreateUserId(account.getId());
		tCustomerService.deleteCondition(t);
		return vo;
	}

	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(@PathVariable String id, TCustemAddVo tripnoteScheduleVo)
			throws IllegalAccessException, InvocationTargetException {
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		BAccount account = (BAccount) subject.getPrincipal();
		tripnoteScheduleVo.setId(Integer.valueOf(id));
		if (tCustomerService.exsit(tripnoteScheduleVo, account, true)) {// 分组重复 直接返回true
			vo.setCode(Result.FAUL);
			vo.setMessage("客户已经存在");
			return vo;
		}
		vo.setCode(Result.SUCESSFUL);
		TCustomer t = new TCustomer();
		BeanUtils.copyProperties(t, tripnoteScheduleVo);
		t.setId(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		tCustomerService.update(t);
		return vo;
	}

	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo getDetail(@PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		TCustomer result = tCustomerService.getByKey(Integer.valueOf(id));
		vo.setCode(Result.SUCESSFUL);
		vo.setData(result);
		return vo;
	}

	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo searchDetail(TCustemAddVo search)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();

		List<TCustomer> result = tCustomerService.search(search, account, false);
		vo.setCode(Result.SUCESSFUL);
		vo.setData(result);
		return vo;
	}
	@RequestMapping(value = "/batch/doDelete")
	public @ResponseBody ResultVo batchDelete(@RequestBody BatchIdsVo ids) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		tCustomerService.deleteBatch(ids,account);
	    return vo;
	}
	/**批处理将客户放到分组中
	 * @param ids
	 * @param catogryId
	 * @return
	 */
	@RequestMapping(value = "/batch/doAdd/{catogryId}")
	public @ResponseBody ResultVo batchToCatogry(@RequestBody BatchIdsVo ids,@PathVariable String catogryId) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		tCustomerService.addBatchToCatogry(ids, account, Integer.valueOf(catogryId));
	    return vo;
	}
	
	/**客户关联的定制信息
	 * @param search
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/tripnote/{id}")
	public @ResponseBody ResultVo search(TTripNoteSearchVo search,@PathVariable String id) throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		vo.setCode(Result.SUCESSFUL);
		search.setUserId(account.getId());
		Integer offset = (search.getPageNo()-1<0?0:(search.getPageNo()-1))*search.getPageSize();
		search.setPageNo(offset);
		search.setType(1);
		PageResultVo result = new PageResultVo();
		BeanUtils.copyProperties(result, search);
		search.setCustomerId(Integer.valueOf(id));
		List<TTripNoteSearchResultVo> t1 = tTripnoteService.queryByCustomer(search);
		result.setData(t1);
		result.setTotal(tTripnoteService.queryCountByCustomer(search));
		vo.setData(result);
		return vo;
	}

}
