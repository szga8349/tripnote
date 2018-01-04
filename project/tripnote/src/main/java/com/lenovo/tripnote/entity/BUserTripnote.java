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
public class BUserTripnote implements Serializable {
    private Integer id;

    private String title;

    private Integer status;

    private String cover;

    private Integer createUserid;

    private String createUsername;

    private Date createTime;

    private String context;

   
}