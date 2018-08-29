package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductOrder {
    /**
     * b_product_order.id (产品订单主键ID)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Integer id;

    /**
     * b_product_order.status (状态:0:编辑:1支付)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Integer status;

    /**
     * b_product_order.product_id (产品主键值)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Integer productId;

    /**
     * b_product_order.create_user_id (订单创建人ID)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Integer createUserId;

    /**
     * b_product_order.create_user_name (创建者登陆名)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private String createUserName;

    /**
     * b_product_order.create_time (创建时间)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Date createTime;

    /**
     * b_product_order.pay_time (付款时间)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Date payTime;

    /**
     * b_product_order.order_user_name (预订人_姓名)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private String orderUserName;

    /**
     * b_product_order.order_user_phone (预订人_手机号)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private String orderUserPhone;

    /**
     * b_product_order.place (出发地)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private String place;

    /**
     * b_product_order.card_id (身份证号)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private String cardId;

    /**
     * b_product_order.use_date (使用日期)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Date useDate;

    /**
     * b_product_order.other_info (其它信息)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private String otherInfo;

    /**
     * b_product_order.price (下订单时产品价格)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Float price;

    /**
     * b_product_order.share_id (分享主键值ID,不为-1表示该订单来自于别人分享的产品)
     * @ibatorgenerated 2018-08-29 10:41:44
     */
    private Integer shareId;
}