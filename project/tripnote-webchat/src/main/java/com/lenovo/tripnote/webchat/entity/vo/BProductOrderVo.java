package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDEncryption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BProductOrderVo {

    /**
     * b_product_order.status (状态:0:编辑:1支付)
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    private Integer status=0;

    /**
     * b_product_order.product_id (产品主键值)
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    @IDEncryption
    private Integer productId;

}
