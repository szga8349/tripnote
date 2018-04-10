package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
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
import com.lenovo.tripnote.entity.BCollection;
import com.lenovo.tripnote.entity.vo.BCollectionResultVo;
import com.lenovo.tripnote.entity.vo.BCollectionSearchVo;
import com.lenovo.tripnote.entity.vo.BCollectionVo;
import com.lenovo.tripnote.entity.vo.PageResultVo;
import com.lenovo.tripnote.service.BCollectionService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**用户收藏
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/user/collection")
public class BCollectionController {
	@Resource
	private BCollectionService bCollectionService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addBUserTripnote(BCollectionVo tripnoteScheduleVo){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BCollection schedule = new BCollection();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setCreateUserId(account.getId());
		bCollectionService.insert(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo updateBUserTripnote(BCollectionVo tripnoteScheduleVo,@PathVariable String id){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BCollection schedule = new BCollection();
		try {
			BeanUtils.copyProperties(schedule, tripnoteScheduleVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		BAccount account = (BAccount) subject.getPrincipal();
		schedule.setId(Integer.valueOf(id));
		schedule.setCreateTime(new Date());
		schedule.setCreateUserId(account.getId());
		bCollectionService.update(schedule);
		vo.setData(schedule.getId());
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo deleteBUserTripnote(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bCollectionService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(BCollectionSearchVo search) throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		vo.setCode(Result.SUCESSFUL);
		search.setUserId(account.getId());
		//从第一页开始
		if(search.getTitle()!=null){
			search.setTitle("%"+search.getTitle()+"%");
		}
		Integer offset = (search.getPageNo()-1<0?0:(search.getPageNo()-1))*search.getPageSize();
		search.setPageNo(offset);
		PageResultVo result = new PageResultVo();
		BeanUtils.copyProperties(result, search);
		List<BCollectionResultVo> t1 = bCollectionService.queryCondition(search);
		result.setData(t1);
		result.setTotal(bCollectionService.queryCountCondition(search));
		vo.setData(result);
		return vo;
	}
}
