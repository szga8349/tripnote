package com.lenovo.spider.vo;

import java.util.Date;

public class AuthInfo {

    private Long id;
    private Long siteId;
    private String username;
    private String password;
    /**1 有效，2 无效  针对账号被封情况*/
    private Integer userStatus;
    private Integer using;
    private Long usingTime;
    private String cookie;
    /**1 有效 2无效*/
    private Integer cookieStatus;
    private Date createTime;
    private Date updateTime;

    /**
     * siteInfo 冗余字段
     */
    private String siteName;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getUsing() {
		return using;
	}

	public void setUsing(Integer using) {
		this.using = using;
	}

	public Long getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(Long usingTime) {
		this.usingTime = usingTime;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public Integer getCookieStatus() {
		return cookieStatus;
	}

	public void setCookieStatus(Integer cookieStatus) {
		this.cookieStatus = cookieStatus;
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Override
	public String toString() {
		return "AuthInfo [username=" + username + ", password=" + password + ", cookie=" + cookie + "]";
	}
}