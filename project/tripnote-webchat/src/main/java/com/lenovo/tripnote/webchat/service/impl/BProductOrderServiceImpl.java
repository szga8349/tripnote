package com.lenovo.tripnote.webchat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BProductCollage;
import com.lenovo.tripnote.webchat.mapper.BProductCollageMapper;
import com.lenovo.tripnote.webchat.service.BProductCollageService;
@Service
public class BProductOrderServiceImpl implements BProductCollageService{
    @Resource
    private BProductCollageMapper bProductCollageMapper;
	@Override
	@Transactional
	public int insert(BProductCollage t) {
		return bProductCollageMapper.insertSelective(t);
	}

	@Override
	@Transactional
	public BProductCollage update(BProductCollage t) {
		this.bProductCollageMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProductCollage getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductCollageMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductCollageMapper.deleteByPrimaryKey(id);
	}

}
