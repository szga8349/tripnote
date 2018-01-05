package com.lenovo.tripnote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.lenovo.tripnote.dao.TTripNoteMapper;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripNoteExample;
import com.lenovo.tripnote.entity.vo.TTripNoteResultVo;
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

	@Override
	public List<TTripNoteResultVo> queryCondition(TTripNote t) {
		TTripNoteExample example = new TTripNoteExample();
		TTripNoteExample.Criteria cname = example.createCriteria().andTitleLike(t.getTitle());
		cname.andCreateUserIdEqualTo(t.getCreateUserId(),"t.create_user_id");
		return tTripNoteMapper.queryCondition(example);
	}

	@Override
	public List<TTripNoteResultVo> queryCondition(TTripNote t, RowBounds rowBound) {
		TTripNoteExample example = new TTripNoteExample();
		TTripNoteExample.Criteria cname = example.createCriteria().andTitleLike(t.getTitle());
		cname.andCreateUserIdEqualTo(t.getCreateUserId(),"t.create_user_id");
		return tTripNoteMapper.queryConditionAndPage(example, rowBound);
	}

}
