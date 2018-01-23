package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleTripMapper;
import com.lenovo.tripnote.entity.TTripnoteScheduleTrip;
import com.lenovo.tripnote.service.TTripnoteScheduleTripService;
@Service()
public class TTripnoteScheduleTripServiceImpl implements TTripnoteScheduleTripService{
	
   @Resource
    private TTripnoteScheduleTripMapper tTripnoteScheduleTripMapper;
	@Override
	public int insert(TTripnoteScheduleTrip t) {
		return tTripnoteScheduleTripMapper.insert(t);
	}

	@Override
	public TTripnoteScheduleTrip update(TTripnoteScheduleTrip t) {
		 this.tTripnoteScheduleTripMapper.updateByPrimaryKeyWithBLOBs(t);
		 return t;
	}

	@Override
	public TTripnoteScheduleTrip getByKey(Integer id) {
		return this.tTripnoteScheduleTripMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.tTripnoteScheduleTripMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripnoteScheduleTrip t) {
		
		return 0;
	}

}
