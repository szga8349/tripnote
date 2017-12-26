package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.TCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TCustomerMapper {
    int countByExample(TCustomerExample example);

    int deleteByExample(TCustomerExample example);

    List<TCustomer> selectByExample(TCustomerExample example);

    int updateByExampleSelective(@Param("record") TCustomer record, @Param("example") TCustomerExample example);

    int updateByExample(@Param("record") TCustomer record, @Param("example") TCustomerExample example);

    List<TCustomer> selectByExampleAndPage(TCustomerExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(TCustomer record);

    int insertSelective(TCustomer record);

    TCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomer record);

    int updateByPrimaryKey(TCustomer record);
}