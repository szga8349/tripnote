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
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer id;

    /**
     * t_tripnote_schedule_traffic.schedule_id
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer scheduleId;

    /**
     * t_tripnote_schedule_traffic.start_schedule_type (起点日程行程类型)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer startScheduleType;

    /**
     * t_tripnote_schedule_traffic.start_schedule_trip
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer startScheduleTrip;

    /**
     * t_tripnote_schedule_traffic.end_schedule_type (终点日程行程类型)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer endScheduleType;

    /**
     * t_tripnote_schedule_traffic.end_schedule_trip
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer endScheduleTrip;

    /**
     * t_tripnote_schedule_traffic.distance (以米为单位)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Float distance;

    /**
     * t_tripnote_schedule_traffic.spend_time (以秒为单位)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Long spendTime;

    /**
     * t_tripnote_schedule_traffic.traffic_type (交通类型:1-公共交通,2-汽车，3-步行，4-飞机,5-火车,6-轮渡,7-巴士,8-地铁)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer trafficType;

    /**
     * t_tripnote_schedule_traffic.arrival_time (到达时间)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Date arrivalTime;

    /**
     * t_tripnote_schedule_traffic.createtime
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Date createtime;

    /**
     * t_tripnote_schedule_traffic.create_userid (定制师用户ID)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Integer createUserid;

    /**
     * t_tripnote_schedule_traffic.shift (班次)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private String shift;

    /**
     * t_tripnote_schedule_traffic.createuser_name
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private String createuserName;

    /**
     * t_tripnote_schedule_traffic.start_time (出发时间)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private Date startTime;

    /**
     * t_tripnote_schedule_traffic.traffic_guide (交通指引,传入json数据)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private String trafficGuide;
}