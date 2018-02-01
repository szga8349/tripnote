package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteScheduleHotel;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripHotelAddVo;

public interface TTripnoteScheduleHotelService extends IDbService<TTripnoteScheduleHotel>{
	
	/**批量增加酒店和日程安排的关系
	 * @param t
	 * @return
	 */
	public List<Integer> batchInsert(TTripnoteScheduleTripHotelAddVo t,BAccount account);
}
