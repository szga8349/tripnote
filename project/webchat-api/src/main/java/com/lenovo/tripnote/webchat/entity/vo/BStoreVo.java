package com.lenovo.tripnote.webchat.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BStoreVo {
	 /**
     * b_store.store_name (商店名称)
     * @ibatorgenerated 2018-09-06 09:49:39
     */
    private String storeName;

    /**
     * b_store.store_address (商店地址)
     * @ibatorgenerated 2018-09-06 09:49:39
     */
    private String storeAddress;

    /**
     * b_store.store_url (商店提供的官网地址)
     * @ibatorgenerated 2018-09-06 09:49:39
     */
    private String storeUrl;

    /**
     * b_store.store_logo (商店logo图片地址)
     * @ibatorgenerated 2018-09-06 09:49:39
     */
    private String storeLogo;

    /**
     * b_store.latitude (商店地址经度)
     * @ibatorgenerated 2018-09-06 09:49:39
     */
    private Float latitude;

    /**
     * b_store.longitude (商店地址纬度)
     * @ibatorgenerated 2018-09-06 09:49:39
     */
    private Float longitude;
}
