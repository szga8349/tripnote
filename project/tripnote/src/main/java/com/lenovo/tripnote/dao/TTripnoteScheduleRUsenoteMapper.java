package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripnoteScheduleRUsenote;
import com.lenovo.tripnote.entity.TTripnoteScheduleRUsenoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripnoteScheduleRUsenoteMapper {
    int countByExample(TTripnoteScheduleRUsenoteExample example);

    int deleteByExample(TTripnoteScheduleRUsenoteExample example);

    List<TTripnoteScheduleRUsenote> selectByExample(TTripnoteScheduleRUsenoteExample example);

    int updateByExampleSelective(@Param("record") TTripnoteScheduleRUsenote record, @Param("example") TTripnoteScheduleRUsenoteExample example);

    int updateByExample(@Param("record") TTripnoteScheduleRUsenote record, @Param("example") TTripnoteScheduleRUsenoteExample example);

    List<TTripnoteScheduleRUsenote> selectByExampleAndPage(TTripnoteScheduleRUsenoteExample example, RowBounds rowBound);

    int insert(TTripnoteScheduleRUsenote record);

    int insertSelective(TTripnoteScheduleRUsenote record);
}