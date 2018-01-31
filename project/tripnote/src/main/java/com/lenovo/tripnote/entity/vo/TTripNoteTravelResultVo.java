package com.lenovo.tripnote.entity.vo;

import java.util.List;

import com.lenovo.tripnote.entity.TTripnoteScheduleTrip;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripNoteTravelResultVo extends TTripNoteResultVo {

	private List<TCustemVo> custems;
	
	 /**
     * 日程关联的当日行程信息
     */
    private List<TTripnoteScheduleTrip> scheduletrips;
    
    /**
     * 日程关联的酒店信息
     */
    private List<TTripnoteScheduleHotelResultVo> scheduleHotels;
    
    /**
     * 日程关联的交通信息
     */
    private List<TTripnoteScheduleTrafficResultVo> traffics;
	
}