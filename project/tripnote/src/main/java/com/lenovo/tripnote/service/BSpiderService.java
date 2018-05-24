package com.lenovo.tripnote.service;

import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.BPoi;

@SuppressWarnings("rawtypes")
public interface BSpiderService extends IDbService{
	
         public void updatePoi(BPoi poi);
         
         public void  updateBHotel(BHotel hotel);
}
