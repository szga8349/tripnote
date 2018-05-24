package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BCityMapper;
import com.lenovo.tripnote.dao.BCountryMapper;
import com.lenovo.tripnote.dao.BHotelMapper;
import com.lenovo.tripnote.dao.BPoiMapper;
import com.lenovo.tripnote.entity.BCity;
import com.lenovo.tripnote.entity.BCityExample;
import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.BPoi;
import com.lenovo.tripnote.service.BSpiderService;
@Service
public class BSpiderServiceImpl implements BSpiderService{
	@Resource
	private BHotelMapper bHotelMapper;
	@Resource
	private BPoiMapper bPoiMapper;
	@Resource
	private BCityMapper bCityMapper;
	@Resource
	private BCountryMapper bCountryMapper;
	@Override
	public int insert(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object update(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getByKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBykey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCondition(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updatePoi(BPoi poi) {
		if(poi.getCityname()!=null){
			poi.setCityId(getCityId(poi.getCityname(),poi.getLon(),poi.getLat()));
		}
		bPoiMapper.updateByPrimaryKeySelective(poi);
	}

	@Override
	public void updateBHotel(BHotel hotel) {
	     if(hotel.getCityName()!=null){
	    	 hotel.setCityId(getCityId(hotel.getCityName(),hotel.getLon(),hotel.getLat()));
		}
		bHotelMapper.updateByPrimaryKeySelective(hotel);
	}
	private int getCityId(String cityName,Double lon,Double lat){
		BCityExample example = new BCityExample();
		example.createCriteria().andNameCnEqualTo(cityName);
		List<BCity> list = bCityMapper.selectByExample(example);
		if(list!=null && !list.isEmpty()){
			return list.get(0).getId();
		}
		BCity bcity = new BCity();
		bcity.setNameCn(cityName);
		bcity.setStatus(1);
		bcity.setLon(lon);
		bcity.setLat(lat);
		bcity.setIsHot(0);
		bCityMapper.insertSelective(bcity);
		return bcity.getId();
	}

}
