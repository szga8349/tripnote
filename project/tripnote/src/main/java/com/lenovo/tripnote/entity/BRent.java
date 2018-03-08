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
public class BRent implements Serializable {
    /**
     * b_rent.id
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private Integer id;

    /**
     * b_rent.name_cn (名称-中文)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private String nameCn;

    /**
     * b_rent.name_en
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private String nameEn;

    /**
     * b_rent.imageurl (汽车缩列图地址url)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private String imageurl;

    /**
     * b_rent.price (价格)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private Double price;

    /**
     * b_rent.supplier (供应商)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private String supplier;

    /**
     * b_rent.create_time (纪录创建时间)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private Date createTime;

    /**
     * b_rent.create_user_id (0-system)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private Integer createUserId;

    /**
     * b_rent.model (自动挡还是手动挡)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private String model;

    /**
     * b_rent.pedestal (座位数)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private Integer pedestal;

    /**
     * b_rent.doors (门数)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private Integer doors;

    /**
     * b_rent.suitcases (行李箱数)
     * @ibatorgenerated 2018-03-08 16:28:37
     */
    private Integer suitcases;
}