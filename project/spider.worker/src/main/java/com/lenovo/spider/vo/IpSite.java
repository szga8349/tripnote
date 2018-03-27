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
public class IpSite {
    
    private Long id;
    private Long ipInfoId;
    private Long siteInfoId;
    private Integer available;
    private Date createTime;
    private Date updateTime;
    
}