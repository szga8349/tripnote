package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BCountry;
import com.lenovo.tripnote.entity.vo.BCountryVo;

public interface BCountryService extends IDbService<BCountry>{

	List<BCountry> select(BCountryVo t);
	
	List<BCountry> selectAndPage(BCountryVo t);

}
