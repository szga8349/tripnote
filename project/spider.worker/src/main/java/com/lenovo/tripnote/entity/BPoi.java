package com.lenovo.tripnote.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
public class BPoi implements Serializable {
    /**
     * b_poi.id
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Integer id;

    /**
     * b_poi.name_cn (名称-中文)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String nameCn;

    /**
     * b_poi.name_en
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String nameEn;

    /**
     * b_poi.address (详细地址)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String address;

    /**
     * b_poi.lat (定位：经度)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Double lat;

    /**
     * b_poi.lon (地点纬度)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Double lon;

    /**
     * b_poi.city_id (地址所在城市主键值)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Integer cityId;

    /**
     * b_poi.cityname (地址所在城市名)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String cityname;

    /**
     * b_poi.type (1-餐饮2-游览3-购物4-娱乐5-产品6-酒店)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Integer type;

    /**
     * b_poi.permissions_type (权限类型,-1个人私有,1管理员可以查看)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Integer permissionsType;

    /**
     * b_poi.phone (电话)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String phone;

    /**
     * b_poi.open_time (开发时间)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String openTime;

    /**
     * b_poi.time_reference (用时参考)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String timeReference;

    /**
     * b_poi.price (参考价)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Double price;

    /**
     * b_poi.supplier (供应商)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String supplier;

    /**
     * b_poi.imageurl (缩列图地址url)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String imageurl;

    /**
     * b_poi.url (网址)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private String url;

    /**
     * b_poi.create_time (纪录创建时间)
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Date createTime;

    /**
     * b_poi.create_user_id
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    private Integer createUserId;

}