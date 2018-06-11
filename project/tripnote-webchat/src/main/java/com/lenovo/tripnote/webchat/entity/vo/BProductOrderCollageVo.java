package com.lenovo.tripnote.webchat.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BProductOrderCollageVo {
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
    private Integer collageStatus = 0;
}