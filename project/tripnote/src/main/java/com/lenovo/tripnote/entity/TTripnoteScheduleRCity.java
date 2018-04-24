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
     * @ibatorgenerated 2018-04-24 15:26:08
     */
    private Integer id;

    /**
     * t_tripnote_schedule_r_city.city_id
     * @ibatorgenerated 2018-04-24 15:26:08
     */
    private Integer cityId;

    /**
     * t_tripnote_schedule_r_city.schedule_id
     * @ibatorgenerated 2018-04-24 15:26:08
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_r_city.create_time
     * @ibatorgenerated 2018-04-24 15:26:08
     */
    private Date createTime;

    /**
     * t_tripnote_schedule_r_city.position (位置:-1开始城市标识,0行程中城市,1达到城市标识)
     * @ibatorgenerated 2018-04-24 15:26:08
     */
    private Integer position;
}