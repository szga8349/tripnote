package com.lenovo.spider.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.lenovo.spider.util.HttpUtil;

public class HttpLogin {

	/**
	 * 登录并返回cookie
	 * @author：涂有
	 * @date 2017年12月18日 下午2:14:15
	 * @param url
	 * @param cookie
	 * @param usernameKey
	 * @param usernameValue
	 * @param passwordKey
	 * @param passwordValue
	 * @return 返回登录成功的cookie
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String login(String url, String cookie, 
			String usernameKey, String usernameValue,
			String passwordKey, String passwordValue) throws ClientProtocolException, IOException{
		
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair(usernameKey, usernameValue));
        nameValuePairList.add(new BasicNameValuePair(passwordKey, passwordValue));
        
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8));
        httppost.addHeader("Cookie", cookie);
        
        try(CloseableHttpResponse response = HttpUtil.httpClient.execute(httppost)) {
            StringBuilder sb = new StringBuilder();
            for(Header header : response.getAllHeaders()){
            	if("set-cookie".equals(header.getName().toLowerCase())){
            		int index = header.getValue().indexOf(';');
            		if(index >= 0){
            			sb.append(header.getValue().substring(0, index+1));
            		}else{
            			sb.append(header.getValue());
            		}
            	}
            }
            return sb.toString();
        }
	}
}
