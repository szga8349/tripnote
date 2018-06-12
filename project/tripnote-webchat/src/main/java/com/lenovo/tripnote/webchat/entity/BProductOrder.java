package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import com.lenovo.tripnote.webchat.convert.IDEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductOrder {
    /**
     * b_product_order.id (产品订单主键ID)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
	@IDEncryption
    private Integer id;

    /**
     * b_product_order.status (状态:0:编辑:1支付)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
    private Integer status;

    /**
     * b_product_order.product_id (产品主键值)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
    @IDEncryption
    private Integer productId;

    /**
     * b_product_order.create_user_id (订单创建人ID)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
    @IDEncryption
    private Integer createUserId;

    /**
     * b_product_order.create_user_name (创建者登陆名)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
    private String createUserName;

    /**
     * b_product_order.create_time (创建时间)
     * @ibatorgenerated 2018-06-06 17:42:48
     */
    private Date createTime;
}