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
public class BPoi implements Serializable {
    /**
     * b_poi.id
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Integer id;

    /**
     * b_poi.name_cn (名称-中文)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String nameCn;

    /**
     * b_poi.name_en
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String nameEn;

    /**
     * b_poi.address (详细地址)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String address;

    /**
     * b_poi.lon (定位：经度)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Double lon;

    /**
     * b_poi.lat (地点纬度)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Double lat;

    /**
     * b_poi.city_id (地址所在城市主键值)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Integer cityId;

    /**
     * b_poi.cityname (地址所在城市名)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String cityname;

    /**
     * b_poi.type (1-餐饮2-游览3-购物4-娱乐)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Integer type;

    /**
     * b_poi.permissions_type (权限类型,-1个人私有,1管理员可以查看)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Integer permissionsType;

    /**
     * b_poi.phone (电话)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String phone;

    /**
     * b_poi.open_time (开发时间)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String openTime;

    /**
     * b_poi.consum_description (消费说明)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String consumDescription;

    /**
     * b_poi.traffic_instructions (交通说明)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String trafficInstructions;

    /**
     * b_poi.time_reference (用时参考)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String timeReference;

    /**
     * b_poi.price (参考价)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Double price;

    /**
     * b_poi.address_instrations (地点简介)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String addressInstrations;

    /**
     * b_poi.supplier (供应商)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String supplier;

    /**
     * b_poi.create_time (纪录创建时间)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Date createTime;

    /**
     * b_poi.create_user_id (0-system)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private Integer createUserId;

    /**
     * b_poi.guide (实用指南)
     * @ibatorgenerated 2018-01-22 16:27:38
     */
    private String guide;
}