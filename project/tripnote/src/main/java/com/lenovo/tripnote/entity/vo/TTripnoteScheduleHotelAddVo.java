package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleHotelAddVo extends TTripnoteScheduleTripAddVo{
    /**
     * t_tripnote_schedule_hotel.check_in_type (入住类型:1入住 2退房)
     * @ibatorgenerated 2018-02-01 11:24:53
     */
     private Integer checkInType;
}
