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
public class TTripnoteScheduleRCity implements Serializable {
    /**
     * t_tripnote_schedule_r_city.city_id
     * @ibatorgenerated 2018-01-10 14:42:37
     */
    private Integer cityId;

    /**
     * t_tripnote_schedule_r_city.schedule_id
     * @ibatorgenerated 2018-01-10 14:42:37
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_r_city.create_time
     * @ibatorgenerated 2018-01-10 14:42:37
     */
    private Date createTime;

}