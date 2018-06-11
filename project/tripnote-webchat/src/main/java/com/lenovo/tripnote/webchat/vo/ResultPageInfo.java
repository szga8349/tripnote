package com.lenovo.tripnote.webchat.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResultPageInfo extends PageInfo {

	/**
	 * 总条数
	 */
	private Long total;

	/**
	 * 具体数据
	 */
	private Object data;
}
