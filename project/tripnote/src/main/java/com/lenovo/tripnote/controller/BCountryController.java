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
import com.lenovo.tripnote.entity.BCountry;
import com.lenovo.tripnote.entity.vo.BCountryVo;
import com.lenovo.tripnote.service.BCountryService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**国家操作
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/country")
public class BCountryController {
	@Resource
	private BCountryService bCountryService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addTTripNote(BCountryVo tripnoteVo) throws IllegalAccessException, InvocationTargetException {
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BCountry t = new BCountry();
		BeanUtils.copyProperties(t, tripnoteVo);
		BAccount account = (BAccount) subject.getPrincipal();
		t.setStatus(1);
		t.setCreateUserId(account.getId());
		bCountryService.insert(t);
		vo.setData(t.getId());
		return vo;
	}

	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		bCountryService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	/**根据区域ID搜索国家 如果ID空 返回所有国家信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(BCountryVo bcountryVo) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(this.bCountryService.select(bcountryVo));
		return vo;
	}
	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(BCountryVo tripnoteVo, @PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BCountry t = new BCountry();
		BeanUtils.copyProperties(t, tripnoteVo);
		t.setId(Integer.valueOf(id));
		bCountryService.update(t);
		return vo;
	}
}
