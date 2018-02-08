package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleHotelResultVo{
	
	private Integer id;
	/**
     * t_tripnote_schedule_hotel.name_cn (酒店名称-中文)
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String nameCn;

    /**
     * t_tripnote_schedule_hotel.name_en
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String nameEn;
    
    /**
     * t_tripnote_schedule_hotel.check_in_time (入住时间)
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String checkInTime;

    /**
     * t_tripnote_schedule_hotel.check_ou_time (退房时间)
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String checkOuTime;
    /**
     * t_tripnote_schedule_hotel.check_in_type (入住类型:1入住 2退房)
     * @ibatorgenerated 2018-02-01 11:24:53
     */
    private Integer checkInType;
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
     * b_hotel.type (与poi的type类型想对应,6标识酒店信息)
     * @ibatorgenerated 2018-02-02 16:31:36
     */
    private Integer type;
    /**
     * b_hotel.lon (定位：经度)
     * @ibatorgenerated 2018-02-02 16:31:36
     */
    private Double lon;

    /**
     * b_hotel.lat (纬度)
     * @ibatorgenerated 2018-02-02 16:31:36
     */
    private Double lat;
    
    
    /**
     * b_hotel.introduction (酒店介绍)
     * @ibatorgenerated 2018-02-02 16:31:36
     */
    private String introduction;
    
    /**
     * b_hotel.imageurl
     * @ibatorgenerated 2018-02-02 16:31:36
     */
    private String imageurl;
    

    /**
     * t_tripnote_schedule_hotel.hotal_id
     * @ibatorgenerated 2018-02-02 16:31:35
     */
    private Integer hotelId;

}
