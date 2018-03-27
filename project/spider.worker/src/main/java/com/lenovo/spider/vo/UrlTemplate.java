package com.lenovo.spider.vo;

import java.util.Date;
import java.util.List;

public class UrlTemplate {

    private Long id;
    private Long siteId;
    private Long urlInfoId;
    /**hbase row key 前缀*/
    private String rowKeyPrefix;
    private String name;
    /**1 string 2 对象 3 数组*/
    private Integer type;
    /**1 可选 2 必须*/
    private Integer importantLevel;
    private String locator;
    /**1 css 2 xpath*/
    private Integer locatorType;
    private Long parentId;
    private Double sort;
    private Date updateTime;
    private Date createTime;

    private List<UrlTemplate> children;

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

	public Long getUrlInfoId() {
		return urlInfoId;
	}

	public void setUrlInfoId(Long urlInfoId) {
		this.urlInfoId = urlInfoId;
	}

	public String getRowKeyPrefix() {
		return rowKeyPrefix;
	}

	public void setRowKeyPrefix(String rowKeyPrefix) {
		this.rowKeyPrefix = rowKeyPrefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getImportantLevel() {
		return importantLevel;
	}

	public void setImportantLevel(Integer importantLevel) {
		this.importantLevel = importantLevel;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public Integer getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(Integer locatorType) {
		this.locatorType = locatorType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<UrlTemplate> getChildren() {
		return children;
	}

	public void setChildren(List<UrlTemplate> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "UrlTemplate [name=" + name + ", locator=" + locator + "]";
	}
}