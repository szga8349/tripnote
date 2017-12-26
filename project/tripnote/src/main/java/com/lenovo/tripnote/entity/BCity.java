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
public class BCity extends BCityKey implements Serializable {
    private String nameEn;

    private Integer regionId;

    private Integer countryId;

    private String countryNameCn;

    private String countryNameEn;

    private Integer status;

    private Integer isHot;

    private Date createTime;

    private Integer createUserId;

   
}