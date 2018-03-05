package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleTripHotelAddVo {
	private List<TTripnoteScheduleHotelAddVo> scheduleHotels;
	private String checkIn;
	private String checkOut;
	private String remark;
}
