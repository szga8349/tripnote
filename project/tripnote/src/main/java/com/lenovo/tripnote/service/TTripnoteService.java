package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.BExportVo;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteTravelResultVo;
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
	
	
	/**根据客户id查询的总数
	 * @param t
	 * @return
	 */
	public int queryCountByCustomer(TTripNoteSearchVo id);
	
	
	public List<TTripNoteSearchResultVo> queryByCustomer(TTripNoteSearchVo t);
	
	
	/**查询路书所有关联POI和交通的价格信息
	 * @param id
	 * @return
	 */
	public TTripNoteDetailResultVo getTravelQuotationByKey(Integer id);
	
	
	/**行程报价按照类型归类
	 * @param id
	 * @return
	 */
	public TTripNoteTravelResultVo getTravelQuotationGroupByTypeByKey(Integer id);
	
	
	/**导出到模板
	 * @param tripnoteId
	 * @param exportVo
	 */
	public void insertToTemplate(Integer tripnoteId,BExportVo exportVo);
	
	/**导入到定制
	 * @param tripnoteId
	 * @param exportVo
	 */
	public void insertToTripnote(Integer tripnoteId,BExportVo exportVo);
	

	TTripNoteDetailResultVo getPdfDetailByKey(Integer id);
	
}
