package com.lenovo.tripnote.webchat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import com.lenovo.tripnote.webchat.mapper.BProductCashflowMapper;
import com.lenovo.tripnote.webchat.service.BProductCashFlowService;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;
@Service()
public class BProductCashFlowServiceImpl implements BProductCashFlowService{
    @Resource
    private BProductCashflowMapper bProductCashflowMapper;

	@Override
	public int insert(BProductCashflow t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BProductCashflow update(BProductCashflow t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BProductCashflow getByKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultPageInfo searchShareProfit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultPageInfo searchProductProfit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float searchBalance(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultPageInfo searchCashFlow() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
