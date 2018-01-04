package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BHotelExt;
import com.lenovo.tripnote.entity.BHotelExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BHotelExtMapper {
    int countByExample(BHotelExtExample example);

    int deleteByExample(BHotelExtExample example);

    List<BHotelExt> selectByExample(BHotelExtExample example);

    int updateByExampleSelective(@Param("record") BHotelExt record, @Param("example") BHotelExtExample example);

    int updateByExample(@Param("record") BHotelExt record, @Param("example") BHotelExtExample example);

    List<BHotelExt> selectByExampleAndPage(BHotelExtExample example, RowBounds rowBound);

    int insert(BHotelExt record);

    int insertSelective(BHotelExt record);
}