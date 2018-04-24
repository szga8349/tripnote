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
     * t_tripnote_schedule_r_city.id
     * @ibatorgenerated 2018-01-11 17:32:53
     */
    private Integer id;
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
    
    
    /**
     * t_tripnote_schedule_r_city.position (位置:-1开始城市标识,0行程中城市,1达到城市标识)
     * @ibatorgenerated 2018-04-24 15:26:08
     */
    private Integer position;

}
