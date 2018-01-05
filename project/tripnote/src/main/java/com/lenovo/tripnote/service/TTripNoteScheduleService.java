package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteSchedule;

public interface TTripNoteScheduleService extends IDbService<TTripnoteSchedule> {
	public List<Integer> addRangeSchedule(Integer tripnoteId,Integer range,BAccount account);
}
