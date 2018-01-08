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
}