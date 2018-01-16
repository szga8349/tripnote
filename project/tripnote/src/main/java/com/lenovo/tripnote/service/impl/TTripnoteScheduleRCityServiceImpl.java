package com.lenovo.tripnote.service.impl;

import java.util.Date;
import java.util.List;

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
	@Override
	public int insertFrist(TTripnoteScheduleRCity t) {
		TTripnoteScheduleRCityExample example = new TTripnoteScheduleRCityExample();
		Criteria c = example.createCriteria();
		c.andCityIdEqualTo(t.getCityId());
		c.andScheduleIdEqualTo(t.getScheduleId());
		example.setOrderByClause("create_time asc");
		List<TTripnoteScheduleRCity>  citys = this.tTripnoteScheduleRCityMapper.selectByExample(example);
		 if(citys!=null && citys.size()>0){//然后有数据 取最前面一条数据的时间 再减去一分钟
			Date date = citys.get(0).getCreateTime();
			date.setMinutes(date.getMinutes()-1);
			t.setCreateTime(date);
		 }
		return tTripnoteScheduleRCityMapper.insert(t);
	}

}
