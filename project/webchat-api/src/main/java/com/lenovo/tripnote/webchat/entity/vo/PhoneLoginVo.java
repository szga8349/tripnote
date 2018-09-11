package com.lenovo.tripnote.webchat.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneLoginVo {
	 /**
     * 设备名称  默认web端
     */
    private String device = "web网页";
    /**
     * b_account.phone
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String phone;
    
    /**
     * 手机验证码
     */
    private String smsCode;
}
