package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BUserTripnote;
import com.lenovo.tripnote.entity.BUserTripnoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BUserTripnoteMapper {
    int countByExample(BUserTripnoteExample example);

    int deleteByExample(BUserTripnoteExample example);

    List<BUserTripnote> selectByExampleWithBLOBs(BUserTripnoteExample example);

    List<BUserTripnote> selectByExample(BUserTripnoteExample example);

    int updateByExampleSelective(@Param("record") BUserTripnote record, @Param("example") BUserTripnoteExample example);

    int updateByExampleWithBLOBs(@Param("record") BUserTripnote record, @Param("example") BUserTripnoteExample example);

    int updateByExample(@Param("record") BUserTripnote record, @Param("example") BUserTripnoteExample example);

    List<BUserTripnote> selectByExampleWithBLOBsAndPage(BUserTripnoteExample example, RowBounds rowBound);

    List<BUserTripnote> selectByExampleAndPage(BUserTripnoteExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(BUserTripnote record);

    int insertSelective(BUserTripnote record);

    BUserTripnote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BUserTripnote record);

    int updateByPrimaryKeyWithBLOBs(BUserTripnote record);

    int updateByPrimaryKey(BUserTripnote record);
}