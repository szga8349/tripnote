package com.lenovo.tripnote.service;

import com.lenovo.tripnote.entity.BHotelWithBLOBs;
import com.lenovo.tripnote.entity.BPoiWithBLOBs;

public interface JdbcService {
	
	public void addPoi(BPoiWithBLOBs poi) throws Exception;

	public void addHotel(BHotelWithBLOBs poi) throws Exception;
}
