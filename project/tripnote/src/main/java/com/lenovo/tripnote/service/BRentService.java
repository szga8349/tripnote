package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BRent;
import com.lenovo.tripnote.entity.vo.BRentSearchVo;

public interface BRentService extends IDbService<BRent>{
        public Integer searchCount(BRentSearchVo vo);
        public List<BRent> selectAndPage(BRentSearchVo vo,RowBounds row);
        public Integer insertRentToSchedule(Integer rentId,Integer scheduleId);
        public Integer deleteRentToSchedule(Integer rentId,Integer scheduleId);
}
