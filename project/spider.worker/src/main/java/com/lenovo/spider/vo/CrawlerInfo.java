package com.lenovo.spider.vo;

import java.util.Date;

public class CrawlerInfo {
    
    private Long id;
    private String name;
    private String host;
    private Integer usedIpNum;
    private Long crawlMsgNum;
    private Date createTime;
    private Date updateTime;
    
    public CrawlerInfo(){
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host){
        this.host = host;
    }
    
    public Integer getUsedIpNum() {
        return usedIpNum;
    }
    
    public void setUsedIpNum(Integer usedIpNum){
        this.usedIpNum = usedIpNum;
    }
    
    public Long getCrawlMsgNum() {
        return crawlMsgNum;
    }
    
    public void setCrawlMsgNum(Long crawlMsgNum){
        this.crawlMsgNum = crawlMsgNum;
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
        sb.append("name:" + this.name + ",");
        sb.append("host:" + this.host + ",");
        sb.append("usedIpNum:" + this.usedIpNum + ",");
        sb.append("crawlMsgNum:" + this.crawlMsgNum + ",");
        return "{" + sb.toString() + "}";
    }
}