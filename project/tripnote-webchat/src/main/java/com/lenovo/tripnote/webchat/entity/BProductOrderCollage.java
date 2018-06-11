package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductOrderCollage {
    /**
     * b_product_order_collage.id (订单拼团主键ID)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Integer id;

    /**
     * b_product_order_collage.collage_user_id (参与拼团用户ID)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Integer collageUserId;

    /**
     * b_product_order_collage.collage_user_name (参与拼团用户名称)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private String collageUserName;

    /**
     * b_product_order_collage.product_collage_id (产品对应的拼团类型ID)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Integer productCollageId;

    /**
     * b_product_order_collage.product_id (拼团产品ID)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Integer productId;

    /**
     * b_product_order_collage.collage_status (拼团状态:0:拼团中,1:拼团结束(达到拼团人数或达到拼团时限))
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Integer collageStatus;

    /**
     * b_product_order_collage.create_user_id
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Integer createUserId;

    /**
     * b_product_order_collage.create_user_name (拼团创建者登录名)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private String createUserName;

    /**
     * b_product_order_collage.create_time (创建时间)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Date createTime;

    /**
     * b_product_order_collage.collage_id (拼团主键ID)
     * @ibatorgenerated 2018-06-07 13:48:24
     */
    private Integer collageId;
}