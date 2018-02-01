package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleHotelResultVo{
	
	private Integer id;
	/**
     * t_tripnote_schedule_hotel.name_cn (酒店名称-中文)
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String nameCn;

    /**
     * t_tripnote_schedule_hotel.name_en
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String nameEn;
    
    /**
     * t_tripnote_schedule_hotel.check_in_time (入住时间)
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String checkInTime;

    /**
     * t_tripnote_schedule_hotel.check_ou_time (退房时间)
     * @ibatorgenerated 2018-01-30 16:34:45
     */
    private String checkOuTime;
    /**
     * t_tripnote_schedule_hotel.check_in_type (入住类型:1入住 2退房)
     * @ibatorgenerated 2018-02-01 11:24:53
     */
    private Integer checkInType;

}
