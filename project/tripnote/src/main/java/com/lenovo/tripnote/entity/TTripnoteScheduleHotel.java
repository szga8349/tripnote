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
public class TTripnoteScheduleHotel implements Serializable {
    /**
     * t_tripnote_schedule_hotel.id
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer id;

    /**
     * t_tripnote_schedule_hotel.name_cn (酒店名称-中文)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String nameCn;

    /**
     * t_tripnote_schedule_hotel.name_en
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String nameEn;

    /**
     * t_tripnote_schedule_hotel.address (地址)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String address;

    /**
     * t_tripnote_schedule_hotel.type_id (酒店类型id)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer typeId;

    /**
     * t_tripnote_schedule_hotel.star_level (酒店星级：0-未评星，1、2、3、4、5、6、7星)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer starLevel;

    /**
     * t_tripnote_schedule_hotel.brand (酒店品牌)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String brand;

    /**
     * t_tripnote_schedule_hotel.check_in_time (入住时间)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String checkInTime;

    /**
     * t_tripnote_schedule_hotel.check_in_type (入住类型:1入住 2退房)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer checkInType;

    /**
     * t_tripnote_schedule_hotel.check_ou_time (退房时间)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String checkOuTime;

    /**
     * t_tripnote_schedule_hotel.built_in (建成时间)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String builtIn;

    /**
     * t_tripnote_schedule_hotel.repair_time (翻修时间)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String repairTime;

    /**
     * t_tripnote_schedule_hotel.floor_number (楼层)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer floorNumber;

    /**
     * t_tripnote_schedule_hotel.room_number (房间数)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer roomNumber;

    /**
     * t_tripnote_schedule_hotel.status (状态：1-可用，0-停用)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer status;

    /**
     * t_tripnote_schedule_hotel.city_id (酒店所在城市)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer cityId;

    /**
     * t_tripnote_schedule_hotel.city_name (酒店所在城市)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String cityName;

    /**
     * t_tripnote_schedule_hotel.create_time (纪录创建时间)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Date createTime;

    /**
     * t_tripnote_schedule_hotel.create_user_id (0-system)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer createUserId;

    /**
     * t_tripnote_schedule_hotel.lon (定位：经度)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Double lon;

    /**
     * t_tripnote_schedule_hotel.lat (纬度)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Double lat;

    /**
     * t_tripnote_schedule_hotel.traffic (交通)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String traffic;

    /**
     * t_tripnote_schedule_hotel.url
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String url;

    /**
     * t_tripnote_schedule_hotel.imageurl
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String imageurl;

    /**
     * t_tripnote_schedule_hotel.phone (电话)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String phone;

    /**
     * t_tripnote_schedule_hotel.schedule_id
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_hotel.hotal_id
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Integer hotalId;

    /**
     * t_tripnote_schedule_hotel.price (价格)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private Double price;

    /**
     * t_tripnote_schedule_hotel.supplier (供应商)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String supplier;

    /**
     * t_tripnote_schedule_hotel.introduction (酒店介绍)
     * @ibatorgenerated 2018-02-02 16:19:54
     */
    private String introduction;

}