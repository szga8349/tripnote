package com.lenovo.tripnote.webchat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.tripnote.webchat.entity.BProductImage;
import com.lenovo.tripnote.webchat.mapper.BProductImageMapper;
import com.lenovo.tripnote.webchat.service.BProductImageService;
@Service
public class BProductImageServiceImpl implements BProductImageService{
    @Resource
    private BProductImageMapper bProductImageMapper;
	@Override
	@Transactional
	public int insert(BProductImage t) {
		return bProductImageMapper.insertSelective(t);
	}

	@Override
	public BProductImage update(BProductImage t) {
		this.bProductImageMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProductImage getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductImageMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return this.bProductImageMapper.deleteByPrimaryKey(id);
	}

}
