package com.lenovo.tripnote.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TCustomerMapper;
import com.lenovo.tripnote.dao.TTripnoteRCustomerMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.TCustomerExample;
import com.lenovo.tripnote.entity.TCustomerExample.Criteria;
import com.lenovo.tripnote.entity.TTripnoteRCustomer;
import com.lenovo.tripnote.entity.TTripnoteRCustomerExample;
import com.lenovo.tripnote.entity.vo.BatchIdsVo;
import com.lenovo.tripnote.service.TCustomerService;

@Service()
public class TCustomerServiceImpl implements TCustomerService {
	@Resource
	private TCustomerMapper tCustomerMapper;
	@Resource
	private TTripnoteRCustomerMapper tTripnoteRCustomerMapper;

	@Override
	public int insert(TCustomer t) {
		return tCustomerMapper.insertSelective(t);
	}

	@Override
	public TCustomer update(TCustomer t) {
		tCustomerMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public TCustomer getByKey(Integer id) {
		return tCustomerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		return tCustomerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TCustomer t) {
		TCustomerExample example = new TCustomerExample();
		Criteria cri = example.createCriteria();
		cri.andIdEqualTo(t.getId());
		cri.andCreateUserIdEqualTo(t.getCreateUserId());
		return this.tCustomerMapper.deleteByExample(example);
	}

	@Override
	public List<TCustomer> search(TCustomer search, BAccount account, boolean isexsit) {

		TCustomerExample example = new TCustomerExample();
		Criteria cri = example.createCriteria();
		// 设置查询当前用户的客户组
		cri.andCreateUserIdEqualTo(account.getId());
		if (search.getName() != null && !isexsit) {// 搜索的情况下 使用like
			cri.andNameLike("%" + search.getName() + "%");
		} else if (search.getName() != null && isexsit) {// 判断名称是否存在的情况下
															// 只能使用相等进行查询
			cri.andNameEqualTo(search.getName());
		}
		if (search.getPhone1() != null && !isexsit) {// 搜索的情况下 使用like
			cri.andPhone1Like("%" + search.getPhone1() + "%");
		} else if (search.getPhone1() != null && isexsit) {// 判断名称是否存在的情况下
															// 只能使用相等进行查询
			cri.andPhone1EqualTo(search.getPhone1());
		}
		if (search.getId() != null) {// 修改时候判断 名称不重复时调用
			cri.andIdNotEqualTo(search.getId());
		}
		if (search.getCatogryId() != null) {// 修改时候判断 名称不重复时调用
			cri.andCatogryIdEqualTo(search.getCatogryId());
		}
		if (search.getImportance() != null) {// 修改时候判断 名称不重复时调用
			cri.andImportanceEqualTo(search.getImportance());
		}
		return tCustomerMapper.selectByExample(example);
	}

	@Override
	public int insertTripnoteRCustomer(Integer tripnoteId, Integer customerId) {
		TTripnoteRCustomer record = new TTripnoteRCustomer();
		record.setCustomerId(customerId);
		record.setTripnoteId(tripnoteId);
		return tTripnoteRCustomerMapper.insert(record);
	}

	@Override
	public int deleteTripnoteRCustomer(Integer tripnoteId, Integer customerId) {
		TTripnoteRCustomerExample example = new TTripnoteRCustomerExample();
		com.lenovo.tripnote.entity.TTripnoteRCustomerExample.Criteria t = example.createCriteria();
		if (customerId != null) {
			t.andCustomerIdEqualTo(customerId);
		}
		t.andTripnoteIdEqualTo(tripnoteId);
		return tTripnoteRCustomerMapper.deleteByExample(example);
	}

	@Override
	public List<Integer> deleteBatch(BatchIdsVo ids, BAccount account) {
	    List<Integer> r = new ArrayList<Integer>();
		for(Integer inter:ids.getIds()){
			TCustomerExample example = new TCustomerExample();
			Criteria cri = example.createCriteria();
			cri.andIdEqualTo(inter);
			cri.andCreateUserIdEqualTo(account.getId());
			r.add(this.tCustomerMapper.deleteByExample(example));
		}
		return r;
	}

	@Override
	public List<Integer> addBatchToCatogry(BatchIdsVo ids, BAccount account,Integer catogryId) {
		List<Integer> r = new ArrayList<Integer>();
		for (Integer inter : ids.getIds()) {
			TCustomer customer = new TCustomer();
			customer.setCatogryId(catogryId);
			TCustomerExample example = new TCustomerExample();
			Criteria cri = example.createCriteria();
			cri.andIdEqualTo(inter);
			cri.andCreateUserIdEqualTo(account.getId());
			r.add(this.tCustomerMapper.updateByExampleSelective(customer, example));
		}
		return r;
	}

	@Override
	public boolean exsit(TCustomer search, BAccount account, boolean b) {
		if(StringUtils.isEmpty(search.getName()) && StringUtils.isEmpty(search.getPhone1())){
			return false;
		}
		TCustomerExample example = new TCustomerExample();
		Criteria cri = example.createCriteria();
		// 设置查询当前用户的客户组
		cri.andCreateUserIdEqualTo(account.getId());
		if (search.getName() != null ) {// 判断名称是否存在的情况下										// 只能使用相等进行查询
			cri.andNameEqualTo(search.getName());
		}
		if (search.getPhone1() != null) {// 判断名称是否存在的情况下// 只能使用相等进行查询
			cri.andPhone1EqualTo(search.getPhone1());
		}
		List<TCustomer> customers = tCustomerMapper.selectByExample(example);
		if(customers!=null && customers.size()>0)
			return true;
		return false;
	}
}
