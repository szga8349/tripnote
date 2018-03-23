package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TCustomer;
import com.lenovo.tripnote.entity.vo.BatchDeleteVo;

public interface TCustomerService extends IDbService<TCustomer>{
      public int insertTripnoteRCustomer(Integer tripnoteId,Integer customerId);
      /*public List<TCustomer> selectCondition(TCustomer t);*/
	  public List<TCustomer> search(TCustomer search, BAccount account, boolean b);
	  int deleteTripnoteRCustomer(Integer tripnoteId, Integer customerId);
	  public List<Integer> batchDelete(BatchDeleteVo ids,BAccount account);
}
