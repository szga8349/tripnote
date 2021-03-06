package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BStore  {
    /**
     * b_store.id (商店铺面ID)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private Integer id;

    /**
     * b_store.store_name (商店名称)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private String storeName;

    /**
     * b_store.store_address (商店地址)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private String storeAddress;

    /**
     * b_store.store_url (商店提供的官网地址)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private String storeUrl;

    /**
     * b_store.store_logo (商店logo图片地址)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private String storeLogo;

    /**
     * b_store.create_user_id (商店创建人ID)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private Integer createUserId;

    /**
     * b_store.latitude (商店地址经度)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private Float latitude;

    /**
     * b_store.longitude (商店地址纬度)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private Float longitude;

    /**
     * b_store.create_time (创建时间)
     * @ibatorgenerated 2018-09-06 10:17:13
     */
    private Date createTime;
}