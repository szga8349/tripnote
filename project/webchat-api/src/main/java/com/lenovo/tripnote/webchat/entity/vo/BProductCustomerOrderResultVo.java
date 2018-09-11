package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.entity.BProduct;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductCustomerOrderResultVo extends BProduct{
	
	/**
	 * 订购人数
	 */
	private Integer orderNumber;
	
	/**
	 * 总价格
	 */
	private Float allPrice;

}
