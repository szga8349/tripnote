package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDDeEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BStoreModuleVo {

    /**
     * b_store_module.module_name (模块名称)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private String moduleName;

    /**
     * b_store_module.store_id (商店主键值ID)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    @IDDeEncryption
    private Integer storeId;

    /**
     * b_store_module.index (模块在商店展示的排列顺序,默认从1开始,往下依次内推)
     * @ibatorgenerated 2018-09-06 10:17:14
     */
    private Integer index;
}
