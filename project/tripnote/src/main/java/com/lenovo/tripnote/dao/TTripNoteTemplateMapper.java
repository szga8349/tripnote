package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripNoteTemplate;
import com.lenovo.tripnote.entity.TTripNoteTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripNoteTemplateMapper {
    int countByExample(TTripNoteTemplateExample example);

    int deleteByExample(TTripNoteTemplateExample example);

    List<TTripNoteTemplate> selectByExample(TTripNoteTemplateExample example);

    int updateByExampleSelective(@Param("record") TTripNoteTemplate record, @Param("example") TTripNoteTemplateExample example);

    int updateByExample(@Param("record") TTripNoteTemplate record, @Param("example") TTripNoteTemplateExample example);

    List<TTripNoteTemplate> selectByExampleAndPage(TTripNoteTemplateExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(TTripNoteTemplate record);

    int insertSelective(TTripNoteTemplate record);

    TTripNoteTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTripNoteTemplate record);

    int updateByPrimaryKey(TTripNoteTemplate record);
}