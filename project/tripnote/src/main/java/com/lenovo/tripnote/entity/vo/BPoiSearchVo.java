package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**搜索poi对象
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BPoiSearchVo extends PageInfoVo{
     private String name;
     private Integer cityId;
     private Integer type;
     private Integer userId;
     /**
     * 默认不设置 查询自己和系统所有的 true查询个人的 false查询系统的
     */
    private Boolean isMy ;
    
    private String supplier;
}
