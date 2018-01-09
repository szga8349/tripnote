package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BCityVo extends PageInfoVo{
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
     * b_city.region_id
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer regionId;

    /**
     * b_city.country_id
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer countryId;
    /**
     * b_city.is_hot (是否热门目的地：0- 不是，1-是)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer isHot;
}
