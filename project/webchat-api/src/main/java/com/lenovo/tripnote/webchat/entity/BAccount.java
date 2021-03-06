package com.lenovo.tripnote.webchat.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class BAccount  {
    /**
     * b_account.id
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer id;

    /**
     * b_account.login_name
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String loginName;

    /**
     * b_account.login_password
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String loginPassword;

    /**
     * b_account.login_name_type (登录账号类型：0-string,1-phoneNumber,2-email)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer loginNameType;

    /**
     * b_account.real_name
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String realName;

    /**
     * b_account.sex
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer sex;

    /**
     * b_account.account_type (0-admin,1-定制师, 2-用户)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer accountType;

    /**
     * b_account.status (状态：1-可用，0-已冻结，-1-已删除)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer status;

    /**
     * b_account.phone
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String phone;

    /**
     * b_account.email
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String email;

    /**
     * b_account.address
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String address;

    /**
     * b_account.level (等级)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer level;

    /**
     * b_account.score (积分)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer score;

    /**
     * b_account.create_time
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Date createTime;

    /**
     * b_account.create_user_id (0-system)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer createUserId;

    /**
     * b_account.imageurl (头像url信息)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String imageurl;

    /**
     * b_account.birthday (生日)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Date birthday;

    /**
     * b_account.language (精通语言)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String language;

    /**
     * b_account.occupation (职业)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String occupation;

    /**
     * b_account.auto_login (第三方自动登录标识:-1:不自动登录(普通用户名密码登录),1:微信自动登录2:QQ自动登录)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private Integer autoLogin;

    /**
     * b_account.login_token (第三方自动登录标识token)
     * @ibatorgenerated 2018-06-19 16:22:08
     */
    private String loginToken;
}