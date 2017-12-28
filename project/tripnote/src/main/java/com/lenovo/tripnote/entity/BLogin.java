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
public class BLogin implements Serializable {
    private Long loginid;

    private String loginip;

    private String device;

    private Long userid;

    private String loginname;

    private Date logintime;

    private Date loginouttime;

    private Integer status;

   
}