package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class BCollectionVo{
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
     * b_collection.type (类型)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer type;

    /**
     * b_collection.cid (收藏的来源id)
     * @ibatorgenerated 2018-04-10 16:48:42
     */
    private Integer cid;

}
