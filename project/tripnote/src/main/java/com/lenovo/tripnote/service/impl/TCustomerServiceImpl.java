package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TCustomerMapper;
import com.lenovo.tripnote.dao.TTripnoteRCustomerMapper;
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.TCustomerExample;
import com.lenovo.tripnote.entity.TTripnoteRCustomer;
import com.lenovo.tripnote.service.TCustomerService;

@Service()
public class TCustomerServiceImpl implements TCustomerService {
    @Resource
    private TCustomerMapper tCustomerMapper;
    @Resource
    private TTripnoteRCustomerMapper tTripnoteRCustomerMapper;
	@Override
	public int insert(TCustomer t) {
		return tCustomerMapper.insert(t);
	}

	@Override
	public TCustomer update(TCustomer t) {
	    tCustomerMapper.updateByPrimaryKeySelective(t);
	    return t;
	}

	@Override
	public TCustomer getByKey(Integer id) {
		return null;
	}

	@Override
	public int deleteBykey(Integer id) {
		return 0;
	}

	@Override
	public int deleteCondition(TCustomer t) {
		return 0;
	}

	@Override
	public List<TCustomer> selectCondition(TCustomer t) {
		TCustomerExample example = new TCustomerExample();
		TCustomerExample.Criteria cname = example.createCriteria().andNameEqualTo(t.getName());
		cname.andPhone1EqualTo(t.getPhone1());
		return tCustomerMapper.selectByExample(example);
	}

	@Override
	public int insertTripnoteRCustomer(Integer tripnoteId, Integer customerId) {
		TTripnoteRCustomer record = new TTripnoteRCustomer();
		record.setCustomerId(customerId);
		record.setTripnoteId(tripnoteId);
		return tTripnoteRCustomerMapper.insert(record);
	}
}
