package com.lenovo.tripnote.entity.vo;

import java.io.Serializable;
import java.util.Date;

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
public class TTripNoteResultVo implements Serializable {
	private Integer id;

	private String code;

	private String title;

	private Date startDate;

	private Date endDate;

	private Integer days;

	private String destination;

	private Date createTime;

	private Integer createUserId;

	private Integer status;

	private String startCity;

	private String remarks;
	
	private String introduction;
}