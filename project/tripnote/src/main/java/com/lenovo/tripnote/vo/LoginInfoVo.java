package com.lenovo.tripnote.vo;

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
}
