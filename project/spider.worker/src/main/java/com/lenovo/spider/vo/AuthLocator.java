package com.lenovo.spider.vo;

import java.util.Date;

public class AuthLocator {

    private Long id;
    private Long siteId;
    private String loginUrl;
    /**登录类型:1 - webdriver , 2 - http post*/
    private Integer loginType;
    
    //以下是webdriver登录需要
    private String usernameLocator;
    /**1 css 2 xpath*/
    private Integer usernameLocatorType;
    private String passwordLocator;
    private Integer passwordLocatorType;
    private Integer validType;
    private String validCodeLocator;
    private Integer validCodeLocatorType;
    private String validImageLocator;
    private String validImageLocatorType;
    private String submitButtonLocator;
    private Integer submitButtonLocatorType;
    
    //以下是http post 需要
    private String httpPostUrl;
    private String httpHeader;
    private String httpForm;
    private String httpEncryptFunc;
    
    
    private Date createTime;
    private Date updateTime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public Integer getLoginType() {
		return loginType;
	}
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	public String getUsernameLocator() {
		return usernameLocator;
	}
	public void setUsernameLocator(String usernameLocator) {
		this.usernameLocator = usernameLocator;
	}
	public Integer getUsernameLocatorType() {
		return usernameLocatorType;
	}
	public void setUsernameLocatorType(Integer usernameLocatorType) {
		this.usernameLocatorType = usernameLocatorType;
	}
	public String getPasswordLocator() {
		return passwordLocator;
	}
	public void setPasswordLocator(String passwordLocator) {
		this.passwordLocator = passwordLocator;
	}
	public Integer getPasswordLocatorType() {
		return passwordLocatorType;
	}
	public void setPasswordLocatorType(Integer passwordLocatorType) {
		this.passwordLocatorType = passwordLocatorType;
	}
	public Integer getValidType() {
		return validType;
	}
	public void setValidType(Integer validType) {
		this.validType = validType;
	}
	public String getValidCodeLocator() {
		return validCodeLocator;
	}
	public void setValidCodeLocator(String validCodeLocator) {
		this.validCodeLocator = validCodeLocator;
	}
	public Integer getValidCodeLocatorType() {
		return validCodeLocatorType;
	}
	public void setValidCodeLocatorType(Integer validCodeLocatorType) {
		this.validCodeLocatorType = validCodeLocatorType;
	}
	public String getValidImageLocator() {
		return validImageLocator;
	}
	public void setValidImageLocator(String validImageLocator) {
		this.validImageLocator = validImageLocator;
	}
	public String getValidImageLocatorType() {
		return validImageLocatorType;
	}
	public void setValidImageLocatorType(String validImageLocatorType) {
		this.validImageLocatorType = validImageLocatorType;
	}
	public String getSubmitButtonLocator() {
		return submitButtonLocator;
	}
	public void setSubmitButtonLocator(String submitButtonLocator) {
		this.submitButtonLocator = submitButtonLocator;
	}
	public Integer getSubmitButtonLocatorType() {
		return submitButtonLocatorType;
	}
	public void setSubmitButtonLocatorType(Integer submitButtonLocatorType) {
		this.submitButtonLocatorType = submitButtonLocatorType;
	}
	public String getHttpPostUrl() {
		return httpPostUrl;
	}
	public void setHttpPostUrl(String httpPostUrl) {
		this.httpPostUrl = httpPostUrl;
	}
	public String getHttpHeader() {
		return httpHeader;
	}
	public void setHttpHeader(String httpHeader) {
		this.httpHeader = httpHeader;
	}
	public String getHttpForm() {
		return httpForm;
	}
	public void setHttpForm(String httpForm) {
		this.httpForm = httpForm;
	}
	public String getHttpEncryptFunc() {
		return httpEncryptFunc;
	}
	public void setHttpEncryptFunc(String httpEncryptFunc) {
		this.httpEncryptFunc = httpEncryptFunc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}