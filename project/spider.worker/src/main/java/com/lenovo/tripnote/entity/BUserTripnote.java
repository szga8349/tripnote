package com.lenovo.tripnote.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@NoArgsConstructor
public class BUserTripnote implements Serializable {
    /**
     * b_user_tripnote.id
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private Integer id;

    /**
     * b_user_tripnote.title
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private String title;

    /**
     * b_user_tripnote.status (1 公开 -1 未公开私有)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private Integer status;

    /**
     * b_user_tripnote.image_url (图片url)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private String imageUrl;

    /**
     * b_user_tripnote.create_userid
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private Integer createUserid;

    /**
     * b_user_tripnote.create_time
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private Date createTime;

    /**
     * b_user_tripnote.type (类型：1笔记 2图片)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private Integer type;

    /**
     * b_user_tripnote.relation_city_id (相关城市ID)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private Integer relationCityId;

    /**
     * b_user_tripnote.relation_city_name (相关城市名称)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private String relationCityName;

    /**
     * b_user_tripnote.relation_poi_id (相关poi id)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private Integer relationPoiId;

    /**
     * b_user_tripnote.ralation_poi_name (相关poi的名称)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private String ralationPoiName;

    /**
     * b_user_tripnote.label (标签)
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private String label;

    /**
     * b_user_tripnote.context
     * @ibatorgenerated 2018-05-02 14:52:44
     */
    private String context;
}