package com.lenovo.tripnote.webchat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.webchat.entity.BProductShare;
import com.lenovo.tripnote.webchat.mapper.BProductShareMapper;
import com.lenovo.tripnote.webchat.service.BProductShareService;

@Service
public class BProductShareServiceImpl implements BProductShareService{
    @Resource
    private BProductShareMapper bProductShareMapper;
	@Override
	public int insert(BProductShare t) {
		// TODO Auto-generated method stub
		return bProductShareMapper.insertSelective(t);
	}

	@Override
	public BProductShare update(BProductShare t) {
		bProductShareMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BProductShare getByKey(Integer id) {
		// TODO Auto-generated method stub
		return bProductShareMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return bProductShareMapper.deleteByPrimaryKey(id);
	}

}
