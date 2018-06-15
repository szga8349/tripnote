package com.lenovo.tripnote.webchat.entity.vo;

import java.util.Date;

import com.lenovo.tripnote.webchat.convert.IDEncryption;
import com.lenovo.tripnote.webchat.entity.BProduct;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductOrderResultVo extends BProduct{

    /**
     * b_product_order.status (状态:0:编辑:1支付)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
    private Integer orderStatus;
    
    /**
     * b_product_order.create_time (创建时间)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
    private Date orderCreateTime;
    
    /**
     * 订单ID
     */
    @IDEncryption
    private Integer orderId;
}
