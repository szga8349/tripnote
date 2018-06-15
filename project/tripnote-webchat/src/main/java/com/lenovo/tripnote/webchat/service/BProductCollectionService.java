package com.lenovo.tripnote.webchat.service;

import java.util.List;

import com.lenovo.tripnote.webchat.entity.BProductCollection;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollectionResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollectionSearchVo;

public interface BProductCollectionService  extends IDbService<BProductCollection>{

	List<BProductCollectionResultVo> search(BProductCollectionSearchVo search);

}
