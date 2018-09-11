package com.lenovo.tripnote.webchat.service;

import java.util.List;

import com.lenovo.tripnote.webchat.entity.BClassify;

public interface BClassifyService extends IDbService<BClassify> {
     public List<BClassify> searchBClassify(Integer parentId);
}
