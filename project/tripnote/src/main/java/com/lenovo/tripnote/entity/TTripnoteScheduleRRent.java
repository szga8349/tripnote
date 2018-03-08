package com.lenovo.tripnote.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleRRent implements Serializable {
    /**
     * t_tripnote_schedule_r_rent.rentid
     * @ibatorgenerated 2018-03-08 16:28:38
     */
    private Integer rentid;

    /**
     * t_tripnote_schedule_r_rent.schedule_id
     * @ibatorgenerated 2018-03-08 16:28:38
     */
    private Integer scheduleId;
}