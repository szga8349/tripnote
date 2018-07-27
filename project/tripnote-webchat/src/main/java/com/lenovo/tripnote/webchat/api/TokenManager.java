package com.lenovo.tripnote.webchat.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.lenovo.tripnote.webchat.utils.HttpClientUtils;

import net.sf.json.JSONObject;

@Component
@PropertySource(value = "classpath:conf/sms.properties", encoding = "UTF-8")
public class TokenManager {
	@Value("${webchat.appid}")
	private String appid;
	@Value("${webchat.secret}")
	private String secret;

	@PostConstruct
	public void get() {
		/*String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		String token = HttpClientUtils.excute(url);
		JSONObject  jsonObject = JSONObject.fromObject(token);
		AccessToken accessToken = (AccessToken) JSONObject.toBean(jsonObject, AccessToken.class);
		System.out.println(accessToken);*/
	}
}
