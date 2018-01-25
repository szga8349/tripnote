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
    /**
     * t_tripnote_schedule_traffic.id
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Integer id;

    /**
     * t_tripnote_schedule_traffic.schedule_id
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_traffic.start_schedule_trip
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Integer startScheduleTrip;

    /**
     * t_tripnote_schedule_traffic.end_schedule_trip
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Integer endScheduleTrip;

    /**
     * t_tripnote_schedule_traffic.distance (以米为单位)
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Float distance;

    /**
     * t_tripnote_schedule_traffic.spend_time (以秒为单位)
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Long spendTime;

    /**
     * t_tripnote_schedule_traffic.traffic_type (交通类型:1-公共交通,2-汽车，3-步行，4-飞机)
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Integer trafficType;

    /**
     * t_tripnote_schedule_traffic.createtime
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Date createtime;

    /**
     * t_tripnote_schedule_traffic.create_userid (定制师用户ID)
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Integer createUserid;

    /**
     * t_tripnote_schedule_traffic.createuser_name
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private String createuserName;

   
}