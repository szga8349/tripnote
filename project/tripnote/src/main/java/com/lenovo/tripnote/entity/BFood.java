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
public class BFood implements Serializable {
    private Integer id;

    private String nameCn;

    private String nameEn;

    private String address;

    private String location;

    private String url;

    private String phone;

    private Integer status;

    private Long cityId;

    private String cityName;

    private Date createTime;

    private Long createUserId;

    private String price;

    private String introduction;

}