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
public class AuthLocator {
    /**
     * 主键
     */
    private Long id;
    /**
     * 所属站点id
     */
    private Long siteId;
    /**
     * 登录地址
     */
    private String loginUrl;
    /**
     * 登录类型:1 - webdriver , 2 - http post
     */
    private Integer loginType;
    /**
     * 用户名输入框位置
     */
    private String usernameLocator;
    /**
     * 1 css 2 xpath
     */
    private Integer usernameLocatorType;
    /**
     * 密码输入框位置
     */
    private String passwordLocator;
    private Integer passwordLocatorType;
    /**
     * 验证码类型
     */
    private Integer validType;
    /**
     * 验证码位置
     */
    private String validCodeLocator;
    private Integer validCodeLocatorType;
    /**
     * 验证码图片位置
     */
    private String validImageLocator;
    private String validImageLocatorType;
    /**
     * 提交按钮位置
     */
    private String submitButtonLocator;
    private Integer submitButtonLocatorType;

    //以下是http post 需要
    /**
     * 登录地址
     */
    private String httpPostUrl;
    /**
     * http请求头信息
     */
    private String httpHeader;
    /**
     * 请求表单信息
     */
    private String httpForm;
    /**
     * 加密方法
     */
    private String httpEncryptFunc;


    private Date createTime;
    private Date updateTime;

}