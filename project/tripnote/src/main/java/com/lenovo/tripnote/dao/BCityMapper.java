package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BCity;
import com.lenovo.tripnote.entity.BCityExample;
import com.lenovo.tripnote.entity.BCityKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BCityMapper {
    int countByExample(BCityExample example);

    int deleteByExample(BCityExample example);

    List<BCity> selectByExample(BCityExample example);

    int updateByExampleSelective(@Param("record") BCity record, @Param("example") BCityExample example);

    int updateByExample(@Param("record") BCity record, @Param("example") BCityExample example);

    List<BCity> selectByExampleAndPage(BCityExample example, RowBounds rowBound);

    int deleteByPrimaryKey(BCityKey key);

    int insert(BCity record);

    int insertSelective(BCity record);

    BCity selectByPrimaryKey(BCityKey key);

    int updateByPrimaryKeySelective(BCity record);

    int updateByPrimaryKey(BCity record);
}