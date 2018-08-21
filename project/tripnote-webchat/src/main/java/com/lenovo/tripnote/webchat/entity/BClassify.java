package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BClassify  {
    /**
     * b_classify.id (产品分类主键值)
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    private Integer id;

    /**
     * b_classify.classify_name (分类名称)
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    private String classifyName;

    /**
     * b_classify.create_time (创建时间)
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    private Date createTime;

    /**
     * b_classify.create_user_id (创建者用户ID)
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    private Integer createUserId;

    /**
     * b_classify.parent_classify_id (父分类ID:为0标识根分类(第一级分类))
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    private Integer parentClassifyId;
}