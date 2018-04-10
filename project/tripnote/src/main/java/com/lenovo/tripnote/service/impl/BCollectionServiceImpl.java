package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BCollectionMapper;
import com.lenovo.tripnote.entity.BCollection;
import com.lenovo.tripnote.entity.vo.BCollectionResultVo;
import com.lenovo.tripnote.entity.vo.BCollectionSearchVo;
import com.lenovo.tripnote.service.BCollectionService;
@Service
public class BCollectionServiceImpl implements BCollectionService{
    @Resource
    private BCollectionMapper bCollectionMapper;
	@Override
	public int insert(BCollection t) {
		// TODO Auto-generated method stub
		return bCollectionMapper.insertSelective(t);
	}

	@Override
	public BCollection update(BCollection t) {
		bCollectionMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BCollection getByKey(Integer id) {
		// TODO Auto-generated method stub
		return bCollectionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return bCollectionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BCollection t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BCollectionResultVo> queryCondition(BCollectionSearchVo search) {
		// TODO Auto-generated method stub
		return bCollectionMapper.queryCondition(search);
	}

	@Override
	public Integer queryCountCondition(BCollectionSearchVo search) {
		// TODO Auto-generated method stub
		return bCollectionMapper.queryCountCondition(search);
	}

}
