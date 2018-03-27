package com.lenovo.spider.vo;

import java.util.Date;

public class UrlInfo {
    
    private Long id;
    private Long siteId;
    private String url;
    /**1 webmagic 2 selenium*/
    private Integer type;
    /**是否保存网页*/
    private Boolean savePage;
    /**是否解析网页*/
    private Boolean analyzePage;
    private Double sort;
    private Date createTime;
    private Date updateTime;
    
    public UrlInfo(){
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getSiteId() {
        return siteId;
    }
    
    public void setSiteId(Long siteId){
        this.siteId = siteId;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url){
        this.url = url;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type){
        this.type = type;
    }
    
    public Boolean getSavePage() {
        return savePage;
    }
    
    public void setSavePage(Boolean savePage){
        this.savePage = savePage;
    }
    
    public Boolean getAnalyzePage() {
        return analyzePage;
    }
    
    public void setAnalyzePage(Boolean analyzePage){
        this.analyzePage = analyzePage;
    }
    
    public Double getSort() {
        return sort;
    }
    
    public void setSort(Double sort){
        this.sort = sort;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "[url=" + url + ", type=" + type + ", savePage=" + savePage + ", analyzePage=" + analyzePage
				+ "]";
	}
}