package com.lenovo.tripnote.entity.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTripVo {

    /**
     * t_tripnote_schedule_trip.poi_id (POI)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Integer poiId;

    /**
     * t_tripnote_schedule_trip.schedule_id (日志主键值)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_trip.name_cn (名称-中文)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String nameCn;

    /**
     * t_tripnote_schedule_trip.name_en
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String nameEn;

    /**
     * t_tripnote_schedule_trip.address (详细地址)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String address;

    /**
     * t_tripnote_schedule_trip.lon (定位：经度)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Double lon;

    /**
     * t_tripnote_schedule_trip.lat (地点纬度)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Double lat;

    /**
     * t_tripnote_schedule_trip.city_id (地址所在城市主键值)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Integer cityId;

    /**
     * t_tripnote_schedule_trip.cityname (地址所在城市名)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String cityname;

    /**
     * t_tripnote_schedule_trip.type (1-餐饮2-游览3-购物4-娱乐)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Integer type;

    /**
     * t_tripnote_schedule_trip.permissions_type (权限类型,-1个人私有,1管理员可以查看)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Integer permissionsType;

    /**
     * t_tripnote_schedule_trip.phone (电话)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String phone;

    /**
     * t_tripnote_schedule_trip.open_time (开发时间)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String openTime;

    /**
     * t_tripnote_schedule_trip.consum_description (消费说明)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String consumDescription;

    /**
     * t_tripnote_schedule_trip.traffic_instructions (交通说明)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String trafficInstructions;

    /**
     * t_tripnote_schedule_trip.time_reference (用时参考)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String timeReference;

    /**
     * t_tripnote_schedule_trip.price (参考价)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Double price;

    /**
     * t_tripnote_schedule_trip.address_instrations (地点简介)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String addressInstrations;

    /**
     * t_tripnote_schedule_trip.supplier (供应商)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String supplier;

    /**
     * t_tripnote_schedule_trip.imageurl (缩列图地址)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String imageurl;

    /**
     * t_tripnote_schedule_trip.url (网址)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String url;

    /**
     * t_tripnote_schedule_trip.guide (实用指南)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private String guide;

    /**
     * t_tripnote_schedule_trip.sort (用于前端排序字段)
     * @ibatorgenerated 2018-03-09 15:43:37
     */
    private Integer sort;
    
    
    //城际交通增加的属性
    /**
     * b_traffic.type (1-飞机2-火车3-轮船4-巴士)
     * @ibatorgenerated 2018-03-08 16:28:36
     */
    private Integer traffictype;

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
    
    /**
     * b_traffic.start_lon (开始地经度)
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    private Double startLon;

    /**
     * b_traffic.start_lat (开始地纬度)
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    private Double startLat;

    /**
     * b_traffic.end_lon (终点地经度)
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    private Double endLon;

    /**
     * b_traffic.end_lat (终点地纬度)
     * @ibatorgenerated 2018-03-09 14:13:21
     */
    private Double endLat;
    /**
     * b_traffic.time_consume (耗时)
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    private String timeConsume;

}
