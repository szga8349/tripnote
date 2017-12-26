package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BScenic;
import com.lenovo.tripnote.entity.BScenicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BScenicMapper {
    int countByExample(BScenicExample example);

    int deleteByExample(BScenicExample example);

    List<BScenic> selectByExampleWithBLOBs(BScenicExample example);

    List<BScenic> selectByExample(BScenicExample example);

    int updateByExampleSelective(@Param("record") BScenic record, @Param("example") BScenicExample example);

    int updateByExampleWithBLOBs(@Param("record") BScenic record, @Param("example") BScenicExample example);

    int updateByExample(@Param("record") BScenic record, @Param("example") BScenicExample example);

    List<BScenic> selectByExampleWithBLOBsAndPage(BScenicExample example, RowBounds rowBound);

    List<BScenic> selectByExampleAndPage(BScenicExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(BScenic record);

    int insertSelective(BScenic record);

    BScenic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BScenic record);

    int updateByPrimaryKeyWithBLOBs(BScenic record);

    int updateByPrimaryKey(BScenic record);
}