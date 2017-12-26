package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BCountry;
import com.lenovo.tripnote.entity.BCountryExample;
import com.lenovo.tripnote.entity.BCountryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BCountryMapper {
    int countByExample(BCountryExample example);

    int deleteByExample(BCountryExample example);

    List<BCountry> selectByExample(BCountryExample example);

    int updateByExampleSelective(@Param("record") BCountry record, @Param("example") BCountryExample example);

    int updateByExample(@Param("record") BCountry record, @Param("example") BCountryExample example);

    List<BCountry> selectByExampleAndPage(BCountryExample example, RowBounds rowBound);

    int deleteByPrimaryKey(BCountryKey key);

    int insert(BCountry record);

    int insertSelective(BCountry record);

    BCountry selectByPrimaryKey(BCountryKey key);

    int updateByPrimaryKeySelective(BCountry record);

    int updateByPrimaryKey(BCountry record);
}