package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.webchat.entity.BStoreModule;
import com.lenovo.tripnote.webchat.entity.BStoreModuleExample;
import com.lenovo.tripnote.webchat.mapper.BStoreModuleMapper;
import com.lenovo.tripnote.webchat.service.BStoreModuleService;
@Service()
public class BStoreModuleServiceImpl implements BStoreModuleService{
    @Resource
    private BStoreModuleMapper bStoreModuleMapper;
	@Override
	public int insert(BStoreModule t) {
		bStoreModuleMapper.insertSelective(t);
		return t.getId();
	}

	@Override
	public BStoreModule update(BStoreModule t) {
		bStoreModuleMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BStoreModule getByKey(Integer id) {

		return this.bStoreModuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.bStoreModuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BStoreModule> searchBStoreModule(Integer storeId) {
		BStoreModuleExample example = new BStoreModuleExample();
		example.createCriteria().andStoreIdEqualTo(storeId);
		example.setOrderByClause(" index asc");
		return bStoreModuleMapper.selectByExample(example);
	}

}
