package com.lenovo.tripnote.webchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.webchat.entity.BProductCollection;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollectionResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollectionSearchVo;
import com.lenovo.tripnote.webchat.mapper.BProductCollectionMapper;
import com.lenovo.tripnote.webchat.service.BProductCollectionService;
@Service
public class BProductCollectionServiceImpl implements BProductCollectionService{
    @Resource
    private BProductCollectionMapper bProductCollectionMapper;
	@Override
	public int insert(BProductCollection t) {
		return bProductCollectionMapper.insertSelective(t);
	}

	@Override
	public BProductCollection update(BProductCollection t) {
		 bProductCollectionMapper.updateByPrimaryKey(t);
		 return t;
	}

	@Override
	public BProductCollection getByKey(Integer id) {
		return this.bProductCollectionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		return this.bProductCollectionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BProductCollectionResultVo> search(BProductCollectionSearchVo search) {
		
		return this.bProductCollectionMapper.search(search);
	}

}
