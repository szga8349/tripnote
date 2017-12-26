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
public class BAccount implements Serializable {
    private Integer id;

    private String loginName;

    private String loginPassword;

    private Integer loginNameType;

    private String realName;

    private Integer sex;

    private Integer accountType;

    private Integer status;

    private String phone;

    private String email;

    private String address;

    private Integer level;

    private Integer score;

    private Date createTime;

    private Integer createUserId;

}