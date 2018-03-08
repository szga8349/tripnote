package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BRentVo {

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
