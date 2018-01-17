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
}
