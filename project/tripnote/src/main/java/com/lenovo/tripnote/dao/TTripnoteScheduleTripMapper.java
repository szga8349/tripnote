package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripnoteScheduleTrip;
import com.lenovo.tripnote.entity.TTripnoteScheduleTripExample;
import com.lenovo.tripnote.entity.TTripnoteScheduleTripWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripnoteScheduleTripMapper {
    int countByExample(TTripnoteScheduleTripExample example);

    int deleteByExample(TTripnoteScheduleTripExample example);

    List<TTripnoteScheduleTripWithBLOBs> selectByExampleWithBLOBs(TTripnoteScheduleTripExample example);

    List<TTripnoteScheduleTrip> selectByExample(TTripnoteScheduleTripExample example);

    int updateByExampleSelective(@Param("record") TTripnoteScheduleTripWithBLOBs record, @Param("example") TTripnoteScheduleTripExample example);

    int updateByExampleWithBLOBs(@Param("record") TTripnoteScheduleTripWithBLOBs record, @Param("example") TTripnoteScheduleTripExample example);

    int updateByExample(@Param("record") TTripnoteScheduleTrip record, @Param("example") TTripnoteScheduleTripExample example);

    List<TTripnoteScheduleTripWithBLOBs> selectByExampleWithBLOBsAndPage(TTripnoteScheduleTripExample example, RowBounds rowBound);

    List<TTripnoteScheduleTrip> selectByExampleAndPage(TTripnoteScheduleTripExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(TTripnoteScheduleTripWithBLOBs record);

    int insertSelective(TTripnoteScheduleTripWithBLOBs record);

    TTripnoteScheduleTripWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTripnoteScheduleTripWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TTripnoteScheduleTripWithBLOBs record);

    int updateByPrimaryKey(TTripnoteScheduleTrip record);
}