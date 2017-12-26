package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.BHotelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BHotelMapper {
    int countByExample(BHotelExample example);

    int deleteByExample(BHotelExample example);

    List<BHotel> selectByExampleWithBLOBs(BHotelExample example);

    List<BHotel> selectByExample(BHotelExample example);

    int updateByExampleSelective(@Param("record") BHotel record, @Param("example") BHotelExample example);

    int updateByExampleWithBLOBs(@Param("record") BHotel record, @Param("example") BHotelExample example);

    int updateByExample(@Param("record") BHotel record, @Param("example") BHotelExample example);

    List<BHotel> selectByExampleWithBLOBsAndPage(BHotelExample example, RowBounds rowBound);

    List<BHotel> selectByExampleAndPage(BHotelExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(BHotel record);

    int insertSelective(BHotel record);

    BHotel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BHotel record);

    int updateByPrimaryKeyWithBLOBs(BHotel record);

    int updateByPrimaryKey(BHotel record);
}