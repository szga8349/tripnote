package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductCashFlowResultVo {
	
	/**
     * b_product.name (产品名称)
     * @ibatorgenerated 2018-07-12 15:38:19
     */
    private String productName;
    
    /**
     * b_product.price_type (格价类型:1限时特价,2拼团,3直购)
     * @ibatorgenerated 2018-07-12 15:38:19
     */
    private Integer priceType;
    
    /**
     * b_product.image_url (产品封面图(在产品列表时展示))
     * @ibatorgenerated 2018-07-12 15:38:19
     */
    private String imageUrl;
    
    /**
     * b_product.introduction (产品介绍)
     * @ibatorgenerated 2018-07-12 15:38:19
     */
    private String introduction;
    
    /**
     * 完成单数
     */
    private Integer salesCount;
    
    /**
     * 总收益
     */
    private Float totalProfit;
    
    /**
     * b_product_cashflow.product_id (分享的产品ID,出账时设置-1)
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    @IDEncryption
    private Integer productId;

}
