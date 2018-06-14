package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductShare  {
    /**
     * b_product_share.id (分享主键ID)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    private Integer id;

    /**
     * b_product_share.product_id (分享产品ID)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    private Integer productId;

    /**
     * b_product_share.share_user_id (分享者用户ID)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    private Integer shareUserId;

    /**
     * b_product_share.share_user_name (分享者用户名称)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    private String shareUserName;

    /**
     * b_product_share.share_time (分享时间)
     * @ibatorgenerated 2018-06-14 18:44:28
     */
    private Date shareTime;

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