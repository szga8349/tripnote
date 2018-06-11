package com.lenovo.tripnote.webchat.vo;

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
public class RegisterVo {
    /**
     * 登录名(或者手机号)
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPasswd;
    
    
    private String smsCode;
}
