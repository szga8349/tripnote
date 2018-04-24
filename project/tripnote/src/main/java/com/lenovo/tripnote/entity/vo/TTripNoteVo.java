package com.lenovo.tripnote.entity.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripNoteVo implements Serializable {
 
    private String code;

    /**
     * 标题
     */
    private String title;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日益
     */
    private Date endDate;

    /**
     * 几天
     */
    private Integer days;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 出发城市
     */
    private String startCity;

    /**
     * 备注信息
     */
    private String remarks;
    
    
    private String customers;
    
    private String introduction;
    
    private Integer type;
    
    /**默认为0
     * 状态：0-草稿、1-已发布(未预订)、2-已预订(行程前)、3-行程中、4-行程结束
     */
    private Integer status = 0;
    
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