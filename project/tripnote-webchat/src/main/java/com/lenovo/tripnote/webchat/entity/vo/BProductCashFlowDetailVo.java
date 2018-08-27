package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDEncryption;
import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductCashFlowDetailVo extends BProductCashflow {

	/**
	 * 用户头像信息
	 */
	private String userImageUrl;

	/**
	 * b_account.login_name
	 * @ibatorgenerated 2018-06-19 16:22:08
	 */
	private String userName;

}
