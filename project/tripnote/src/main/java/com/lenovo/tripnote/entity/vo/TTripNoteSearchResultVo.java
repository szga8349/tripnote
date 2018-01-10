package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shijy2
 *
 */

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripNoteSearchResultVo  extends TTripNoteResultVo{

	private List<TCustemVo> custems;
	
	private List<SampleScheduleResultVo> tTripNoteSchedules;
	 /**
     * 定制日程关联的目的地信息
     */
    private List<TTripnoteScheduleRCityVo> citys;
}