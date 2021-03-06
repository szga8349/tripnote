package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BUserTripnoteMapper;
import com.lenovo.tripnote.entity.BUserTripnote;
import com.lenovo.tripnote.entity.vo.BUserTripnoteResultVo;
import com.lenovo.tripnote.entity.vo.BUserTripnoteSearchVo;
import com.lenovo.tripnote.service.BUserTripnoteService;
@Service()
public class BUserTripnoteServiceImpl implements BUserTripnoteService {
	@Resource
	private  BUserTripnoteMapper  bUserTripnoteMapper;

	@Override
	public int insert(BUserTripnote t) {
		// TODO Auto-generated method stub
		return bUserTripnoteMapper.insertSelective(t);
	}

	@Override
	public BUserTripnote update(BUserTripnote t) {
		this.bUserTripnoteMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BUserTripnote getByKey(Integer id) {
		
		return this.bUserTripnoteMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.bUserTripnoteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BUserTripnote t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BUserTripnoteResultVo> queryCondition(BUserTripnoteSearchVo search) {
		// TODO Auto-generated method stub
		 return bUserTripnoteMapper.queryCondition(search);
	}

	@Override
	public Integer queryCountCondition(BUserTripnoteSearchVo search) {
		// TODO Auto-generated method stub
		return bUserTripnoteMapper.queryCountCondition(search);
	}

}
