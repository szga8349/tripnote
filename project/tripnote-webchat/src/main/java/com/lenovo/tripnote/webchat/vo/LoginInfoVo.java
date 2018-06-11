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
public class LoginInfoVo {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPasswd;
    
    
    /**
     * 设备名称  默认web端
     */
    private String device = "tripNode Web";
}
