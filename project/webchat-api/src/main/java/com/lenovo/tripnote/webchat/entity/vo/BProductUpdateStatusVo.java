package com.lenovo.tripnote.webchat.entity.vo;

import java.util.List;

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
public class BProductUpdateStatusVo {
	private List<BProductStatusVo> statuses;
}
