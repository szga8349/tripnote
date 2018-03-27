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
public class UrlInfo {
    /**
     * 链接信息主键
     */
    private Long id;
    /**
     * 所属站点id
     */
    private Long siteId;
    /**
     * 链接表达式（正则）
     */
    private String url;
    /**
     * 抓取方式：1 webmagic 2 selenium
     */
    private Integer type;
    /**
     * 是否保存网页
     */
    private Boolean savePage;
    /**
     * 是否解析网页
     */
    private Boolean analyzePage;
    /**
     * 是否有下一页标记
     */
    private Boolean hasNextPage;
    /**
     * 下一页的类型：1.点击，2.滚动
     */
    private Integer nextPageType;
    /**
     * 下一页标记位置
     */
    private String nextPageLocator;
    /**
     * 排序
     */
    private Double sort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "[url=" + url + ", type=" + type + ", savePage=" + savePage + ", analyzePage=" + analyzePage
                + "]";
    }
}