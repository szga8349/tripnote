package com.lenovo.tripnote.webchat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.mapper.BProductOrderMapper;
import com.lenovo.tripnote.webchat.service.BProductOrderService;
@Service
public class BProductCollageServiceImpl implements BProductOrderService{
    @Resource
    private BProductOrderMapper bProductOrderMapper;
	@Override
	@Transactional
	public int insert(BProductOrder t) {
		return bProductOrderMapper.insertSelective(t);
	}

	@Override
	@Transactional
	public BProductOrder update(BProductOrder t) {
		this.bProductOrderMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProductOrder getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductOrderMapper.deleteByPrimaryKey(id);
	}

}
