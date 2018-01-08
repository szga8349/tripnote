package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripNoteExample;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteResultVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripNoteMapper {
    int countByExample(TTripNoteExample example);

    int deleteByExample(TTripNoteExample example);

    int updateByExampleSelective(@Param("record") TTripNote record, @Param("example") TTripNoteExample example);

    int updateByExample(@Param("record") TTripNote record, @Param("example") TTripNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTripNote record);

    int insertSelective(TTripNote record);

    TTripNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTripNote record);

    int updateByPrimaryKey(TTripNote record);
    
    
    List<TTripNoteResultVo> queryCondition(TTripNoteExample example);
    
    List<TTripNoteResultVo> queryConditionAndPage(TTripNoteExample example, RowBounds rowBound);

    TTripNoteDetailResultVo getDetailByKey(Integer id);
}