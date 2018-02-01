package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomerCatogry;
import com.lenovo.tripnote.entity.vo.TCustomerCatogryVo;

public interface TCustomerCatogryService extends IDbService<TCustomerCatogry> {
	public List<TCustomerCatogry> search(TCustomerCatogryVo t,BAccount account,boolean isexsit);
} 