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
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.BAccountDetailVo;
import com.lenovo.tripnote.entity.vo.BExportVo;
import com.lenovo.tripnote.entity.vo.BHtml5Vo;
import com.lenovo.tripnote.entity.vo.PageResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteVo;
import com.lenovo.tripnote.service.BAccountService;
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
	@Resource
	private BAccountService bAccountService;
	
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
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		TTripNote t = new TTripNote();
		t.setId(Integer.valueOf(id));
		t.setCreateUserId(account.getId());
		tTripnoteService.deleteCondition(t);
		return vo;
	}

	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(TTripNoteSearchVo search) throws IllegalAccessException, InvocationTargetException {
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
		search.setType(1);
		PageResultVo result = new PageResultVo();
		BeanUtils.copyProperties(result, search);
		List<TTripNoteSearchResultVo> t1 = tTripnoteService.queryCondition(search);
		result.setData(t1);
		result.setTotal(tTripnoteService.queryCountCondition(search));
		vo.setData(result);
		return vo;
	}

	@RequestMapping(value = "/doUpdate/{id}")
	public @ResponseBody ResultVo update(TTripNoteVo tripnoteVo, @PathVariable String id)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		tripnoteVo.setType(1);
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
	
	/**导出模板
	 * @param id
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/doExport/{id}")
	public @ResponseBody ResultVo exportTemplate(@PathVariable String id,@RequestBody BExportVo exportVo)
			throws IllegalAccessException, InvocationTargetException {
		ResultVo vo = new ResultVo();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		exportVo.setCreateUserId(account.getId());
		tTripnoteService.insertToTemplate(Integer.valueOf(id), exportVo);
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/doExport/html5/{id}")
	public  @ResponseBody ResultVo exportHtml5(@PathVariable String id){
		//TTripNote tripnote = tTripnoteService.getByKey(Integer.valueOf(id));
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);;
		TTripNoteDetailResultVo detail = tTripnoteService.getPdfDetailByKey(Integer.valueOf(id));
		if(detail!=null){
			BAccountDetailVo account = bAccountService.getDetail(detail.getCreateUserId());
			BHtml5Vo html5 = new BHtml5Vo();
			html5.setAccount(account);
			html5.setTripnote(detail);
			vo.setData(html5);
		}
		return vo;
	}
}
