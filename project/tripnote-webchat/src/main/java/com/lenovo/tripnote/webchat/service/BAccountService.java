package com.lenovo.tripnote.webchat.service;

import com.lenovo.tripnote.webchat.entity.BAccount;
import com.lenovo.tripnote.webchat.entity.BLogin;
import com.lenovo.tripnote.webchat.entity.vo.BAccountDetailVo;

public interface BAccountService extends IDbService<BAccount> {

	BAccount getByUsernameOrPhone(String username);

	int insert(BLogin record);

	BLogin getByAccountID(Long loginID);

	int update(BLogin userId);
	
	BAccountDetailVo getDetail(Integer valueOf);
}
