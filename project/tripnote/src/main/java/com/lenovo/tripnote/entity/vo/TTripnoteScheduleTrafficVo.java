package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTrafficVo {
	
	private Integer scheduleId;

	private Integer startScheduleTrip;

	private Integer endScheduleTrip;

	private Float distance;

	private Long spendTime;
	
	   /**
     * t_tripnote_schedule_traffic.arrival_time (到达时间)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private String arrivalTime;

    /**
     * t_tripnote_schedule_traffic.shift (班次)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private String shift;


    /**
     * t_tripnote_schedule_traffic.start_time (出发时间)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private String startTime;
    
    /**
     * t_tripnote_schedule_traffic.traffic_type (交通类型:1-公共交通,2-汽车，3-步行，4-飞机,5-火车,6-轮渡,7-巴士,8-地铁)
     * @ibatorgenerated 2018-01-29 11:35:30
     */
    private Integer trafficType;

}
