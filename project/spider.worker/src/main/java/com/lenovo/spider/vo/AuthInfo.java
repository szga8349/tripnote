package com.lenovo.spider.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthInfo {
    /**
     * 用户登录信息主键
     */
    private Long id;
    /**
     * 所属站点id
     */
    private Long siteId;
    /**
     * 登录的用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 账号状态：1 有效，2 无效  针对账号被封情况
     */
    private Integer userStatus;
    /**
     * 是否在使用中
     */
    private Integer using;
    /**
     * 使用时间
     */
    private Long usingTime;
    /**
     * cookie
     */
    private String cookie;
    /**
     * cookie状态：1 有效 2无效
     */
    private Integer cookieStatus;
    private Date createTime;
    private Date updateTime;

    /**
     * siteInfo 冗余字段
     */
    private String siteName;

}