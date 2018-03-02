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
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer id;

    /**
     * t_tripnote_schedule_hotel.name_cn (酒店名称-中文)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String nameCn;

    /**
     * t_tripnote_schedule_hotel.name_en
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String nameEn;

    /**
     * t_tripnote_schedule_hotel.address (地址)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String address;

    /**
     * t_tripnote_schedule_hotel.type_id (酒店类型id)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer typeId;

    /**
     * t_tripnote_schedule_hotel.star_level (酒店星级：0-未评星，1、2、3、4、5、6、7星)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer starLevel;

    /**
     * t_tripnote_schedule_hotel.brand (酒店品牌)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String brand;

    /**
     * t_tripnote_schedule_hotel.check_in_time (入住时间)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String checkInTime;

    /**
     * t_tripnote_schedule_hotel.check_in_type (入住类型:1入住 2退房)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer checkInType;

    /**
     * t_tripnote_schedule_hotel.check_ou_time (退房时间)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String checkOuTime;

    /**
     * t_tripnote_schedule_hotel.built_in (建成时间)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String builtIn;

    /**
     * t_tripnote_schedule_hotel.repair_time (翻修时间)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String repairTime;

    /**
     * t_tripnote_schedule_hotel.floor_number (楼层)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer floorNumber;

    /**
     * t_tripnote_schedule_hotel.room_number (房间数)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer roomNumber;

    /**
     * t_tripnote_schedule_hotel.status (状态：1-可用，0-停用)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer status;

    /**
     * t_tripnote_schedule_hotel.city_id (酒店所在城市)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer cityId;

    /**
     * t_tripnote_schedule_hotel.city_name (酒店所在城市)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String cityName;

    /**
     * t_tripnote_schedule_hotel.create_time (纪录创建时间)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Date createTime;

    /**
     * t_tripnote_schedule_hotel.create_user_id (0-system)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer createUserId;

    /**
     * t_tripnote_schedule_hotel.lon (定位：经度)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Double lon;

    /**
     * t_tripnote_schedule_hotel.lat (纬度)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Double lat;

    /**
     * t_tripnote_schedule_hotel.traffic (交通)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String traffic;

    /**
     * t_tripnote_schedule_hotel.url
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String url;

    /**
     * t_tripnote_schedule_hotel.imageurl
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String imageurl;

    /**
     * t_tripnote_schedule_hotel.phone (电话)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String phone;

    /**
     * t_tripnote_schedule_hotel.schedule_id
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_hotel.hotel_id
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer hotelId;

    /**
     * t_tripnote_schedule_hotel.price (价格)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Double price;

    /**
     * t_tripnote_schedule_hotel.supplier (供应商)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String supplier;

    /**
     * t_tripnote_schedule_hotel.type (与poi的type类型想对应,6标识酒店信息)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private Integer type;

    /**
     * t_tripnote_schedule_hotel.remark (备注)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String remark;

    /**
     * t_tripnote_schedule_hotel.introduction (酒店介绍)
     * @ibatorgenerated 2018-03-02 15:01:18
     */
    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Integer getCheckInType() {
        return checkInType;
    }

    public void setCheckInType(Integer checkInType) {
        this.checkInType = checkInType;
    }

    public String getCheckOuTime() {
        return checkOuTime;
    }

    public void setCheckOuTime(String checkOuTime) {
        this.checkOuTime = checkOuTime;
    }

    public String getBuiltIn() {
        return builtIn;
    }

    public void setBuiltIn(String builtIn) {
        this.builtIn = builtIn;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}