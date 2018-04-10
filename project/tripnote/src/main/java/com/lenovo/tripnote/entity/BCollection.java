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
public class BCollection implements Serializable {
    /**
     * b_collection.id
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer id;

    /**
     * b_collection.name (名称)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String name;

    /**
     * b_collection.image_url (缩列图地址url)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String imageUrl;

    /**
     * b_collection.url (网址)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String url;

    /**
     * b_collection.api_url (获取数据的API地址信息)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private String apiUrl;

    /**
     * b_collection.create_time (纪录创建时间)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Date createTime;

    /**
     * b_collection.create_user_id
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer createUserId;
}