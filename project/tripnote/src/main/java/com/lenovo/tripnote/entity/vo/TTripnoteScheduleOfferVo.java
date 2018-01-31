package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleOfferVo {
	
	private Integer id;
	 /**
     * t_tripnote_schedule_offer.name_cn (名称-中文)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private String nameCn;

    /**
     * t_tripnote_schedule_offer.name_en
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private String nameEn;

    /**
     * t_tripnote_schedule_offer.num
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer num;

    /**
     * t_tripnote_schedule_offer.price (价格)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Double price;

    /**
     * t_tripnote_schedule_offer.supplier (供应商)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private String supplier;

    /**
     * t_tripnote_schedule_offer.type (1-餐饮2-游览3-购物4-娱乐5-产品,6-酒店)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer type;

    /**
     * t_tripnote_schedule_offer.indexdate (第几天行程的)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer indexdate;
	

}
