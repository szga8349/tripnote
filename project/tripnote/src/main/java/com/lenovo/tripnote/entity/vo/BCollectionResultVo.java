package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BCollectionResultVo {
	
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

}
