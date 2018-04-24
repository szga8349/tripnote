package com.lenovo.tripnote.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripnoteScheduleMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleRCityMapper;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteSchedule;
import com.lenovo.tripnote.entity.TTripnoteScheduleExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleRCity;
import com.lenovo.tripnote.entity.vo.BatchIdsVo;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleResultVo;
import com.lenovo.tripnote.service.TTripNoteScheduleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service()
public class TTripNoteScheduleServiceImpl implements TTripNoteScheduleService {
	@Resource
	private TTripnoteScheduleMapper tTripnoteScheduleMapper;
	@Resource
	private TTripnoteScheduleRCityMapper tTripnoteScheduleRCityMapper;

	@Override
	public int insert(TTripnoteSchedule t) {
		// TODO Auto-generated method stub
		return tTripnoteScheduleMapper.insertSelective(t);
	}

	@Override
	public TTripnoteSchedule update(TTripnoteSchedule t) {
		tTripnoteScheduleMapper.updateByPrimaryKeySelective(t);
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
		// 删除日程对应的交通信息
		tTripnoteScheduleMapper.deleteTraffic(t);
		// 删除日程对应的定制师笔记关联信息
		tTripnoteScheduleMapper.deleteScheduleRUsenote(t);
		// 删除日程对应的日程行程信息
		tTripnoteScheduleMapper.deleteScheduleTrip(t);
		// 删除日程对应的目的地关联信息
		tTripnoteScheduleMapper.deleteScheduleRCity(t);
		// 删除日程信息
		TTripnoteScheduleExample sexample = new TTripnoteScheduleExample();
		com.lenovo.tripnote.entity.TTripnoteScheduleExample.Criteria criteria1 = sexample.createCriteria();
		criteria1.andCreateUseridEqualTo(t.getCreateUserid());
		criteria1.andIdEqualTo(t.getId());
		return tTripnoteScheduleMapper.deleteByExample(sexample);
	}

	@Override
	public List<Integer> addRangeSchedule(Integer tripnoteId, Integer range, BAccount account) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < range.intValue(); i++) {
			TTripnoteSchedule shcedule = new TTripnoteSchedule();
			shcedule.setIndexdate(i + 1);
			// shcedule.setTitle("D");
			shcedule.setCreateUserid(account.getId());
			shcedule.setCreateuserName(account.getLoginName());
			shcedule.setTripnoteId(tripnoteId);
			tTripnoteScheduleMapper.insert(shcedule);
			result.add(shcedule.getId());
		}
		return result;
	}

	@Override
	public TTripNoteScheduleResultVo getDetailByKey(Integer scheduleId) {
		// TODO Auto-generated method stub
		return tTripnoteScheduleMapper.getDetailByKey(scheduleId);
	}

	@Override
	public int updateIndexdates(String indexdates) {
		JSONObject json = JSONObject.fromObject(indexdates);
		JSONArray arrary = json.getJSONArray("indexdates");
		int size = arrary.size();
		int count = 0;
		for (int i = 0; i < size; i++) {
			JSONObject custer = arrary.getJSONObject(i);
			TTripnoteSchedule t1 = new TTripnoteSchedule();
			t1.setId(custer.getInt("scheduleid"));
			t1.setIndexdate(custer.getInt("indexdate"));
			count += tTripnoteScheduleMapper.updateByPrimaryKeySelective(t1);
		}
		return count;
	}

	@Override
	public List<Integer> addIndexdates(Integer tripnoteId, String indexdates, BAccount user) {
		JSONObject json = JSONObject.fromObject(indexdates);
		JSONArray arrary = json.getJSONArray("indexdates");
		int size = arrary.size();
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			JSONObject custer = arrary.getJSONObject(i);
			TTripnoteSchedule t1 = new TTripnoteSchedule();
			t1.setTripnoteId(tripnoteId);
			t1.setCreateUserid(user.getId());
			t1.setCreateuserName(user.getLoginName());
			if (custer.containsKey("title")) {
				t1.setTitle(custer.getString("title"));
			}
			t1.setIndexdate(custer.getInt("indexdate"));
			tTripnoteScheduleMapper.insert(t1);
			if (custer.containsKey("cityId")) {//增加定制日程与城市间的关联关系
				TTripnoteScheduleRCity record = new TTripnoteScheduleRCity();
				record.setCityId(custer.getInt("cityId"));
				if(custer.containsKey("position"))
				    record.setPosition(custer.getInt("position"));
				else
				   record.setPosition(0);
				record.setScheduleId(t1.getId());
				tTripnoteScheduleRCityMapper.insert(record);
			}
			result.add(t1.getId());
		}
		return result;
	}

	@Override
	public List<Integer> deleteBatch(BatchIdsVo ids, BAccount account) {
		List<Integer> returnRows = new ArrayList<Integer>();
		if(ids.getIds()!=null)
		for(Integer i:ids.getIds()){
			TTripnoteSchedule  schedule = new TTripnoteSchedule();
			schedule.setId(i);
			schedule.setCreateUserid(account.getId());
			returnRows.add(deleteCondition(schedule));
		}
		return returnRows;
	}

}
