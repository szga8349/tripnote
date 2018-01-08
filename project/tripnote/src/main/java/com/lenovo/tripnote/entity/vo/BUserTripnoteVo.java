package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BUserTripnoteVo {
	
	/**
	 * 定制日程主键值
	 */
	private Integer scheduleId;
	
	private String title;

	/**
	 * 默认笔记为定制师私有的
	 */
	private Integer status = -1;

	private String cover;

	private String context;
}
