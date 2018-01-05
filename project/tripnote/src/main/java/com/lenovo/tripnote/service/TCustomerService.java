package com.lenovo.tripnote.service;

import java.util.List;

import com.lenovo.tripnote.entity.TCustomer;

public interface TCustomerService extends IDbService<TCustomer>{
      public int insertTripnoteRCustomer(Integer tripnoteId,Integer customerId);
      public List<TCustomer> selectCondition(TCustomer t);
}