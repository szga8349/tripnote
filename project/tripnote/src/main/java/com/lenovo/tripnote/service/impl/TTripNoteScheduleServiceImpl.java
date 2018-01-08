package com.lenovo.tripnote.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteSchedule;
import com.lenovo.tripnote.service.TTripNoteScheduleService;

@Service()
public class TTripNoteScheduleServiceImpl implements TTripNoteScheduleService {
    @Resource
    private TTripnoteScheduleMapper tTripnoteScheduleMapper;
	@Override
	public int insert(TTripnoteSchedule t) {
		// TODO Auto-generated method stub
		return tTripnoteScheduleMapper.insert(t);
	}

	@Override
	public TTripnoteSchedule update(TTripnoteSchedule t) {
		 tTripnoteScheduleMapper.updateByPrimaryKey(t);
		 return t;
	}

	@Override
	public TTripnoteSchedule getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.tTripnoteScheduleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		return this.tTripnoteScheduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripnoteSchedule t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> addRangeSchedule(Integer tripnoteId, Integer range, BAccount account) {
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<range.intValue();i++){
			TTripnoteSchedule shcedule = new TTripnoteSchedule();
			shcedule.setIndexdate(i+1);
			shcedule.setTitle("D");
			shcedule.setCreateUserid(account.getId());
			shcedule.setCreateuserName(account.getLoginName());
			shcedule.setTripnoteId(tripnoteId);
			tTripnoteScheduleMapper.insert(shcedule);
			result.add(shcedule.getId());
		}
		return result;
	}

	

}