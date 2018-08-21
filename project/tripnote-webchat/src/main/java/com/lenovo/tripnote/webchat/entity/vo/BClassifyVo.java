package com.lenovo.tripnote.webchat.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BClassifyVo {

    /**
     * b_classify.classify_name (分类名称)
     * @ibatorgenerated 2018-08-21 17:07:11
     */
    private String classifyName;
    /**
     * b_classify.parent_classify_id (父分类ID:为0标识根分类(第一级分类))
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    private Integer parentClassifyId = 0;
}
