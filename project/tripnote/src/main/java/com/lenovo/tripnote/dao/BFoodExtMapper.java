package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BFoodExt;
import com.lenovo.tripnote.entity.BFoodExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BFoodExtMapper {
    int countByExample(BFoodExtExample example);

    int deleteByExample(BFoodExtExample example);

    List<BFoodExt> selectByExample(BFoodExtExample example);

    int updateByExampleSelective(@Param("record") BFoodExt record, @Param("example") BFoodExtExample example);

    int updateByExample(@Param("record") BFoodExt record, @Param("example") BFoodExtExample example);

    List<BFoodExt> selectByExampleAndPage(BFoodExtExample example, RowBounds rowBound);

    int insert(BFoodExt record);

    int insertSelective(BFoodExt record);
}