package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.TTripNoteExample;
import com.lenovo.tripnote.entity.TTripNoteKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripNoteMapper {
    int countByExample(TTripNoteExample example);

    int deleteByExample(TTripNoteExample example);

    List<TTripNote> selectByExample(TTripNoteExample example);

    int updateByExampleSelective(@Param("record") TTripNote record, @Param("example") TTripNoteExample example);

    int updateByExample(@Param("record") TTripNote record, @Param("example") TTripNoteExample example);

    List<TTripNote> selectByExampleAndPage(TTripNoteExample example, RowBounds rowBound);

    int deleteByPrimaryKey(TTripNoteKey key);

    int insert(TTripNote record);

    int insertSelective(TTripNote record);

    TTripNote selectByPrimaryKey(TTripNoteKey key);

    int updateByPrimaryKeySelective(TTripNote record);

    int updateByPrimaryKey(TTripNote record);
}