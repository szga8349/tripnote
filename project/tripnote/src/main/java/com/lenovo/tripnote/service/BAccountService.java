package com.lenovo.tripnote.service;

import com.lenovo.tripnote.entity.BAccount;

public interface BAccountService {

	BAccount getByUsernameOrPhone(String username);

	int insert(BAccount account);

	int update(BAccount account);

}
