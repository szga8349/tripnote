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
public class BCountry implements Serializable {
    private Integer id;

    private String regionId;

    private String code;

    private String nameCn;

    private String nameEn;

    private Integer status;

    private Date createTime;

    private Integer createUserId;

}