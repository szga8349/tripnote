package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripnoteScheduleTraffic;
import com.lenovo.tripnote.entity.TTripnoteScheduleTrafficExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripnoteScheduleTrafficMapper {
    int countByExample(TTripnoteScheduleTrafficExample example);

    int deleteByExample(TTripnoteScheduleTrafficExample example);

    List<TTripnoteScheduleTraffic> selectByExample(TTripnoteScheduleTrafficExample example);

    int updateByExampleSelective(@Param("record") TTripnoteScheduleTraffic record, @Param("example") TTripnoteScheduleTrafficExample example);

    int updateByExample(@Param("record") TTripnoteScheduleTraffic record, @Param("example") TTripnoteScheduleTrafficExample example);

    List<TTripnoteScheduleTraffic> selectByExampleAndPage(TTripnoteScheduleTrafficExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(TTripnoteScheduleTraffic record);

    int insertSelective(TTripnoteScheduleTraffic record);

    TTripnoteScheduleTraffic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTripnoteScheduleTraffic record);

    int updateByPrimaryKey(TTripnoteScheduleTraffic record);
}