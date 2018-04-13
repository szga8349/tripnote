package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BAccountMapper;
import com.lenovo.tripnote.dao.BLoginMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BLogin;
import com.lenovo.tripnote.entity.vo.BAccountDetailVo;
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
		return bAccountMapper.insertSelective(account);
	}

	@Override
	public BAccount update(BAccount account) {
		 bAccountMapper.updateByPrimaryKeySelective(account);
		 return account;
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

	@Override
	public BAccount getByKey(Integer id) {
		return this.bAccountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCondition(BAccount t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BAccountDetailVo getDetail(Integer id) {
		// TODO Auto-generated method stub
		return this.bAccountMapper.getDetail(id);
	}


}
