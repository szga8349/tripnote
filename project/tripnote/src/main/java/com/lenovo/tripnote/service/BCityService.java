package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BCity;
import com.lenovo.tripnote.entity.vo.BCityVo;

public interface BCityService extends IDbService<BCity>{

	List<BCity> select(BCityVo t);
	
	List<BCity> selectAndPage(BCityVo t, RowBounds rowBound);
}
