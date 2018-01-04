package com.lenovo.tripnote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripNoteMapper;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.service.TTripnoteService;

@Service()
public class TripnoteServiceImpl implements TTripnoteService{
	
	@Resource
	private TTripNoteMapper tTripNoteMapper;

	@Override
	public int insert(TTripNote t) {
		return tTripNoteMapper.insert(t);
	}

	@Override
	public TTripNote update(TTripNote t) {
		 tTripNoteMapper.updateByPrimaryKey(t);
		 return t;
	}

	@Override
	public TTripNote getByKey(Integer id) {
		return tTripNoteMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBykey(Integer id) {
		return tTripNoteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCondition(TTripNote t) {
		return 0;
	}

}
