package com.lenovo.tripnote.webchat.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AutoLoginInfo {
    /**
     * 设备名称  默认web端
     */
    private String device = "微信小程序";
    
    
    private Integer autoLogin;
    /**
     * b_account.login_token (第三方自动登录标识token)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String loginToken;
    
    
    private String loginName;
    
    
    private String loginPassword;
    
    private String imageurl;
}
