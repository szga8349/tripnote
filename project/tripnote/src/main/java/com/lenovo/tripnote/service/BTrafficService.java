package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BTraffic;
import com.lenovo.tripnote.entity.vo.BTrafficSearchVo;

public interface BTrafficService extends IDbService<BTraffic>{

	Integer searchCount(BTrafficSearchVo bpoiSearch);

	List<BTraffic> selectAndPage(BTrafficSearchVo bpoiSearch, RowBounds rowBounds);

}
