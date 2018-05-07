package com.lenovo.tripnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BHotelMapper;
import com.lenovo.tripnote.dao.BPoiMapper;
import com.lenovo.tripnote.entity.BHotelWithBLOBs;
import com.lenovo.tripnote.entity.BPoiWithBLOBs;

@Service()
public class JdbcServiceImpl implements JdbcService{
    @Resource
    private BPoiMapper bPoiMapper;
    @Resource
    private BHotelMapper bHotelMapper;
	@Override
	public void addPoi(BPoiWithBLOBs record) throws Exception{
		bPoiMapper.insertSelective(record);
	}

	@Override
	public void addHotel(BHotelWithBLOBs record) throws Exception{
		bHotelMapper.insertSelective(record);
	}

}
