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
public class TTripnoteScheduleTrip implements Serializable {
    private Integer id;

    private Integer scheduleId;

    private Date createtime;

    private Integer createUserid;

    private String createuserName;
}