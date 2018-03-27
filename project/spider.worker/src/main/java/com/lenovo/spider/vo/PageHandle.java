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
public class PageHandle {

    private Long id;
    private Long siteId;
    /**
     * 1 文本包含 2 xpath元素存在 3 css元素存在
     */
    private Integer judgeWay;
    /**
     * 与judge_way对应
     */
    private String content;
    /**
     * 1 换ip 2 换账号登录 3 换cookie
     */
    private Integer handleWay;
    /**
     * 出现次数
     */
    private Integer occurTimes;
    /**
     * 1 重新抓取 2 不抓取
     */
    private Integer recraw;
    private Date createTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "PageHandle [content=" + content + "]";
    }
}