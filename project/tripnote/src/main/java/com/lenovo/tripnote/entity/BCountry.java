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
public class BCountry implements Serializable {
    /**
     * b_country.id
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer id;

    /**
     * b_country.region_id (地区，洲 -id)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private String regionId;

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

    /**
     * b_country.status (状态：1-可用，0-停用)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer status;

    /**
     * b_country.create_time
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Date createTime;

    /**
     * b_country.create_user_id (0-system)
     * @ibatorgenerated 2018-01-09 09:43:44
     */
    private Integer createUserId;

}