package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.webchat.entity.BStore;
import com.lenovo.tripnote.webchat.entity.BStoreExample;
import com.lenovo.tripnote.webchat.mapper.BStoreMapper;
import com.lenovo.tripnote.webchat.service.BStoreService;
@Service()
public class BStoreServiceImpl implements BStoreService{
    @Resource
    private BStoreMapper bStoreMapper;
	@Override
	public int insert(BStore t) {
		bStoreMapper.insertSelective(t);
		return t.getId();
	}

	@Override
	public BStore update(BStore t) {
		bStoreMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BStore getByKey(Integer id) {

		return this.bStoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.bStoreMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BStore> searchBStore(Integer userId) {
		BStoreExample example = new BStoreExample();
		example.createCriteria().andCreateUserIdEqualTo(userId);
		example.setOrderByClause(" id asc");
		return bStoreMapper.selectByExample(example);
	}

}
