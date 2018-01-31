package com.lenovo.tripnote.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleOffer implements Serializable {
    /**
     * t_tripnote_schedule_offer.id
     * @ibatorgenerated 2018-01-31 10:40:22
     */
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
     * t_tripnote_schedule_offer.create_time (纪录创建时间)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Date createTime;

    /**
     * t_tripnote_schedule_offer.create_user_id (0-system)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer createUserId;

    /**
     * t_tripnote_schedule_offer.type (1-餐饮2-游览3-购物4-娱乐5-产品,6-酒店)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer type;

    /**
     * t_tripnote_schedule_offer.tripnote_id
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer tripnoteId;

    /**
     * t_tripnote_schedule_offer.indexdate (第几天行程的)
     * @ibatorgenerated 2018-01-31 10:40:22
     */
    private Integer indexdate;
}