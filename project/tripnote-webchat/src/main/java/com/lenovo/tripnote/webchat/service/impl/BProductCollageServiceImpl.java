package com.lenovo.tripnote.webchat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BProductCollage;
import com.lenovo.tripnote.webchat.mapper.BProductCollageMapper;
import com.lenovo.tripnote.webchat.service.BProductCollageService;
@Service
public class BProductCollageServiceImpl implements BProductCollageService{
    @Resource
    private BProductCollageMapper bProductOrderMapper;
	@Override
	@Transactional
	public int insert(BProductCollage t) {
		return bProductOrderMapper.insertSelective(t);
	}

	@Override
	@Transactional
	public BProductCollage update(BProductCollage t) {
		this.bProductOrderMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProductCollage getByKey(Integer id) {
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
