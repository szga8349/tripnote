package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDEncryption;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BProductShareVo {
    /**
     * b_product_share.product_id (分享产品ID)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    @IDEncryption
    private Integer productId;
    /**
     * b_product_share.share_level (分享层级 默认从1开始)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    private Integer shareLevel;

    /**
     * b_product_share.parent_share_id (分享父节点ID,没有父节点设置为0)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    private Integer parentShareId;
}
