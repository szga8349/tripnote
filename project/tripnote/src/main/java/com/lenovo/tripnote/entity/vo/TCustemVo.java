package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TCustemVo {
    private Integer id;
    
	private String name;
	
	private String phone;
	
	/**
     * t_customer.IDCard (身份证信息)
     * @ibatorgenerated 2018-01-31 18:03:56
     */
    private String idcard;

}
