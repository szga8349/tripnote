package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.vo.PageInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BProductShareSearchVo extends PageInfo {
	private Integer userId;
	private String name;
}
