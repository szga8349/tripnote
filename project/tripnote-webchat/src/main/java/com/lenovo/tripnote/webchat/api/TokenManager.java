package com.lenovo.tripnote.webchat.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:conf/sms.properties", encoding = "UTF-8")
public class TokenManager {
	@Value("${webchat.appid}")
	private String appid;
	@Value("${webchat.secret}")
	private String secret;

	@PostConstruct
	public void get() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;

		System.out.println("");
	}
}
