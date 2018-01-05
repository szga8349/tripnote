package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分页对象 暂时不做实现后期分页时进行实现
 * 
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class PageInfoVo {
	private Integer pageNo;
	private Integer pageSize;
}
