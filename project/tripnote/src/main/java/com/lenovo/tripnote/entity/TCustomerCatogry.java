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
public class TCustomerCatogry implements Serializable {
    /**
     * t_customer_catogry.id
     * @ibatorgenerated 2018-01-31 18:03:56
     */
    private Integer id;

    /**
     * t_customer_catogry.name
     * @ibatorgenerated 2018-01-31 18:03:56
     */
    private String name;

    /**
     * t_customer_catogry.create_user_id (定制师id)
     * @ibatorgenerated 2018-01-31 18:03:56
     */
    private Integer createUserId;

    /**
     * t_customer_catogry.create_time
     * @ibatorgenerated 2018-01-31 18:03:56
     */
    private Date createTime;
}