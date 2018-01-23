package com.lenovo.tripnote.entity;

import java.io.Serializable;
import java.util.Date;

public class BPoi implements Serializable {
    /**
     * b_poi.id
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Integer id;

    /**
     * b_poi.name_cn (名称-中文)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String nameCn;

    /**
     * b_poi.name_en
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String nameEn;

    /**
     * b_poi.address (详细地址)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String address;

    /**
     * b_poi.lon (定位：经度)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Double lon;

    /**
     * b_poi.lat (地点纬度)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Double lat;

    /**
     * b_poi.city_id (地址所在城市主键值)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Integer cityId;

    /**
     * b_poi.cityname (地址所在城市名)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String cityname;

    /**
     * b_poi.type (1-餐饮2-游览3-购物4-娱乐)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Integer type;

    /**
     * b_poi.permissions_type (权限类型,-1个人私有,1管理员可以查看)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Integer permissionsType;

    /**
     * b_poi.phone (电话)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String phone;

    /**
     * b_poi.open_time (开发时间)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String openTime;

    /**
     * b_poi.consum_description (消费说明)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String consumDescription;

    /**
     * b_poi.traffic_instructions (交通说明)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String trafficInstructions;

    /**
     * b_poi.time_reference (用时参考)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String timeReference;

    /**
     * b_poi.price (参考价)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Double price;

    /**
     * b_poi.address_instrations (地点简介)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String addressInstrations;

    /**
     * b_poi.supplier (供应商)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String supplier;

    /**
     * b_poi.imageurl (缩列图地址url)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String imageurl;

    /**
     * b_poi.url (网址)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String url;

    /**
     * b_poi.create_time (纪录创建时间)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Date createTime;

    /**
     * b_poi.create_user_id (0-system)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private Integer createUserId;

    /**
     * b_poi.guide (实用指南)
     * @ibatorgenerated 2018-01-23 14:45:28
     */
    private String guide;

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPermissionsType() {
        return permissionsType;
    }

    public void setPermissionsType(Integer permissionsType) {
        this.permissionsType = permissionsType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getConsumDescription() {
        return consumDescription;
    }

    public void setConsumDescription(String consumDescription) {
        this.consumDescription = consumDescription;
    }

    public String getTrafficInstructions() {
        return trafficInstructions;
    }

    public void setTrafficInstructions(String trafficInstructions) {
        this.trafficInstructions = trafficInstructions;
    }

    public String getTimeReference() {
        return timeReference;
    }

    public void setTimeReference(String timeReference) {
        this.timeReference = timeReference;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddressInstrations() {
        return addressInstrations;
    }

    public void setAddressInstrations(String addressInstrations) {
        this.addressInstrations = addressInstrations;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}