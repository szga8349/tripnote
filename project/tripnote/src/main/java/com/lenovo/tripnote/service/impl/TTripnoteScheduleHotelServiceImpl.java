package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleHotelMapper;
import com.lenovo.tripnote.entity.TTripnoteScheduleHotel;
import com.lenovo.tripnote.service.TTripnoteScheduleHotelService;

@Service
public class TTripnoteScheduleHotelServiceImpl implements TTripnoteScheduleHotelService{
   @Resource
   private TTripnoteScheduleHotelMapper tTripnoteScheduleHotelMapper;
	@Override
	public int insert(TTripnoteScheduleHotel t) {
		// TODO Auto-generated method stub
		return tTripnoteScheduleHotelMapper.insert(t);
	}

	@Override
	public TTripnoteScheduleHotel update(TTripnoteScheduleHotel t) {
		tTripnoteScheduleHotelMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public TTripnoteScheduleHotel getByKey(Integer id) {
		// TODO Auto-generated method stub
		return tTripnoteScheduleHotelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		return tTripnoteScheduleHotelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripnoteScheduleHotel t) {
		
		return 0;
	}

}
