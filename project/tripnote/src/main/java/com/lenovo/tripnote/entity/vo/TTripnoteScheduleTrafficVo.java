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

}
