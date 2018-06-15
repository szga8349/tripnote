package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDDeEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductCollectionVo {
	 /**
     * b_product_collection.collection_product_id (收藏产品ID)
     * @ibatorgenerated 2018-06-15 09:12:43
     */
	@IDDeEncryption
    private Integer collectionProductId;

    /**
     * b_product_collection.collection_remark (收藏备注)
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    private String collectionRemark;
}
