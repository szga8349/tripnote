package com.lenovo.tripnote.webchat.service;

import com.lenovo.tripnote.webchat.entity.BProductCollection;
import com.lenovo.tripnote.webchat.entity.vo.BProductCollectionSearchVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;

public interface BProductCollectionService  extends IDbService<BProductCollection>{

	ResultPageInfo search(BProductCollectionSearchVo search);

}
