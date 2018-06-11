package com.lenovo.tripnote.webchat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BProductImage  {
    /**
     * b_product_image.id (图片主键)
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    private Integer id;

    /**
     * b_product_image.url (图片URL地址)
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    private String url;

    /**
     * b_product_image.product_id (产品主键ID值)
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    private Integer productId;

}