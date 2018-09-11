package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.vo.PageInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductCashFlowSearchVo extends PageInfo{
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	
	
	/**
	 * 收益类型
	 */
	private Integer profitType;

}
