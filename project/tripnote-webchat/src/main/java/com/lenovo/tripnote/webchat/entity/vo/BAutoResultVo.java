package com.lenovo.tripnote.webchat.entity.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**自动登录返回信息
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BAutoResultVo extends BAccountDetailVo {
	
	private String token;

}
