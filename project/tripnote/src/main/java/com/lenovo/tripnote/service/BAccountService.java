package com.lenovo.tripnote.service;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BLogin;
import com.lenovo.tripnote.entity.vo.BAccountDetailVo;

public interface BAccountService extends IDbService<BAccount>{

	BAccount getByUsernameOrPhone(String username);

	int insert(BLogin record);

	BLogin getByAccountID(Long loginID);

	int update(BLogin userId);

	BAccountDetailVo getDetail(Integer valueOf);

}
