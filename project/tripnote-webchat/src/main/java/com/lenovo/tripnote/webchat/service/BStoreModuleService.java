package com.lenovo.tripnote.webchat.service;

import java.util.List;

import com.lenovo.tripnote.webchat.entity.BStoreModule;

public interface BStoreModuleService extends IDbService<BStoreModule>{
    public List<BStoreModule> searchBStoreModule(Integer shortId);
}
