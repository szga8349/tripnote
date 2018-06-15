package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.vo.PageInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BProductCollectionSearchVo extends PageInfo{
	
        private String name;
        
        private Integer userId;
}
