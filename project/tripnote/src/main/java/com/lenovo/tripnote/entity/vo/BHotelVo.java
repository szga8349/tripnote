package com.lenovo.tripnote.entity.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BHotelVo {
	 /**
     * b_hotel.name_cn (酒店名称-中文)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String nameCn;

    /**
     * b_hotel.name_en
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String nameEn;

    /**
     * b_hotel.address (地址)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String address;

    /**
     * b_hotel.type_id (酒店类型id)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Integer typeId;

    /**
     * b_hotel.star_level (酒店星级：0-未评星，1、2、3、4、5、6、7星)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Integer starLevel;

    /**
     * b_hotel.brand (酒店品牌)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String brand;

    /**
     * b_hotel.check_in_time (入住时间)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String checkInTime;

    /**
     * b_hotel.check_ou_time (退房时间)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String checkOuTime;

    /**
     * b_hotel.built_in (建成时间)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String builtIn;

    /**
     * b_hotel.repair_time (翻修时间)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String repairTime;

    /**
     * b_hotel.floor_number (楼层)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Integer floorNumber;

    /**
     * b_hotel.room_number (房间数)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Integer roomNumber;

    /**
     * b_hotel.status (状态：1-可用，0-停用)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Integer status;

    /**
     * b_hotel.city_id (酒店所在城市)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Integer cityId;

    /**
     * b_hotel.city_name (酒店所在城市)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String cityName;

    /**
     * b_hotel.create_time (纪录创建时间)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Date createTime;

    /**
     * b_hotel.create_user_id (0-system)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Integer createUserId;

    /**
     * b_hotel.lon (定位：经度)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Double lon;

    /**
     * b_hotel.lat (纬度)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private Double lat;

    /**
     * b_hotel.traffic (交通)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String traffic;

    /**
     * b_hotel.url
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String url;

    /**
     * b_hotel.imageurl
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String imageurl;

    /**
     * b_hotel.phone (电话)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String phone;
    /**
     * b_hotel.price (价格)
     * @ibatorgenerated 2018-02-02 16:06:46
     */
    private Double price;
    
    /**
     * b_hotel.supplier (供应商)
     * @ibatorgenerated 2018-02-02 16:19:55
     */
    private String supplier;

    /**
     * b_hotel.introduction (酒店介绍)
     * @ibatorgenerated 2018-01-30 15:54:59
     */
    private String introduction;
    
    
}
