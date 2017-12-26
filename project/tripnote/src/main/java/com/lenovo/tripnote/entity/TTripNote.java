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
public class TTripNote extends TTripNoteKey implements Serializable {
    private String title;

    private Date startDate;

    private Date endDate;

    private Integer days;

    private String customer;

    private String destination;

    private Date createTime;

    private Integer createUserId;

    private Integer status;

   
}