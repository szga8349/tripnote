package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.TTripNoteResultVo;

public interface TTripnoteService extends IDbService<TTripNote>{
	
	public List<TTripNoteResultVo> queryCondition(TTripNote t);
	
	public List<TTripNoteResultVo> queryCondition(TTripNote t, RowBounds rowBound) ;
	
}
