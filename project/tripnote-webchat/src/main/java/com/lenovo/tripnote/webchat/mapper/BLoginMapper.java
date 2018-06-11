package com.lenovo.tripnote.webchat.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.webchat.entity.BLogin;
import com.lenovo.tripnote.webchat.entity.BLoginExample;

public interface BLoginMapper {
    int countByExample(BLoginExample example);

    int deleteByExample(BLoginExample example);

    List<BLogin> selectByExample(BLoginExample example);

    int updateByExampleSelective(@Param("record") BLogin record, @Param("example") BLoginExample example);

    int updateByExample(@Param("record") BLogin record, @Param("example") BLoginExample example);

    List<BLogin> selectByExampleAndPage(BLoginExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long loginid);

    int insert(BLogin record);

    int insertSelective(BLogin record);

    BLogin selectByPrimaryKey(Long loginid);

    int updateByPrimaryKeySelective(BLogin record);

    int updateByPrimaryKey(BLogin record);

	BLogin getByAccountID(Long userId);
}