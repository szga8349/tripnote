package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteSchedule;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleResultVo;

public interface TTripNoteScheduleService extends IDbService<TTripnoteSchedule> {
	public List<Integer> addRangeSchedule(Integer tripnoteId,Integer range,BAccount account);

	public TTripNoteScheduleResultVo getDetailByKey(Integer scheduleId);
	
	public int updateIndexdates(String indexdates);
	
	public List<Integer> addIndexdates(Integer tripnoteId,String indexdates,BAccount account);
	
}
