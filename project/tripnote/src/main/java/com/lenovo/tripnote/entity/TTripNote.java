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
public class TTripNote implements Serializable {
    /**
     * t_trip_note.id
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer id;

    /**
     * t_trip_note.code (编号)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private String code;

    /**
     * t_trip_note.title (trip note 名称)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private String title;

    /**
     * t_trip_note.start_date (行程开始时间)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Date startDate;

    /**
     * t_trip_note.end_date (行程结束时间)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Date endDate;

    /**
     * t_trip_note.days (行程天数)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer days;

    /**
     * t_trip_note.destination (目的地: 成都-北京-东京-京都-大阪-奈良-北海道-冲绳)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private String destination;

    /**
     * t_trip_note.end_city_id
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer endCityId;

    /**
     * t_trip_note.create_time (创建时间)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Date createTime;

    /**
     * t_trip_note.create_user_id (创建人，定制师id)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer createUserId;

    /**
     * t_trip_note.status (状态：0-草稿、1-已发布(未预订)、2-已预订(行程前)、3-行程中、4-行程结束)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer status;

    /**
     * t_trip_note.start_city
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private String startCity;

    /**
     * t_trip_note.start_city_id
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer startCityId;

    /**
     * t_trip_note.remarks
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private String remarks;

    /**
     * t_trip_note.type (0-系统模板,1-定制 2-定制师模板，3机构模板)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Integer type;

    /**
     * t_trip_note.publish_time (发布时间)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Date publishTime;

    /**
     * t_trip_note.imageurl (制定封面url地址)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private String imageurl;

    /**
     * t_trip_note.price (定制行程客户报价,在行程报价中会更新此值)
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private Double price;

    /**
     * t_trip_note.introduction
     * @ibatorgenerated 2018-04-24 14:53:48
     */
    private String introduction;
}