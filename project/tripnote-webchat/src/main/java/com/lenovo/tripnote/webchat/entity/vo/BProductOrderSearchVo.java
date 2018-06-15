package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.vo.PageInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductOrderSearchVo extends PageInfo {
	
	private Integer userId;
	
	private String name;
	 /**
     * b_product.price_type (格价类型:1限时特价,2拼团,3直购)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Integer priceType;
}
