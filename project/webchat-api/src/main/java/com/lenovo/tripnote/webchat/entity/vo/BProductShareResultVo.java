package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.entity.BProduct;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductShareResultVo extends BProduct{
	
	/**
	 * 购买人数
	 */
	private Integer purchaseNumber;
	
	
	/**
	 * 分享收益
	 */
	private Float shareProfit;

}
