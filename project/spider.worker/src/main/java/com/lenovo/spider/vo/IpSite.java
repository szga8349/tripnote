package com.lenovo.spider.vo;

import java.util.Date;

public class IpSite {
    
    private Long id;
    private Long ipInfoId;
    private Long siteInfoId;
    private Integer available;
    private Date createTime;
    private Date updateTime;
    
    public IpSite(){
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getIpInfoId() {
        return ipInfoId;
    }
    
    public void setIpInfoId(Long ipInfoId){
        this.ipInfoId = ipInfoId;
    }
    
    public Long getSiteInfoId() {
        return siteInfoId;
    }
    
    public void setSiteInfoId(Long siteInfoId){
        this.siteInfoId = siteInfoId;
    }
    
    public Integer getAvailable() {
        return available;
    }
    
    public void setAvailable(Integer available){
        this.available = available;
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
        StringBuffer sb = new StringBuffer();
        sb.append("id:" + this.id + ",");
        sb.append("ipInfoId:" + this.ipInfoId + ",");
        sb.append("siteInfoId:" + this.siteInfoId + ",");
        sb.append("available:" + this.available + ",");
        sb.append("createTime:" + this.createTime + ",");
        sb.append("updateTime:" + this.updateTime);
        return "{" + sb.toString() + "}";
    }
}