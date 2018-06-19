package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BAccount;
import com.lenovo.tripnote.webchat.entity.BAccountExample;
import com.lenovo.tripnote.webchat.entity.BAccountExample.Criteria;
import com.lenovo.tripnote.webchat.entity.BLogin;
import com.lenovo.tripnote.webchat.entity.vo.BAccountDetailVo;
import com.lenovo.tripnote.webchat.mapper.BAccountMapper;
import com.lenovo.tripnote.webchat.mapper.BLoginMapper;
import com.lenovo.tripnote.webchat.service.BAccountService;


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
	@Transactional
	public int insert(BAccount account) {
		return bAccountMapper.insertSelective(account);
	}

	@Override
	@Transactional
	public BAccount update(BAccount account) {
		 bAccountMapper.updateByPrimaryKeySelective(account);
		 return account;
	}
	@Override
	@Transactional
	public int insert(BLogin record) {
		return bLoginMapper.insert(record);
	}
	
	@Override
	public BLogin getByAccountID(Long userId) {
		return this.bLoginMapper.getByAccountID(userId);
	}
	
	@Override
	@Transactional
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
	public BAccountDetailVo getDetail(Integer id) {
		// TODO Auto-generated method stub
		return this.bAccountMapper.getDetail(id);
	}

	@Override
	public BAccount getAutoToken(String token, Integer autoType) {
		BAccountExample example = new BAccountExample();
		Criteria cri = example.createCriteria();
		cri.andLoginTokenEqualTo(token);
		cri.andAutoLoginEqualTo(autoType);
		List<BAccount> list  = bAccountMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}


}
