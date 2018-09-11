package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BStoreModule {
    /**
     * b_store_module.id (商店模块ID)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private Integer id;

    /**
     * b_store_module.module_name (模块名称)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private String moduleName;

    /**
     * b_store_module.store_id (商店主键值ID)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private Integer storeId;

    /**
     * b_store_module.index (模块在商店展示的排列顺序,默认从1开始,往下依次内推)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private Integer index;

    /**
     * b_store_module.create_user_id (模块创建者ID)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private Integer createUserId;

    /**
     * b_store_module.create_time (建创时间)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private Date createTime;
}