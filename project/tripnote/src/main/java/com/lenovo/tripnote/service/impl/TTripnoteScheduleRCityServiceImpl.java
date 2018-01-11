package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleRCityMapper;
import com.lenovo.tripnote.entity.TTripnoteScheduleRCity;
import com.lenovo.tripnote.entity.TTripnoteScheduleRCityExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleRCityExample.Criteria;
import com.lenovo.tripnote.service.TTripnoteScheduleRCityService;
@Service
public class TTripnoteScheduleRCityServiceImpl implements TTripnoteScheduleRCityService{
   @Resource
   private TTripnoteScheduleRCityMapper tTripnoteScheduleRCityMapper;
	@Override
	public int insert(TTripnoteScheduleRCity t) {
		
		return tTripnoteScheduleRCityMapper.insert(t);
	}

	@Override
	public TTripnoteScheduleRCity update(TTripnoteScheduleRCity t) {
		return null;
	}

	@Override
	public TTripnoteScheduleRCity getByKey(Integer id) {
		return null;
	}

	@Override
	public int deleteBykey(Integer id) {
		return this.tTripnoteScheduleRCityMapper.deleteByPrimaryKey(id);
	}


	/**
	 * 按照cityid和scheduleid进行删除
	 */
	@Override
	public int deleteCondition(TTripnoteScheduleRCity t) {
		TTripnoteScheduleRCityExample example = new TTripnoteScheduleRCityExample();
		Criteria c = example.createCriteria();
		c.andCityIdEqualTo(t.getCityId());
		c.andScheduleIdEqualTo(t.getScheduleId());
		return this.tTripnoteScheduleRCityMapper.deleteByExample(example );
	}

}
