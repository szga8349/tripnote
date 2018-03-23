package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TCustomerCatogryResultVo {
	
	  /**
     * t_customer_catogry.name
     * @ibatorgenerated 2018-01-31 18:03:56
     */
    private String name;
    
    
    private Integer id;
    
    /**
     * 分组下客户人数
     */
    private Integer count;

}
