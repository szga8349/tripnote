package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BUserTripnoteVo{
	

    /**
     * b_user_tripnote.title
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String title;

    /**
     * b_user_tripnote.status (1 公开 -1 未公开私有)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer status;

    /**
     * b_user_tripnote.image_url (图片url)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String imageUrl;

    /**
     * b_user_tripnote.type (类型：1笔记 2图片)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer type;

    /**
     * b_user_tripnote.relation_city_id (相关城市ID)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer relationCityId;

    /**
     * b_user_tripnote.relation_city_name (相关城市名称)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String relationCityName;

    /**
     * b_user_tripnote.relation_poi_id (相关poi id)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer relationPoiId;

    /**
     * b_user_tripnote.ralation_poi_name (相关poi的名称)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String ralationPoiName;

    /**
     * b_user_tripnote.label (标签)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String label;

    /**
     * b_user_tripnote.context (内容)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String context;
}
