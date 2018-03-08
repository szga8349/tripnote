package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BTraffic;
import com.lenovo.tripnote.entity.vo.BRentVo;
import com.lenovo.tripnote.entity.vo.BTrafficSearchVo;
import com.lenovo.tripnote.entity.vo.BTrafficVo;
import com.lenovo.tripnote.entity.vo.PageResultVo;
import com.lenovo.tripnote.service.BTrafficService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**城际交通操作
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/traffic")
public class BTrafficController {
	@Resource
	private BTrafficService bTrafficService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo add(BTrafficVo bpoiVo) throws IllegalAccessException, InvocationTargetException {
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BTraffic t = new BTraffic();
		BeanUtils.copyProperties(t, bpoiVo);
		BAccount account = (BAccount) subject.getPrincipal();
		t.setCreateUserId(account.getId());
		bTrafficService.insert(t);
		vo.setData(t.getId());
		return vo;
	}

	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bTrafficService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	/**
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(BTrafficSearchVo bpoiSearch) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		bpoiSearch.setCreateUserId(account.getId());
		Integer offset = (bpoiSearch.getPageNo()-1<0?0:(bpoiSearch.getPageNo()-1))*bpoiSearch.getPageSize();
		RowBounds rowBounds = new RowBounds(offset,bpoiSearch.getPageSize());
		
		PageResultVo result = new PageResultVo();
		try {
			BeanUtils.copyProperties(result, bpoiSearch);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		result.setTotal(this.bTrafficService.searchCount(bpoiSearch));
		result.setData(this.bTrafficService.selectAndPage(bpoiSearch,rowBounds));
		vo.setData(result);
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(BRentVo bpoiVo, @PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BTraffic t = new BTraffic();
		BeanUtils.copyProperties(t, bpoiVo);
		t.setId(Integer.valueOf(id));
		bTrafficService.update(t);
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo getDetail(@PathVariable String id) throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bTrafficService.getByKey(Integer.valueOf(id)));
		return vo;
	}
}
