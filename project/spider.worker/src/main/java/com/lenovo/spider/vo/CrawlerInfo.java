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
public class CrawlerInfo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 爬虫名称
     */
    private String name;
    /**
     * 主机地址
     */
    private String host;
    /**
     * 使用ip数量
     */
    private Integer usedIpNum;
    /**
     * 爬取数量
     */
    private Long crawlMsgNum;
    private Date createTime;
    private Date updateTime;
    
}