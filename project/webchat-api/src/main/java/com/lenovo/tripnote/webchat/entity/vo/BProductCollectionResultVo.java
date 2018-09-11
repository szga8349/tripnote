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
public class BProductCollectionResultVo extends BProduct{
    /**
     * b_product_collection.collection_product_id (收藏产品ID)
     * @ibatorgenerated 2018-06-15 09:12:43
     */
	@IDEncryption
    private Integer collectionProductId;

    /**
     * b_product_collection.collection_user_id (收藏用户ID)
     * @ibatorgenerated 2018-06-15 09:12:43
     */
	@IDEncryption
    private Integer collectionUserId;

    /**
     * b_product_collection.collection_user_name (收藏用户登录名)
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    private String collectionUserName;

    /**
     * b_product_collection.collection_create_time (收藏记录创建时间)
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    private Date collectionCreateTime;

    /**
     * b_product_collection.collection_remark (收藏备注)
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    private String collectionRemark;
}
