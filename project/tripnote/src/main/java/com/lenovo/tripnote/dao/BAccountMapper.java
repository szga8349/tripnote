package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BAccountMapper {
    int countByExample(BAccountExample example);

    int deleteByExample(BAccountExample example);

    List<BAccount> selectByExample(BAccountExample example);

    int updateByExampleSelective(@Param("record") BAccount record, @Param("example") BAccountExample example);

    int updateByExample(@Param("record") BAccount record, @Param("example") BAccountExample example);

    List<BAccount> selectByExampleAndPage(BAccountExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(BAccount record);

    int insertSelective(BAccount record);

    BAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BAccount record);

    int updateByPrimaryKey(BAccount record);
}