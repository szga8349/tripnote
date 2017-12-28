package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BAccountMapper;
import com.lenovo.tripnote.dao.BLoginMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BLogin;
import com.lenovo.tripnote.service.BAccountService;

@Service()
public class BAccountServiceImpl implements BAccountService {
	@Resource
	private BAccountMapper bAccountMapper;
	
	@Resource
	private BLoginMapper bLoginMapper;

	@Override
	public BAccount getByUsernameOrPhone(String username) {
		return bAccountMapper.getByUsernameOrPhone(username);
	}
	
	@Override
	public int insert(BAccount account) {
		return bAccountMapper.insert(account);
	}

	@Override
	public int update(BAccount account) {
		return bAccountMapper.updateByPrimaryKeySelective(account);
	}
	@Override
	public int insert(BLogin record) {
		return bLoginMapper.insert(record);
	}
	
	@Override
	public BLogin getByAccountID(Long userId) {
		return this.bLoginMapper.getByAccountID(userId);
	}
	
	@Override
	public int update(BLogin userId) {
		 return this.bLoginMapper.updateByPrimaryKeySelective(userId);
	}


}
