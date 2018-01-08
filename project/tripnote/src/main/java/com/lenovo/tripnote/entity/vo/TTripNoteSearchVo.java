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
}
