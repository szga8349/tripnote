package com.lenovo.tripnote.webchat.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductCollageVo {
	/**
     * b_product_collage.people_num (拼团人数)
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    private Integer peopleNum;

    /**
     * b_product_collage.collage_price (拼团价格)
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    private Float collagePrice;

    /**
     * b_product_collage.collage_limit_time (拼团时限：单位小时)
     * @ibatorgenerated 2018-06-06 14:07:51
     */
    private Integer collageLimitTime;

}
