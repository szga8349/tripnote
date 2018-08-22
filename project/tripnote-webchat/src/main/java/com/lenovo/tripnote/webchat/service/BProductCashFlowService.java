package com.lenovo.tripnote.webchat.service;

import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowSearchVo;
import com.lenovo.tripnote.webchat.vo.ResultPageInfo;

public interface BProductCashFlowService extends IDbService<BProductCashflow>{
	
	/**查询分析收益
	 * @return
	 */
	public ResultPageInfo searchShareProfit(BProductCashFlowSearchVo searchVo);
	
	/**查询订单收益
	 * @return
	 */
	public ResultPageInfo searchProductProfit(BProductCashFlowSearchVo searchVo);
	
	/**查询用户余额
	 * @param userId
	 * @return
	 */
	public Float searchBalance(Integer userId);
	
	/**查询流水明细
	 * @return
	 */
	public ResultPageInfo searchCashFlow(BProductCashFlowSearchVo searchVo);

}
