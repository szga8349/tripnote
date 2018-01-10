package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**查询 定制日程返回关联的城市对象
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteScheduleRCityVo {
	/**
     * t_tripnote_schedule_r_city.city_id
     * @ibatorgenerated 2018-01-10 14:03:37
     */
    private Integer cityId;
    
    /**
     * b_city.country_id
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer countryId;
    
    /**
     * t_tripnote_schedule_r_city.schedule_id
     * @ibatorgenerated 2018-01-10 14:03:37
     */
    private Integer scheduleId;
    
    /**
     * b_city.name_cn (城市名：中文)
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private String nameCn;

    /**
     * b_city.name_en (城市名：英文)
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private String nameEn;
    
    /**
     * b_city.lat (经度)
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Double lat;

    /**
     * b_city.lon (纬度)
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Double lon;

}