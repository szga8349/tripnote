package com.lenovo.tripnote.service;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.BLogin;

public interface BAccountService {

	BAccount getByUsernameOrPhone(String username);

	int insert(BAccount account);

	int update(BAccount account);

	int insert(BLogin record);

	BLogin getByAccountID(Long loginID);

	int update(BLogin userId);

}
