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
public class BAirportTrainStation implements Serializable {
    private Integer id;

    private String nameCn;

    private String nameEn;

    private Integer citeId;

    private Integer type;

    private String address;

    private String location;

    private Integer status;

    private Integer createUserId;

    private Date createTime;

    private String introduction;

}