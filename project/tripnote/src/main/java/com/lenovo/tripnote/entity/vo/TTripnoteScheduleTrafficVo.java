package com.lenovo.tripnote.entity.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTrafficVo {
	
	private Integer scheduleId;

	/**
     * t_tripnote_schedule_traffic.start_schedule_type (起点日程行程类型)
     * @ibatorgenerated 2018-02-01 14:07:39
     */
    private Integer startScheduleType;

    /**
     * t_tripnote_schedule_traffic.start_schedule_trip
     * @ibatorgenerated 2018-02-01 14:07:39
     */
    private Integer startScheduleTrip;

    /**
     * t_tripnote_schedule_traffic.end_schedule_type (终点日程行程类型)
     * @ibatorgenerated 2018-02-01 14:07:39
     */
    private Integer endScheduleType;
    

	private Integer endScheduleTrip;

	private Float distance;

	private Long spendTime;
	
	   /**
     * t_tripnote_schedule_traffic.arrival_time (到达时间)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private Date arrivalTime;

    /**
     * t_tripnote_schedule_traffic.shift (班次)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private String shift;


    /**
     * t_tripnote_schedule_traffic.start_time (出发时间)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private Date startTime;
    
    /**
     * t_tripnote_schedule_traffic.traffic_type (交通类型:1-公共交通,2-汽车，3-步行，4-飞机,5-火车,6-轮渡,7-巴士,8-地铁)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private Integer trafficType;
    

    /**
     * t_tripnote_schedule_traffic.traffic_guide (交通指引,传入json数据)
     * @ibatorgenerated 2018-03-12 16:18:41
     */
    private String trafficGuide;
}
