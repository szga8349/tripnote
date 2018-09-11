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
     * @ibatorgenerated 2018-08-29 10:41:45
     */
	@IDEncryption
    private Integer id;

    /**
     * b_product_order_collage.collage_user_id (参与拼团用户ID)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
	@IDEncryption
    private Integer collageUserId;

    /**
     * b_product_order_collage.collage_user_name (参与拼团用户名称)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String collageUserName;

    /**
     * b_product_order_collage.product_collage_id (产品对应的拼团类型ID)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
	@IDEncryption
    private Integer productCollageId;

    /**
     * b_product_order_collage.product_id (拼团产品ID)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
	@IDEncryption
    private Integer productId;

    /**
     * b_product_order_collage.collage_status (拼团状态:0:拼团中,1:拼团结束(达到拼团人数或达到拼团时限))
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private Integer collageStatus;

    /**
     * b_product_order_collage.create_user_id
     * @ibatorgenerated 2018-08-29 10:41:45
     */
	@IDEncryption
    private Integer createUserId;

    /**
     * b_product_order_collage.create_user_name (拼团创建者登录名)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String createUserName;

    /**
     * b_product_order_collage.create_time (创建时间)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private Date createTime;

    /**
     * b_product_order_collage.pay_time (付款时间)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private Date payTime;

    /**
     * b_product_order_collage.collage_id (拼团主键ID)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
	@IDEncryption
    private Integer collageId;

    /**
     * b_product_order_collage.order_user_name (预订人_姓名)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String orderUserName;

    /**
     * b_product_order_collage.order_user_phone (预订人_手机号)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String orderUserPhone;

    /**
     * b_product_order_collage.place (出发地)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String place;

    /**
     * b_product_order_collage.card_id (身份证号)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String cardId;

    /**
     * b_product_order_collage.use_date (使用日期)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private Date useDate;

    /**
     * b_product_order_collage.other_info (其它信息)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String otherInfo;

    /**
     * b_product_order_collage.deposit (付预定金)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private Float deposit;

    /**
     * b_product_order_collage.pay_status (支付状态：0编辑 1预支付 2支付完成)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private Integer payStatus;

    /**
     * b_product_order_collage.form_id (微信小程序发送通知模板信息中使用的form_id,由预支付后设置)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private String formId;

    /**
     * b_product_order_collage.price (下订单时产品价格)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
    private Float price;

    /**
     * b_product_order_collage.share_id (分享主键值ID,不为-1表示该订单来自于别人分享的产品)
     * @ibatorgenerated 2018-08-29 10:41:45
     */
	@IDEncryption
    private Integer shareId;
}