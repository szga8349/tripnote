package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BFood;
import com.lenovo.tripnote.entity.BFoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BFoodMapper {
    int countByExample(BFoodExample example);

    int deleteByExample(BFoodExample example);

    List<BFood> selectByExampleWithBLOBs(BFoodExample example);

    List<BFood> selectByExample(BFoodExample example);

    int updateByExampleSelective(@Param("record") BFood record, @Param("example") BFoodExample example);

    int updateByExampleWithBLOBs(@Param("record") BFood record, @Param("example") BFoodExample example);

    int updateByExample(@Param("record") BFood record, @Param("example") BFoodExample example);

    List<BFood> selectByExampleWithBLOBsAndPage(BFoodExample example, RowBounds rowBound);

    List<BFood> selectByExampleAndPage(BFoodExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(BFood record);

    int insertSelective(BFood record);

    BFood selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BFood record);

    int updateByPrimaryKeyWithBLOBs(BFood record);

    int updateByPrimaryKey(BFood record);
}