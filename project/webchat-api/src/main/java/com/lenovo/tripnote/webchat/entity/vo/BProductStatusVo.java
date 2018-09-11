package com.lenovo.tripnote.webchat.entity.vo;

import com.lenovo.tripnote.webchat.convert.IDDeEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BProductStatusVo {
	@IDDeEncryption
	private Integer id;
	private Integer status;
}
