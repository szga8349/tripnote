package com.lenovo.tripnote.webchat.entity.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lenovo.tripnote.webchat.convert.IDDeEncryption;

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
	@IDDeEncryption
    private Integer productId;
	

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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date useDate;

    /**
     * b_product_order.other_info (其它信息)
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    private String otherInfo;
    
    /**
     * b_product_order_collage.deposit (付预定金)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private Float deposit;
    
    /**
     * b_product_order_collage.play_status (支付状态：0编辑 1预支付 2支付完成)
     * @ibatorgenerated 2018-08-07 15:29:05
     */
    private Integer playStatus;

    /**
     * b_product_order_collage.form_id (微信小程序发送通知模板信息中使用的form_id,由预支付后设置)
     * @ibatorgenerated 2018-08-07 15:29:05
     */
    private String formId;



}
