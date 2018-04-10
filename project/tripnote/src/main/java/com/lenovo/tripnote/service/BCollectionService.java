package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BCollection;
import com.lenovo.tripnote.entity.vo.BCollectionResultVo;
import com.lenovo.tripnote.entity.vo.BCollectionSearchVo;

public interface BCollectionService extends IDbService<BCollection>{

	List<BCollectionResultVo> queryCondition(BCollectionSearchVo search);

	Integer queryCountCondition(BCollectionSearchVo search);


}
