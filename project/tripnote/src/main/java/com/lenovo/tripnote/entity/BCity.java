package com.lenovo.tripnote.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class BCity implements Serializable {
    /**
     * b_city.id
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Integer id;

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
     * b_city.region_id
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Integer regionId;

    /**
     * b_city.country_id
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Integer countryId;

    /**
     * b_city.country_name_cn
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private String countryNameCn;

    /**
     * b_city.country_name_en
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private String countryNameEn;

    /**
     * b_city.status
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Integer status;

    /**
     * b_city.is_hot (是否热门目的地：0- 不是，1-是)
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Integer isHot;

    /**
     * b_city.create_time
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Date createTime;

    /**
     * b_city.create_user_id (0-system)
     * @ibatorgenerated 2018-01-10 13:59:25
     */
    private Integer createUserId;

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