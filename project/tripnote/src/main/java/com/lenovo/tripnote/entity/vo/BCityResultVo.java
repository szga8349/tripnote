package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BCityResultVo {
	
	private Integer id;
	 /**
     * b_city.name_cn (城市名：中文)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private String nameCn;

    /**
     * b_city.name_en (城市名：英文)
     * @ibatorgenerated 2018-01-09 09:43:44
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
