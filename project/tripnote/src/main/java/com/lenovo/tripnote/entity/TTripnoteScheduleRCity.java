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
     * t_tripnote_schedule_r_city.id
     * @ibatorgenerated 2018-01-11 17:32:53
     */
    private Integer id;

    /**
     * t_tripnote_schedule_r_city.city_id
     * @ibatorgenerated 2018-01-11 17:32:53
     */
    private Integer cityId;

    /**
     * t_tripnote_schedule_r_city.schedule_id
     * @ibatorgenerated 2018-01-11 17:32:53
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_r_city.create_time
     * @ibatorgenerated 2018-01-11 17:32:53
     */
    private Date createTime;
}