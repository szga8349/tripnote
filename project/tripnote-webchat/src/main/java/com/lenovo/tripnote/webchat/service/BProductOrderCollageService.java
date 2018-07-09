package com.lenovo.tripnote.webchat.service;


import java.util.List;

import com.lenovo.tripnote.webchat.entity.BProductOrderCollage;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollagedPartakeVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

public interface BProductOrderCollageService extends IDbService<BProductOrderCollage> {

	/**参与拼团
	 * @param id:拼团记录ID
	 * @param token:登录用户信息
	 * @return
	 */
	int insertCollage(BProductOrderCollagedPartakeVo vo,TokenVo token);

	/**拼团查询哪些人信息
	 * @param valueOf
	 * @return
	 */
	List<BProductOrderCollageDetailVo> listUser(Integer valueOf);
	
	

	/**产品对应的拼团
	 * @param valueOf
	 * @return
	 */
	List<BProductOrderCollageDetailVo> listCollage(Integer valueOf);

}
