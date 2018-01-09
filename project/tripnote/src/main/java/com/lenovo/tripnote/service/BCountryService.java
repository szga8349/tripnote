package com.lenovo.tripnote.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lenovo.tripnote.entity.BCountry;
import com.lenovo.tripnote.entity.vo.BCountryVo;

public interface BCountryService extends IDbService<BCountry>{

	List<BCountry> select(BCountryVo t);
	
	List<BCountry> selectAndPage(BCountryVo t, RowBounds rowBound);

}
