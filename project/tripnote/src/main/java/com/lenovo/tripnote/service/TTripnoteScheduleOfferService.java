package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripnoteScheduleOffer;
import com.lenovo.tripnote.entity.vo.TCustemVo;
import com.lenovo.tripnote.entity.vo.TTravelVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleOfferSearchVo;

public interface TTripnoteScheduleOfferService extends IDbService<TTripnoteScheduleOffer>{
	
   public List<Integer> batchAdd(TTravelVo vo,BAccount account);
   
   public List<TTripnoteScheduleOfferResultVo> search(TTripnoteScheduleOfferSearchVo offer);

   public Integer addCustem(TCustemVo ttavelVo, BAccount account);

   public int deleteTripnoteRCustomer(Integer tripnoteId, Integer customerId);
}
