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
public class BHotel implements Serializable {
    private Integer id;

    private String nameCn;

    private String nameEn;

    private String address;

    private String location;

    private Integer typeId;

    private Integer starLevel;

    private String brand;

    private String checkInTime;

    private String checkOuTime;

    private String builtIn;

    private String repairTime;

    private Integer floorNumber;

    private Integer roomNumber;

    private Integer status;

    private Integer cityId;

    private String cityName;

    private Date createTime;

    private Integer createUserId;

    private String introduction;

  
}