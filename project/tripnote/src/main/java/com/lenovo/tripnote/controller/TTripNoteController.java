package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteVo;
import com.lenovo.tripnote.service.TCustomerService;
import com.lenovo.tripnote.service.TTripnoteService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

/**定制
 * @author shijy2
 *
 */
@Controller
@RequestMapping(value = "/tripnote")
public class TTripNoteController {
	@Resource
	private TTripnoteService tTripnoteService;
	@Resource
	private TCustomerService tCustomerService;

	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addTTripNote(TTripNoteVo tripnoteVo) {
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BAccount account = (BAccount) subject.getPrincipal();
		//设置定制类型
		tripnoteVo.setType(1);
		TTripNote t = tTripnoteService.insertTripNote(tripnoteVo, account);
		vo.setData(t.getId());
		return vo;
	}

	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteService.deleteBykey(Integer.valueOf(id));
		return vo;
	}

	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(TTripNoteSearchVo search) {
		ResultVo vo = new ResultVo();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		vo.setCode(Result.SUCESSFUL);
		search.setUserId(account.getId());
		//从第一页开始
		Integer offset = (search.getPageNo()-1<0?0:(search.getPageNo()-1))*search.getPageSize();
		RowBounds rowBounds = new RowBounds(offset,search.getPageSize());
		List<TTripNoteSearchResultVo> t1 = tTripnoteService.queryCondition(search,rowBounds);
		vo.setData(t1);
		return vo;
	}

	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(TTripNoteVo tripnoteVo, @PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		TTripNote t = new TTripNote();
		BeanUtils.copyProperties(t, tripnoteVo);
		t.setId(Integer.valueOf(id));
		tTripnoteService.update(tripnoteVo,account,Integer.valueOf(id));
		return vo;
	}
	@RequestMapping(value = "/doDetail/{id}")
	public @ResponseBody ResultVo getTTripNotePrimaryKey(@PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(tTripnoteService.getDetailByKey(Integer.valueOf(id)));
		return vo;
	}
}
