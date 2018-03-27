package com.lenovo.spider.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlTemplate {

    private Long id;
    private Long siteId;
    private Long urlInfoId;
    /**
     * hbase row key 前缀
     */
    private String rowKeyPrefix;
    private String name;
    /**
     * 1 string 2 对象 3 数组
     */
    private Integer type;
    private String locator;
    private Long parentId;
    private Date updateTime;
    private Date createTime;

    private List<UrlTemplate> children;

    @Override
    public String toString() {
        return "UrlTemplate [name=" + name + ", locator=" + locator + "]";
    }
}