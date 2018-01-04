package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripnoteRCustomer;
import com.lenovo.tripnote.entity.TTripnoteRCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripnoteRCustomerMapper {
    int countByExample(TTripnoteRCustomerExample example);

    int deleteByExample(TTripnoteRCustomerExample example);

    List<TTripnoteRCustomer> selectByExample(TTripnoteRCustomerExample example);

    int updateByExampleSelective(@Param("record") TTripnoteRCustomer record, @Param("example") TTripnoteRCustomerExample example);

    int updateByExample(@Param("record") TTripnoteRCustomer record, @Param("example") TTripnoteRCustomerExample example);

    List<TTripnoteRCustomer> selectByExampleAndPage(TTripnoteRCustomerExample example, RowBounds rowBound);

    int insert(TTripnoteRCustomer record);

    int insertSelective(TTripnoteRCustomer record);
}