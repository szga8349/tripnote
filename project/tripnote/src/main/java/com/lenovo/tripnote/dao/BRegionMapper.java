package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BRegion;
import com.lenovo.tripnote.entity.BRegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BRegionMapper {
    int countByExample(BRegionExample example);

    int deleteByExample(BRegionExample example);

    List<BRegion> selectByExample(BRegionExample example);

    int updateByExampleSelective(@Param("record") BRegion record, @Param("example") BRegionExample example);

    int updateByExample(@Param("record") BRegion record, @Param("example") BRegionExample example);

    List<BRegion> selectByExampleAndPage(BRegionExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(BRegion record);

    int insertSelective(BRegion record);

    BRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BRegion record);

    int updateByPrimaryKey(BRegion record);
}