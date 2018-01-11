package com.lenovo.tripnote.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.TTripnoteSchedule;
import com.lenovo.tripnote.entity.TTripnoteScheduleExample;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleResultVo;

public interface TTripnoteScheduleMapper {
	
    int countByExample(TTripnoteScheduleExample example);

    int deleteByExample(TTripnoteScheduleExample example);

    List<TTripnoteSchedule> selectByExampleWithBLOBs(TTripnoteScheduleExample example);

    List<TTripnoteSchedule> selectByExample(TTripnoteScheduleExample example);

    int updateByExampleSelective(@Param("record") TTripnoteSchedule record, @Param("example") TTripnoteScheduleExample example);

    int updateByExampleWithBLOBs(@Param("record") TTripnoteSchedule record, @Param("example") TTripnoteScheduleExample example);

    int updateByExample(@Param("record") TTripnoteSchedule record, @Param("example") TTripnoteScheduleExample example);

    List<TTripnoteSchedule> selectByExampleWithBLOBsAndPage(TTripnoteScheduleExample example, RowBounds rowBound);

    List<TTripnoteSchedule> selectByExampleAndPage(TTripnoteScheduleExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(TTripnoteSchedule record);

    int insertSelective(TTripnoteSchedule record);

    TTripnoteSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTripnoteSchedule record);

    int updateByPrimaryKeyWithBLOBs(TTripnoteSchedule record);

    int updateByPrimaryKey(TTripnoteSchedule record);

	TTripNoteScheduleResultVo getDetailByKey(Integer scheduleId);
	
	 
    /**删除定制日程关联的交通信息
     * @param example
     * @return
     */
    int deleteTraffic(@Param("record") TTripnoteSchedule record);
    
    /**删除定制日程的日程行程信息
     * @param example
     * @return
     */
    int deleteScheduleTrip(@Param("record") TTripnoteSchedule record);
    
    /**删除定制日程的定制师笔记信息
     * @param example
     * @return
     */
    int deleteScheduleRUsenote(@Param("record") TTripnoteSchedule record);
    
    /**删除定制日程关联的目的地城市信息
     * @param record
     * @return
     */
    int deleteScheduleRCity(@Param("record") TTripnoteSchedule record);
}