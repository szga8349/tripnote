package com.lenovo.tripnote.webchat.entity.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lenovo.tripnote.webchat.convert.IDDeEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductVo {
    /**
     * b_product.name (产品名称)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private String name;

    /**
     * b_product.use_start_date (使用开始日期)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date useStartDate;

    /**
     * b_product.use_end_date (使用结束日期)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date useEndDate;

    /**
     * b_product.price_type (格价类型:1限时特价,2拼团,3直购)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private Integer priceType;

    /**
     * b_product.product_count (产品份数)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private Integer productCount;

    /**
     * b_product.discount_price (折扣价)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private Float discountPrice;

    /**
     * b_product.raw_price (原价)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private Float rawPrice;

    /**
     * b_product.limit_time (时间限制:单位小时)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private Integer limitTime;

    /**
     * b_product.commission (佣金)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private Float commission;

    /**
     * b_product.introduction (产品介绍)
     * @ibatorgenerated 2018-06-06 14:07:53
     */
    private String introduction;
    
    /**
     * b_product.status (产品状态:-1(下架,结束):0:编辑:1发布)
     * @ibatorgenerated 2018-06-06 16:41:07
     */
    private Integer status = 0;
    

    /**
     * b_product.image_url (产品封面图(在产品列表时展示))
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    private String imageUrl;
    
    /**
     * 图片URL
     */
    private List<String> imageUrls;
    
    /**
     * 当价格类型为团购时 需要将前端设置的团购组合以数组方式传过来
     */
    private List<BProductCollageVo> productCollages;
    
    /**
     * b_product.cost_description (费用说明)
     * @ibatorgenerated 2018-07-12 15:38:19
     */
    private String costDescription;

    /**
     * b_product.reservation_note (预订须知)
     * @ibatorgenerated 2018-07-12 15:38:19
     */
    private String reservationNote;
    /**
     * b_product.guide (实用指南)
     * @ibatorgenerated 2018-07-12 15:38:19
     */
    private String guide;
    
    /**
     * b_product.classify_id (分类主键值ID)
     * @ibatorgenerated 2018-09-06 09:49:38
     */
    @IDDeEncryption
    private Integer classifyId;

    /**
     * b_product.store_id (门店主键值ID)
     * @ibatorgenerated 2018-09-06 09:49:38
     */
    @IDDeEncryption
    private Integer storeId;
    
}
