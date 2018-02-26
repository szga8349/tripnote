package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BPoi;
import com.lenovo.tripnote.entity.vo.BPoiResultVo;
import com.lenovo.tripnote.entity.vo.BPoiSearchVo;

public interface BPoiService extends IDbService<BPoi>{

	List<BPoiResultVo> selectAndPage(BPoiSearchVo bpoiSearch, RowBounds rowBounds);
	
	Integer searchCount(BPoiSearchVo bpoiSearch);

}
