package com.lenovo.tripnote.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BHotelMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleHotelMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.TTripnoteScheduleHotel;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleHotelAddVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripHotelAddVo;
import com.lenovo.tripnote.service.TTripnoteScheduleHotelService;

@Service
public class TTripnoteScheduleHotelServiceImpl implements TTripnoteScheduleHotelService{
   @Resource
   private TTripnoteScheduleHotelMapper tTripnoteScheduleHotelMapper;
   @Resource
   private BHotelMapper bHotelMapper;
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

	@Override
	public List<Integer> batchInsert(TTripnoteScheduleTripHotelAddVo t, BAccount account) {
		List<Integer> ids = new ArrayList<Integer>();
		if(t.getScheduleHotels()!=null){
			for(TTripnoteScheduleHotelAddVo addVo:t.getScheduleHotels()){
				BHotel hotel = bHotelMapper.selectByPrimaryKey(addVo.getSourceId());
				TTripnoteScheduleHotel scheduleHotel = new TTripnoteScheduleHotel();
				try {
					BeanUtils.copyProperties(scheduleHotel, hotel);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				scheduleHotel.setId(null);
				scheduleHotel.setCheckInTime(t.getCheckIn());
				scheduleHotel.setCheckOuTime(t.getCheckOut());
				scheduleHotel.setScheduleId(addVo.getScheduleId());
				scheduleHotel.setCreateUserId(account.getId());
				scheduleHotel.setCheckInType(addVo.getCheckInType());
				scheduleHotel.setHotelId(addVo.getSourceId());
				tTripnoteScheduleHotelMapper.insert(scheduleHotel);
				ids.add(scheduleHotel.getId());
			}
		}
		return ids;
	}

}
