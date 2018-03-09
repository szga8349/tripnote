package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.BRentMapper;
import com.lenovo.tripnote.dao.TTripnoteScheduleRRentMapper;
import com.lenovo.tripnote.entity.BRent;
import com.lenovo.tripnote.entity.BRentExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleRRent;
import com.lenovo.tripnote.entity.TTripnoteScheduleRRentExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleRRentExample.Criteria;
import com.lenovo.tripnote.entity.vo.BRentSearchVo;
import com.lenovo.tripnote.service.BRentService;
@Service
public class BRentServiceImpl implements BRentService {
    @Resource
    private BRentMapper bRentMapper;
    @Resource
    private TTripnoteScheduleRRentMapper tTripnoteScheduleRRentMapper;
	@Override
	public int insert(BRent t) {
		// TODO Auto-generated method stub
		return bRentMapper.insertSelective(t);
	}

	@Override
	public BRent update(BRent t) {
		bRentMapper.updateByPrimaryKeySelective(t);
		return t;
	}

	@Override
	public BRent getByKey(Integer id) {
		// TODO Auto-generated method stub
		return this.bRentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		
		return this.bRentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(BRent t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer searchCount(BRentSearchVo vo) {
		//暂时不加条件进行全搜索
		BRentExample example = new BRentExample();
		return this.bRentMapper.countByExample(example);
	}

	@Override
	public List<BRent> selectAndPage(BRentSearchVo vo, RowBounds row) {
		BRentExample example = new BRentExample();
		// TODO Auto-generated method stub
		return this.bRentMapper.selectByExampleAndPage(example, row);
	}

	@Override
	public Integer insertRentToSchedule(Integer rentid, Integer scheduleId) {
		TTripnoteScheduleRRent record = new TTripnoteScheduleRRent();
		record.setRentid(rentid);
		record.setScheduleId(scheduleId);
		return tTripnoteScheduleRRentMapper.insertSelective(record);
	}

	@Override
	public Integer deleteRentToSchedule(Integer rentId, Integer scheduleId) {
		TTripnoteScheduleRRentExample example = new TTripnoteScheduleRRentExample();
		Criteria cri = example.createCriteria();
		cri.andRentidEqualTo(rentId);
		cri.andScheduleIdEqualTo(scheduleId);
		return tTripnoteScheduleRRentMapper.deleteByExample(example);
	}

}
