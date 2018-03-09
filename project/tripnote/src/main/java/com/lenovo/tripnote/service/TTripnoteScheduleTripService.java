package com.lenovo.tripnote.service;

import com.lenovo.tripnote.entity.TTripnoteScheduleTrip;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripSortVo;

public interface TTripnoteScheduleTripService extends IDbService<TTripnoteScheduleTrip> {
	public int bachUpdateSort(TTripnoteScheduleTripSortVo vo);
}
