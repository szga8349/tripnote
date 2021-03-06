package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**定制日程详细信息
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class TTripNoteScheduleResultVo extends TTripNoteScheduleVo{
    private Integer id;
    
    /**
     * 定制日程关联的目的地信息
     */
    private List<TTripnoteScheduleRCityVo> citys;
    /**
     * 日程关联的交通信息
     */
    private List<TTripnoteScheduleTrafficResultVo> traffics;
    /**
     * 日程关联的当日行程信息
     */
    private List<TTripnoteScheduleTripResultVo> scheduletrips;
    /**
     * 日程关联的定制师笔记信息
     */
    private List<BUserTripnoteResultVo> usertripnotes;
    
    /**
     * 日程关联的酒店信息
     */
    private List<TTripnoteScheduleHotelResultVo> scheduleHotels;
    
    
    /**
     * 日程关联的租车信息
     */
    private List<TTripnoteScheduleRentResultVo> rents;
  
}
