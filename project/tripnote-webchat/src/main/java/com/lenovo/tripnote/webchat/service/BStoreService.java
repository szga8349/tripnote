package com.lenovo.tripnote.webchat.service;

import java.util.List;

import com.lenovo.tripnote.webchat.entity.BStore;

public interface BStoreService extends IDbService<BStore> {
     public List<BStore> searchBStore(Integer userId);
}
