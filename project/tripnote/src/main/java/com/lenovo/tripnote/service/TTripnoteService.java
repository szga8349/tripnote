package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteVo;

public interface TTripnoteService extends IDbService<TTripNote>{
	
	public List<TTripNoteSearchResultVo> queryCondition(TTripNoteSearchVo t);
	
	public List<TTripNoteSearchResultVo> queryCondition(TTripNoteSearchVo t, RowBounds rowBound) ;
	
	public TTripNote insertTripNote(TTripNoteVo tripnoteVo,BAccount account);

	public TTripNote update(TTripNoteVo tripnoteVo, BAccount account, Integer valueOf);
	
	/**获取详情 此处要获取custmers
	 * @param id
	 * @return
	 */
	public TTripNoteDetailResultVo getDetailByKey(Integer id);
	
	
	public int queryCountCondition(TTripNoteSearchVo t);
	
	
	/**查询路书所有关联POI和交通的价格信息
	 * @param id
	 * @return
	 */
	public TTripNoteDetailResultVo getTravelQuotationByKey(Integer id);
	
}
