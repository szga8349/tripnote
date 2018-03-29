package com.lenovo.tripnote.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class BAccount implements Serializable {
    /**
     * b_account.id
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer id;

    /**
     * b_account.login_name
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private String loginName;

    /**
     * b_account.login_password
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private String loginPassword;

    /**
     * b_account.login_name_type (登录账号类型：0-string,1-phoneNumber,2-email)
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer loginNameType;

    /**
     * b_account.real_name
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private String realName;

    /**
     * b_account.sex
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer sex;

    /**
     * b_account.account_type (0-admin,1-定制师, 2-用户)
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer accountType;

    /**
     * b_account.status (状态：1-可用，0-已冻结，-1-已删除)
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer status;

    /**
     * b_account.phone
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private String phone;

    /**
     * b_account.email
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private String email;

    /**
     * b_account.address
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private String address;

    /**
     * b_account.level (等级)
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer level;

    /**
     * b_account.score (积分)
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer score;

    /**
     * b_account.create_time
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Date createTime;

    /**
     * b_account.create_user_id (0-system)
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private Integer createUserId;

    /**
     * b_account.imageurl (头像url信息)
     * @ibatorgenerated 2018-03-29 15:38:39
     */
    private String imageurl;
}