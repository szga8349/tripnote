package com.lenovo.tripnote.webchat.service;


import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.vo.BProductDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductUpdateStatusVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductVo;
import com.lenovo.tripnote.webchat.vo.BatchVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

public interface BProductService extends IDbService<BProduct>{
	
      public int addProduct(BProductVo vo,TokenVo token);
      
      public int updateProduct(BProductVo vo,Integer id);
      
      public ResultPageInfo searchProduct(BProductSearchVo searchVo);

	  public void deleteBatch(BatchVo batchVo);

	  public BProductDetailVo detail(Integer valueOf);
	  
	  public void updateStatusBatch(BProductUpdateStatusVo statuses);
	  
	  /**验证订单是否 还能继续支付
	 * @param orderId
	 * @param priceType
	 * @return
	 */
	public boolean checkProduct(Integer orderId,Integer priceType);
}
