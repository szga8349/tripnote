package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 定制导出模板时所需参数对象
 * 
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BExportVo {
	/**
	 * 选的日程主键ID
	 */
	private List<Integer> scheduleIds;

	/**
	 * 导入到定制时 需要传入目标定制ID
	 */
	private Integer tripnoteId;
	
	private Integer createUserId;
}
