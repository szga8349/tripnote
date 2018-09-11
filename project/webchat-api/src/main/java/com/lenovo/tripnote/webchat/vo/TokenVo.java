package com.lenovo.tripnote.webchat.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenVo {
	
	
	private Integer userId;
	private String userLoginName;
	private Date loginTime;

}
