package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteScheduleOffer;
import com.lenovo.tripnote.entity.TTripnoteScheduleOfferExample;
import com.lenovo.tripnote.entity.vo.TTravelVo;

public interface TTripnoteScheduleOfferService extends IDbService<TTripnoteScheduleOffer>{
   public List<Integer> batchAdd(TTravelVo vo,BAccount account);
   
   public List<TTripnoteScheduleOffer> search(TTripnoteScheduleOfferExample offer);
}
