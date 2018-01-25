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
     * t_tripnote_schedule_traffic.traffic_type (交通类型:1-公共交通,2-汽车，3-步行，4-飞机)
     * @ibatorgenerated 2018-01-25 16:25:06
     */
    private Integer trafficType;

}
