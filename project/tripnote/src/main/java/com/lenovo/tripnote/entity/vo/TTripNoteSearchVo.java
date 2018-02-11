package com.lenovo.tripnote.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TTripNoteSearchVo extends PageInfoVo{
	  /**
     * 标题
     */
    private String title;
    

    private Integer userId;
    
    private Integer type;
    
    /**
     * 排序字段
     */
    private String sortField;
    
    /**
     * 升降序 1升序 -1降序 
     */
    private Integer ascOrDes;
    
    
    /**
     * 是否查询所有 默认为false
     */
    private Boolean isAll = false;
    
    
    /**
     * 按照天数搜索 前台传入开始和结束 dateEnd有可能为空
     */
    private Integer dateStart;
    
    
    private Integer dateEnd;
}
