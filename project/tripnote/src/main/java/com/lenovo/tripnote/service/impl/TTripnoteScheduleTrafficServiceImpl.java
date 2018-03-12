package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleTrafficMapper;
import com.lenovo.tripnote.entity.TTripnoteScheduleTraffic;
import com.lenovo.tripnote.service.TTripnoteScheduleTrafficService;

@Service()
public class TTripnoteScheduleTrafficServiceImpl implements TTripnoteScheduleTrafficService{
	
	@Resource
	private TTripnoteScheduleTrafficMapper tTripnoteScheduleTrafficMapper;

	@Override
	public int insert(TTripnoteScheduleTraffic t) {
		return tTripnoteScheduleTrafficMapper.insertSelective(t);
	}

	@Override
	public TTripnoteScheduleTraffic update(TTripnoteScheduleTraffic t) {
		 this.tTripnoteScheduleTrafficMapper.updateByPrimaryKeySelective(t);
		 return t;
	}

	@Override
	public TTripnoteScheduleTraffic getByKey(Integer id) {

		return this.tTripnoteScheduleTrafficMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.tTripnoteScheduleTrafficMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripnoteScheduleTraffic t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
