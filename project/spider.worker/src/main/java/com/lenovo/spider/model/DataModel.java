package com.lenovo.spider.model;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DataModel {
	private String parentUrl;
	private String url;
	private JSON jsonString;
}
