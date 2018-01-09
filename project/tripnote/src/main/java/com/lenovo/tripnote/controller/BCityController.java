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
import com.lenovo.tripnote.entity.BCity;
import com.lenovo.tripnote.entity.vo.BCityVo;
import com.lenovo.tripnote.service.BCityService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**城市操作
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/city")
public class BCityController {
	@Resource
	private BCityService bCityService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo add(BCityVo tripnoteVo) throws IllegalAccessException, InvocationTargetException {
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BCity t = new BCity();
		BeanUtils.copyProperties(t, tripnoteVo);
		BAccount account = (BAccount) subject.getPrincipal();
		t.setStatus(1);
		t.setCreateUserId(account.getId());
		bCityService.insert(t);
		vo.setData(t.getId());
		return vo;
	}

	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bCityService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	/**根据区域ID 国家ID搜索城市  如果查询条件为空 返回所有国家信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(BCityVo bcountryVo) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Integer offset = (bcountryVo.getPageNo()-1<0?0:(bcountryVo.getPageNo()-1))*bcountryVo.getPageSize();
		RowBounds rowBounds = new RowBounds(offset,bcountryVo.getPageSize());
		vo.setData(this.bCityService.selectAndPage(bcountryVo,rowBounds));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(BCityVo tripnoteVo, @PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BCity t = new BCity();
		BeanUtils.copyProperties(t, tripnoteVo);
		t.setId(Integer.valueOf(id));
		bCityService.update(t);
		return vo;
	}
}
