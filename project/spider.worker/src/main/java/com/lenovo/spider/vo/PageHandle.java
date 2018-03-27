package com.lenovo.spider.vo;

import java.util.Date;

public class PageHandle {
    
    private Long id;
    private Long siteId;
    /**1 文本包含 2 xpath元素存在 3 css元素存在*/
    private Integer judgeWay;
    /**与judge_way对应*/
    private String content;
    /**1 换ip 2 换账号登录 3 换cookie*/
    private Integer handleWay;
    /**出现次数*/
    private Integer occurTimes;
    /**1 重新抓取 2 不抓取*/
    private Integer recraw;
    private Date createTime;
    private Date updateTime;
    
    public PageHandle(){
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
    
    public Integer getJudgeWay() {
        return judgeWay;
    }
    
    public void setJudgeWay(Integer judgeWay){
        this.judgeWay = judgeWay;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public Integer getHandleWay() {
        return handleWay;
    }
    
    public void setHandleWay(Integer handleWay){
        this.handleWay = handleWay;
    }
    
    public Integer getOccurTimes() {
        return occurTimes;
    }
    
    public void setOccurTimes(Integer occurTimes){
        this.occurTimes = occurTimes;
    }
    
    public Integer getRecraw() {
        return recraw;
    }
    
    public void setRecraw(Integer recraw){
        this.recraw = recraw;
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
		return "PageHandle [content=" + content + "]";
	}
    
}