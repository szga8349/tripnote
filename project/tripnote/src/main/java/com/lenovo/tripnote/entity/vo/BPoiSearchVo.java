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
}
