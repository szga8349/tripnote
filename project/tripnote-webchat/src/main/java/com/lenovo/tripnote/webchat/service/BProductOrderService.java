package com.lenovo.tripnote.webchat.service;

import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;

public interface BProductOrderService extends IDbService<BProductOrder> {

	ResultPageInfo search(BProductOrderSearchVo search);

}
