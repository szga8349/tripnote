package com.lenovo.tripnote.webchat.vo;

/**
 * 收益类型
 * 
 * @author shijy2
 *
 */
public enum ProfitTypeVo {
	SHARE(1),//分享收益
	ORDER(2), //订单收益
	OUT(-1);//出账
	private int index;

	ProfitTypeVo(int index) {
		this.index = index;
	}

	public int index() {
		return this.index;
	}
}
