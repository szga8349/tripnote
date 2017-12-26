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
public class BScenic implements Serializable {
    private Integer id;

    private String nameCn;

    private String nameEn;

    private Integer cityId;

    private String cityName;

    private String address;

    private String location;

    private String phone;

    private String ticket;

    private String url;

    private String openingHours;

    private String transport;

    private String usedTime;

    private Integer index;

    private Integer grade;

    private Integer status;

    private Integer isHot;

    private Date createTime;

    private Integer createUserId;

    private String introduction;

   
}