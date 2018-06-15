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
public class BProduct {
    /**
     * b_product.id (主键ID)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
	@IDEncryption
    private Integer id;

    /**
     * b_product.name (产品名称)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private String name;

    /**
     * b_product.user_date (使用日期)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date userDate;

    /**
     * b_product.price_type (格价类型:1限时特价,2拼团,3直购)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Integer priceType;

    /**
     * b_product.product_count (产品份数)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Integer productCount;

    /**
     * b_product.discount_price (折扣价)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Float discountPrice;

    /**
     * b_product.raw_price (原价)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Float rawPrice;

    /**
     * b_product.limit_time (时间限制:单位小时)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Integer limitTime;

    /**
     * b_product.commission (佣金)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Float commission;

    /**
     * b_product.status (产品状态:-1(下架,结束):0:编辑:1发布)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Integer status;

    /**
     * b_product.image_url (产品封面图(在产品列表时展示))
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private String imageUrl;

    /**
     * b_product.create_user_name (创建者登录名)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private String createUserName;

    /**
     * b_product.create_user_id (创建者ID)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    @IDEncryption
    private Integer createUserId;

    /**
     * b_product.create_time (创建时间)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Date createTime;

    /**
     * b_product.last_update_time (最后修改时间)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private Date lastUpdateTime;

    /**
     * b_product.introduction (产品介绍)
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private String introduction;
}