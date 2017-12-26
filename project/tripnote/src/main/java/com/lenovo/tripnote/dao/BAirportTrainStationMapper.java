package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BAirportTrainStation;
import com.lenovo.tripnote.entity.BAirportTrainStationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BAirportTrainStationMapper {
    int countByExample(BAirportTrainStationExample example);

    int deleteByExample(BAirportTrainStationExample example);

    List<BAirportTrainStation> selectByExampleWithBLOBs(BAirportTrainStationExample example);

    List<BAirportTrainStation> selectByExample(BAirportTrainStationExample example);

    int updateByExampleSelective(@Param("record") BAirportTrainStation record, @Param("example") BAirportTrainStationExample example);

    int updateByExampleWithBLOBs(@Param("record") BAirportTrainStation record, @Param("example") BAirportTrainStationExample example);

    int updateByExample(@Param("record") BAirportTrainStation record, @Param("example") BAirportTrainStationExample example);

    List<BAirportTrainStation> selectByExampleWithBLOBsAndPage(BAirportTrainStationExample example, RowBounds rowBound);

    List<BAirportTrainStation> selectByExampleAndPage(BAirportTrainStationExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Integer id);

    int insert(BAirportTrainStation record);

    int insertSelective(BAirportTrainStation record);

    BAirportTrainStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BAirportTrainStation record);

    int updateByPrimaryKeyWithBLOBs(BAirportTrainStation record);

    int updateByPrimaryKey(BAirportTrainStation record);
}