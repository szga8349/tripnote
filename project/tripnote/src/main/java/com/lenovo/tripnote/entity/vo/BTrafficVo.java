package com.lenovo.tripnote.entity.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BTrafficVo {

    /**
     * b_traffic.name_cn (名称-中文)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private String nameCn;

    /**
     * b_traffic.name_en
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private String nameEn;

    /**
     * b_traffic.price (价格)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Double price;

    /**
     * b_traffic.supplier (供应商)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private String supplier;

    /**
     * b_traffic.create_user_id (0-system)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Integer createUserId;

    /**
     * b_traffic.type (1-飞机2-火车3-轮船4-巴士)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Integer type;

    /**
     * b_traffic.end_cityid (第几天行程的)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Integer endCityid;

    /**
     * b_traffic.start_cityid
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Integer startCityid;

    /**
     * b_traffic.start_name
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private String startName;

    /**
     * b_traffic.end_name
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private String endName;

    /**
     * b_traffic.start_time
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Date startTime;

    /**
     * b_traffic.end_time
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Date endTime;

    /**
     * b_traffic.model (飞机:机型 火车:车型)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private String model;
}
