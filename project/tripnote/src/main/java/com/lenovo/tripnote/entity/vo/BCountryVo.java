package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BCountryVo {
	  /**
     * b_country.region_id (地区，洲 -id)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer regionId;

    /**
     * b_country.code (国家代码)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private String code;

    /**
     * b_country.name_cn (国家名称：中文)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private String nameCn;

    /**
     * b_country.name_en (国家：英文)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private String nameEn;

}
