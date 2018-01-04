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
public class TCustomer implements Serializable {
    private Integer id;

    private String name;

    private Integer createUserId;

    private Date createTime;

    private Integer status;

    private Integer sex;

    private String phone1;

    private String phone2;

    private String phone3;

    private String email;

    private String qq;

    private String weixin;

}