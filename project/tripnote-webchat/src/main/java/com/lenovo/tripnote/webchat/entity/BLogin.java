package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import com.lenovo.tripnote.webchat.convert.IDEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BLogin  {
    /**
     * b_login.loginid
     * @ibatorgenerated 2018-06-04 14:44:41
     */
	@IDEncryption
    private Integer loginid;

    /**
     * b_login.loginIp (录登IP地址)
     * @ibatorgenerated 2018-06-04 14:44:41
     */
    private String loginip;

    /**
     * b_login.device (登录设备)
     * @ibatorgenerated 2018-06-04 14:44:41
     */
    private String device;

    /**
     * b_login.userId (登录用户ID)
     * @ibatorgenerated 2018-06-04 14:44:41
     */
    private Integer userid;

    /**
     * b_login.loginName
     * @ibatorgenerated 2018-06-04 14:44:41
     */
    private String loginname;

    /**
     * b_login.loginTime (录登时间)
     * @ibatorgenerated 2018-06-04 14:44:41
     */
    private Date logintime;

    /**
     * b_login.loginOutTime (登出时间)
     * @ibatorgenerated 2018-06-04 14:44:41
     */
    private Date loginouttime;

    /**
     * b_login.status
     * @ibatorgenerated 2018-06-04 14:44:41
     */
    private Integer status;

}