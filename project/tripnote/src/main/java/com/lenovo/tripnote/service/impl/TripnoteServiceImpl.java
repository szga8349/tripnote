package com.lenovo.tripnote.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripNoteMapper;
import com.lenovo.tripnote.dao.TTripnoteRCustomerMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripNoteExample;
import com.lenovo.tripnote.entity.TTripNoteExample.Criteria;
import com.lenovo.tripnote.entity.TTripnoteRCustomerExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleExample;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteVo;
import com.lenovo.tripnote.service.TCustomerService;
import com.lenovo.tripnote.service.TTripnoteService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service()
public class TripnoteServiceImpl implements TTripnoteService{
	@Resource
	private TCustomerService tCustomerService;
	@Resource
	private TTripNoteMapper tTripNoteMapper;
	@Resource
	private TTripnoteScheduleMapper tTripnoteScheduleMapper;
	
	@Resource
	private TTripnoteRCustomerMapper tTripnoteRCustomerMapper;

	@Override
	public int insert(TTripNote t) {
		return tTripNoteMapper.insert(t);
	}

	@Override
	public TTripNote update(TTripNote t) {
		 tTripNoteMapper.updateByPrimaryKeySelective(t);
		 return t;
	}

	@Override
	public TTripNote getByKey(Integer id) {
		return tTripNoteMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		//需要删除想关联的表信息
		return tTripNoteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripNote t) {
		//删除关联的客户关系
		TTripnoteRCustomerExample rexample = new TTripnoteRCustomerExample();
		com.lenovo.tripnote.entity.TTripnoteRCustomerExample.Criteria cr = rexample.createCriteria();
		cr.andTripnoteIdEqualTo(t.getId());
		tTripnoteRCustomerMapper.deleteByExample(rexample);
		//删除定制下关联所有日程对应的交通信息
		tTripNoteMapper.deleteTraffic(t);
		//删除定制下关联所有日程对应的定制师笔记关联信息
	    tTripNoteMapper.deleteScheduleRUsenote(t);
		//删除定制下关联所有日程对应的日程行程信息
		tTripNoteMapper.deleteScheduleTrip(t);
		//删除定制下关联所有日程对应的目的地关联信息
		tTripNoteMapper.deleteScheduleRCity(t);
		//删除关联的日程信息
		TTripnoteScheduleExample sexample = new TTripnoteScheduleExample();
		com.lenovo.tripnote.entity.TTripnoteScheduleExample.Criteria criteria1 = sexample.createCriteria();
		criteria1.andCreateUseridEqualTo(t.getCreateUserId());
		criteria1.andTripnoteIdEqualTo(t.getId());
		tTripnoteScheduleMapper.deleteByExample(sexample);
		//删除定制信息
		TTripNoteExample example = new TTripNoteExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(t.getId());
		criteria.andCreateUserIdEqualTo(t.getCreateUserId());
		return tTripNoteMapper.deleteByExample(example );
	}

	@Override
	public List<TTripNoteSearchResultVo> queryCondition(TTripNoteSearchVo t) {
		
		return tTripNoteMapper.queryCondition(t);
	}

	@Override
	public List<TTripNoteSearchResultVo> queryCondition(TTripNoteSearchVo t, RowBounds rowBound) {
	
		return tTripNoteMapper.queryConditionAndPage(t, rowBound);
	}
	public TTripNote insertTripNote(TTripNoteVo tripnoteVo,BAccount account){
		TTripNote t = new TTripNote();
		try {
			BeanUtils.copyProperties(t, tripnoteVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		t.setCreateUserId(account.getCreateUserId());
		t.setCreateTime(new Date());
		t.setCreateUserId(account.getId());
		this.insert(t);
	    if(tripnoteVo.getCustomers()!=null){//关联客户信息
	    	JSONObject json = JSONObject.fromObject(tripnoteVo.getCustomers());
	    	JSONArray arrary = json.getJSONArray("customer");
	    	int size = arrary.size();
	    	System.out.println(json);
	    	for(int i=0;i<size;i++){
	    		JSONObject custer = arrary.getJSONObject(i);
	    		TCustomer t1 = new TCustomer();
		    	t1.setName(custer.getString("name"));
		    	t1.setPhone1(custer.getString("phone"));
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
	    return t;
	}

	@Override
	public TTripNote update(TTripNoteVo tripnoteVo, BAccount account, Integer id) {
		TTripNote t = new TTripNote();
		try {
			BeanUtils.copyProperties(t, tripnoteVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		t.setCreateUserId(account.getCreateUserId());
		t.setCreateTime(new Date());
		t.setCreateUserId(account.getId());
		t.setId(id);
		if(t.getStatus()==1){//发布状态
			t.setPublishTime(new Date());
		}
		this.update(t);
	    if(tripnoteVo.getCustomers()!=null){//关联客户信息
	    	JSONObject json = JSONObject.fromObject(tripnoteVo.getCustomers());
	    	JSONArray arrary = json.getJSONArray("customer");
	    	int size = arrary.size();
	    	for(int i=0;i<size;i++){
	    		JSONObject custer = arrary.getJSONObject(i);
	    		TCustomer t1 = new TCustomer();
		    	t1.setName(custer.getString("name"));
		    	t1.setPhone1(custer.getString("phone"));
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
	    return t;
	}

	@Override
	public TTripNoteDetailResultVo getDetailByKey(Integer id) {
		
		return tTripNoteMapper.getDetailByKey(id);
	}

	@Override
	public int queryCountCondition(TTripNoteSearchVo t) {
		TTripNoteExample example = new TTripNoteExample();
		Criteria criteria = example.createCriteria();
		if (t.getUserId() != null) {
			criteria.andCreateUserIdEqualTo(t.getUserId());
		}
		if (t.getTitle()!=null) {
			criteria.andTitleLike(t.getTitle());
		}
		if (t.getType() != null) {
			criteria.andTypeEqualTo(t.getType());
		}
		return this.tTripNoteMapper.countByExample(example);
	}

	@Override
	public TTripNoteDetailResultVo getTravelQuotationByKey(Integer id) {
		// TODO Auto-generated method stub
		return tTripNoteMapper.getTravelQuotationByKey(id);
	}

	
}
