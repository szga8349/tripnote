package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripnoteSchedule;
import com.lenovo.tripnote.entity.TTripnoteScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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
}