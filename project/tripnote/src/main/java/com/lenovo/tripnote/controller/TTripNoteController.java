package com.lenovo.tripnote.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
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
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.TCustemVo;
import com.lenovo.tripnote.entity.vo.TTripNoteResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteVo;
import com.lenovo.tripnote.service.TTripnoteService;
import com.lenovo.tripnote.service.TCustomerService;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

@Controller
@RequestMapping(value = "/tripnote")
public class TTripNoteController {
	@Resource
	private TTripnoteService tTripnoteService;
	@Resource
	private TCustomerService tCustomerService;
	@RequestMapping(value = "/doAdd")
	public @ResponseBody ResultVo addTTripNote(TTripNoteVo tripnoteVo){
		Subject subject = SecurityUtils.getSubject();
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		BAccount account = (BAccount) subject.getPrincipal();
		TTripNote t = new TTripNote();
		try {
			BeanUtils.copyProperties(t, tripnoteVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		t.setCreateUserId(account.getCreateUserId());
		t.setCreateTime(new Date());
		t.setCreateUserId(account.getId());
		tTripnoteService.insert(t);
	    if(tripnoteVo.getCustomers()!=null && tripnoteVo.getCustomers().size()>0){//关联客户信息
	    	for(TCustemVo vo1:tripnoteVo.getCustomers()){
	    		TCustomer t1 = new TCustomer();
		    	t1.setName(vo1.getName());
		    	t1.setPhone1(vo1.getPhone());
		    	List<TCustomer> customer = tCustomerService.selectCondition(t1); 
		    	if(customer!=null && !customer.isEmpty()){
		    		tCustomerService.insertTripnoteRCustomer(t.getId(), customer.get(0).getId());
		    	}else{
		    		t1.setStatus(1);
		    		t1.setCreateUserId(account.getId());
		    		tCustomerService.insert(t1);
		    		tCustomerService.insertTripnoteRCustomer(t.getId(), t1.getId());
		    	}
	    	}
		}
		vo.setData(t.getId());
		return vo;
	}
	@RequestMapping(value = "/doDelete/{id}")
	public @ResponseBody ResultVo delete(@PathVariable String id){
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		tTripnoteService.deleteBykey(Integer.valueOf(id));
		return vo;
	}
	@RequestMapping(value = "/doSearch")
	public @ResponseBody ResultVo search(TTripNoteSearchVo search){
		ResultVo vo = new ResultVo();
		Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		vo.setCode(Result.SUCESSFUL);
		TTripNote t = new TTripNote();
		t.setCreateUserId(account.getId());
		t.setTitle(search.getTitle());
		List<TTripNoteResultVo> t2 = tTripnoteService.queryCondition(t,new RowBounds(0, 10));
		List<TTripNoteResultVo> t1 = tTripnoteService.queryCondition(t);
		vo.setData(t1);
		return vo;
	}
}
