package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BUserTripnote;
import com.lenovo.tripnote.entity.vo.BUserTripnoteResultVo;
import com.lenovo.tripnote.entity.vo.BUserTripnoteSearchVo;

public interface BUserTripnoteService extends IDbService<BUserTripnote>{

	List<BUserTripnoteResultVo> queryCondition(BUserTripnoteSearchVo search);

	Integer queryCountCondition(BUserTripnoteSearchVo search);

}
