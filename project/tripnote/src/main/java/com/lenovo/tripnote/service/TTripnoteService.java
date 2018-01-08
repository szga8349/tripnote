package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteVo;

public interface TTripnoteService extends IDbService<TTripNote>{
	
	public List<TTripNoteResultVo> queryCondition(TTripNote t);
	
	public List<TTripNoteResultVo> queryCondition(TTripNote t, RowBounds rowBound) ;
	
	public TTripNote insertTripNote(TTripNoteVo tripnoteVo,BAccount account);

	public TTripNote update(TTripNoteVo tripnoteVo, BAccount account, Integer valueOf);
	
	/**获取详情 此处要获取custmers
	 * @param id
	 * @return
	 */
	public TTripNoteDetailResultVo getDetailByKey(Integer id);
	
}
