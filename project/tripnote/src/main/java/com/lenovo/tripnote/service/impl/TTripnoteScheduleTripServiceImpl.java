package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleTripMapper;
import com.lenovo.tripnote.entity.TTripnoteScheduleTripWithBLOBs;
import com.lenovo.tripnote.service.TTripnoteScheduleTripService;
@Service()
public class TTripnoteScheduleTripServiceImpl implements TTripnoteScheduleTripService{
	
   @Resource
    private TTripnoteScheduleTripMapper tTripnoteScheduleTripMapper;
	@Override
	public int insert(TTripnoteScheduleTripWithBLOBs t) {
		return tTripnoteScheduleTripMapper.insert(t);
	}

	@Override
	public TTripnoteScheduleTripWithBLOBs update(TTripnoteScheduleTripWithBLOBs t) {
		 this.tTripnoteScheduleTripMapper.updateByPrimaryKeyWithBLOBs(t);
		 return t;
	}

	@Override
	public TTripnoteScheduleTripWithBLOBs getByKey(Integer id) {
		return this.tTripnoteScheduleTripMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.tTripnoteScheduleTripMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripnoteScheduleTripWithBLOBs t) {
		
		return 0;
	}

}
