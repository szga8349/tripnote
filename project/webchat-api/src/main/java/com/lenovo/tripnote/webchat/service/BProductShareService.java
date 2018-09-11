package com.lenovo.tripnote.webchat.service;

import com.lenovo.tripnote.webchat.entity.BProductShare;
import com.lenovo.tripnote.webchat.entity.vo.BProductShareSearchVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;

public interface BProductShareService  extends IDbService<BProductShare>{

	ResultPageInfo search(BProductShareSearchVo search);

}
