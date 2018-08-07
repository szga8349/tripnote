package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import com.lenovo.tripnote.webchat.convert.IDEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductOrderCollage  {
    /**
     * b_product_order_collage.id (订单拼团主键ID)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
	@IDEncryption
    private Integer id;

    /**
     * b_product_order_collage.collage_user_id (参与拼团用户ID)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
	@IDEncryption
    private Integer collageUserId;

    /**
     * b_product_order_collage.collage_user_name (参与拼团用户名称)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private String collageUserName;

    /**
     * b_product_order_collage.product_collage_id (产品对应的拼团类型ID)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    @IDEncryption
    private Integer productCollageId;

    /**
     * b_product_order_collage.product_id (拼团产品ID)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    @IDEncryption
    private Integer productId;

    /**
     * b_product_order_collage.collage_status (拼团状态:0:拼团中,1:拼团结束(达到拼团人数或达到拼团时限))
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private Integer collageStatus;

    /**
     * b_product_order_collage.create_user_id
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    @IDEncryption
    private Integer createUserId;

    /**
     * b_product_order_collage.create_user_name (拼团创建者登录名)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private String createUserName;

    /**
     * b_product_order_collage.create_time (创建时间)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private Date createTime;

    /**
     * b_product_order_collage.collage_id (拼团主键ID)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    @IDEncryption
    private Integer collageId;

    /**
     * b_product_order_collage.order_user_name (预订人_姓名)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private String orderUserName;

    /**
     * b_product_order_collage.order_user_phone (预订人_手机号)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private String orderUserPhone;

    /**
     * b_product_order_collage.place (出发地)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private String place;

    /**
     * b_product_order_collage.card_id (身份证号)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private String cardId;

    /**
     * b_product_order_collage.use_date (使用日期)
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    private Date useDate;

    /**
     * b_product_order_collage.other_info (其它信息)
     * @ibatorgenerated 2018-07-09 16:50:59
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