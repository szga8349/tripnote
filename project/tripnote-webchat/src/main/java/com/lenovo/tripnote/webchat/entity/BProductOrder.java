package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * @ibatorgenerated 2018-07-09 16:50:58
     */
	@IDEncryption
    private Integer id;

    /**
     * b_product_order.status (状态:0:编辑:1支付)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private Integer status;

    /**
     * b_product_order.product_id (产品主键值)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    @IDEncryption
    private Integer productId;

    /**
     * b_product_order.create_user_id (订单创建人ID)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    @IDEncryption
    private Integer createUserId;

    /**
     * b_product_order.create_user_name (创建者登陆名)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private String createUserName;

    /**
     * b_product_order.create_time (创建时间)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	/**
	 * b_product_order.pay_time (付款时间)
	 * @ibatorgenerated 2018-07-09 16:50:58
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;

    /**
     * b_product_order.order_user_name (预订人_姓名)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private String orderUserName;

    /**
     * b_product_order.order_user_phone (预订人_手机号)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private String orderUserPhone;

    /**
     * b_product_order.place (出发地)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private String place;

    /**
     * b_product_order.card_id (身份证号)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private String cardId;

    /**
     * b_product_order.use_date (使用日期)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private Date useDate;

    /**
     * b_product_order.other_info (其它信息)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private String otherInfo;
}