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
	
	private String imageurl;
	
    /**
     * 定制行程客户报价,在行程报价中会更新此值
     */
    private Double price;
    
    /**
     * t_trip_note.start_city_id
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer startCityId;
    /**
     * t_trip_note.end_city_id
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer endCityId;
}