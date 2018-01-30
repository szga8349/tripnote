package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.vo.BHotelSearchVo;

public interface BHotelService extends IDbService<BHotel>{
           public List<BHotel> searchHotels(BHotelSearchVo vo);
}
