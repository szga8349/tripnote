package com.lenovo.tripnote.entity.vo;

import java.io.Serializable;

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
    private String startDate;

    /**
     * 结束日益
     */
    private String endDate;

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
}