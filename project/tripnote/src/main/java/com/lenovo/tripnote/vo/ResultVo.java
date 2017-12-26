package com.lenovo.tripnote.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class ResultVo {
	
	/**
	 * code为1 返回正确 code为-1 标识异常，此时message有值 为异常原因
	 */
	private int code;
	/**
	 * code为-1 此值不为空，异常原因
	 */
	private String message;
	
	/**
	 * 返回 结果对象信息
	 */
	private Object data;
	
	

}
