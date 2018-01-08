package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**详细信息的时候 需要返回关联客户以及关联日程信息客户端
 * @author shijy2
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripNoteDetailResultVo extends TTripNoteResultVo {

	private List<TCustemVo> custems;
	
	private List<TTripNoteScheduleResuleVo> tTripNoteSchedules;
	
}