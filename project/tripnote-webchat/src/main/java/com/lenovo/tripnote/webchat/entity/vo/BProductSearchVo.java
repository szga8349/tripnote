package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDDeEncryption;
import com.lenovo.tripnote.webchat.vo.PageInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BProductSearchVo extends PageInfo{
	
	private Integer userId;
	
	private Integer status;
	
    private String name;
    
    private Boolean isMy = false;
    
    private Integer priceType;
    
    /**
     * b_product.classify_id (分类主键值ID)
     * @ibatorgenerated 2018-09-06 09:49:38
     */
    @IDDeEncryption
    private Integer classifyId;

    /**
     * b_product.store_id (门店主键值ID)
     * @ibatorgenerated 2018-09-06 09:49:38
     */
    @IDDeEncryption
    private Integer storeId;

}
