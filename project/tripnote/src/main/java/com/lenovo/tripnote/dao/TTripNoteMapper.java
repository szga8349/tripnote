package com.lenovo.tripnote.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripNoteExample;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteSearchVo;
import com.lenovo.tripnote.entity.vo.TTripNoteTravelResultVo;

public interface TTripNoteMapper {
    int countByExample(TTripNoteExample example);

    int deleteByExample(TTripNoteExample example);

    int updateByExampleSelective(@Param("record") TTripNote record, @Param("example") TTripNoteExample example);

    int updateByExample(@Param("record") TTripNote record, @Param("example") TTripNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTripNote record);

    int insertSelective(TTripNote record);

    TTripNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTripNote record);

    int updateByPrimaryKey(TTripNote record);
    
    List<TTripNoteSearchResultVo> queryCondition(@Param("record")TTripNoteSearchVo record);
    
    List<TTripNoteSearchResultVo> queryConditionAndPage(@Param("record")TTripNoteSearchVo record, RowBounds rowBound);

    TTripNoteDetailResultVo getDetailByKey(Integer id);
    
    
    /**删除定制下所有日程关联的交通信息
     * @param example
     * @return
     */
    int deleteTraffic(@Param("record") TTripNote record);
    
    /**删除定制下所有日程关联的日程行程信息
     * @param example
     * @return
     */
    int deleteScheduleTrip(@Param("record") TTripNote record);
    
    /**删除定制下所有日程关联的定制师笔记信息
     * @param example
     * @return
     */
    int deleteScheduleRUsenote(@Param("record") TTripNote record);
    
    /**删除定制下所有日程关联的目的地城市信息
     * @param record
     * @return
     */
    int deleteScheduleRCity(@Param("record") TTripNote record);
    

	
	/**查询路书所有关联POI和交通的价格信息
	 * @param id
	 * @return
	 */
	TTripNoteDetailResultVo getTravelQuotationByKey(Integer id);
	

	/**查询路书所有关联POI和交通的价格信息 按类型排序
	 * @param id
	 * @return
	 */
	TTripNoteTravelResultVo getTravelQuotationGroupByTypeByKey(Integer id);

	/**查询总数
	 * @param t
	 * @return
	 */
	int queryCount(@Param("record")TTripNoteSearchVo t);
}