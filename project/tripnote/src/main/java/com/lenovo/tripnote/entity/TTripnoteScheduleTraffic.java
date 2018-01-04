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
public class TTripnoteScheduleTraffic implements Serializable {
    private Integer id;

    private Integer scheduleId;

    private Integer startScheduleTrip;

    private Integer endScheduleTrip;

    private Float distance;

    private Long spendTime;

    private Date createtime;

    private Integer createUserid;

    private String createuserName;

}