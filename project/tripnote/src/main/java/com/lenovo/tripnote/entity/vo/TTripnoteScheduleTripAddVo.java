package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTripAddVo {
	
	/**
     * t_tripnote_schedule_trip.schedule_id (日志主键值)
     * @ibatorgenerated 2018-01-23 14:54:44
     */
    private Integer scheduleId;
    
    
    /**
     * 数据来源主键ID
     */
    private Integer sourceId;
    
    private Integer sort;

}
