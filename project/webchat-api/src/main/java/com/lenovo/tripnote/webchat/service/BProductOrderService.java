package com.lenovo.tripnote.webchat.service;

import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;

public interface BProductOrderService extends IDbService<BProductOrder> {

	ResultPageInfo search(BProductOrderSearchVo search);
	
	ResultPageInfo searchCustomer(BProductOrderSearchVo search);

	/**产品对应的普通订单 包括直购和限时单
	 * @param valueOf
	 * @return
	 */
	ResultPageInfo listByProduct(BProductOrderSearchVo valueOf);

}
