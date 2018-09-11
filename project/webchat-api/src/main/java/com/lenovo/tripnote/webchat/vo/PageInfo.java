package com.lenovo.tripnote.webchat.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shijy2
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class PageInfo {
	
	/**
	 * 第几页 默认从1开始
	 */
	private Integer pageNum = 1 ;
	
	/**
	 * 每页条数
	 */
	private Integer pageSize = 10;
}
