package com.lenovo.spider.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * http 请求工具类
 * @author WangYinPing(王崟平)
 *
 */
public class HttpUtil {
    public static CloseableHttpClient httpClient;
    private static Logger logger = LogUtil.getLogger("time");
    
    static {
		int socketTimeout = 1000000;
		int connectionTimeout = 1000000;
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectionTimeout).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
	}
    
    public static JSONObject postStr(String url,Map<String,String> paramMap){
    	long start = System.currentTimeMillis();
    	JSONObject result = postStr(url, null, paramMap);
    	long end = System.currentTimeMillis();
    	logger.info("httppost时间：{}，URL：{}", end - start, url);
    	return result;
    }
    
    /**
     * 在先前的基础加入header的设置
     * @author：涂有
     * @date 2017年4月18日 下午4:53:18
     * @param url
     * @param header
     * @param paramMap
     * @return
     * @throws
     */
    public static JSONObject postStr(String url,Map<String, String> header,Map<String,String> paramMap){
    	JSONObject jsonObj = new JSONObject();
        int code = 1;
        String tip = "ok";
        Set<Entry<String,String>> entrySet = paramMap.entrySet();
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        for(Entry<String,String> entry : entrySet){
            NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(),entry.getValue());
            nameValuePairList.add(nameValuePair);
        }
        HttpPost httppost = new HttpPost(url);
        UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);
        httppost.setEntity(requestEntity);
        //设置header
        if(header != null){
        	for(String key : header.keySet()){
        		httppost.addHeader(key, header.get(key));
        	}
        }
        
        CloseableHttpResponse  response = null;
        StatusLine statusLine = null;
        try {
            response = httpClient.execute(httppost);
            statusLine = response.getStatusLine();
            jsonObj.put("protocolVersion", statusLine.getProtocolVersion());
            jsonObj.put("reasonPhrase",  statusLine.getReasonPhrase());
            jsonObj.put("statusCode", statusLine.getStatusCode());
            if(statusLine.getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                if(httpEntity != null){
                    jsonObj.put("data",EntityUtils.toString(httpEntity));
                }
            }
            //未抛出异常
            code = 2;
            tip = statusLine.toString();
        } catch (ClientProtocolException e) {
        	tip = "postStr ClientProtocolException";
        } catch (IOException e) {
        	tip = "postStr IOException";
        } catch (Exception e) {
        	tip = "postStr Exception";
        }finally{
            try {
            	if(response != null){
            		response.close();
            	}
            } catch (IOException e) {
            }
        }
        
        jsonObj.put("code", code);
        jsonObj.put("tip", tip);
        return jsonObj;
    }
    
    public static JSONObject postStr(String url,Map<String, String> header,String str){
    	JSONObject jsonObj = new JSONObject();
        int code = 1;
        String tip = "ok";
       
        HttpPost httppost = new HttpPost(url);
        StringEntity st = new StringEntity(str,Consts.UTF_8);
        httppost.setEntity(st);
        //设置header
        if(header != null){
        	for(String key : header.keySet()){
        		httppost.addHeader(key, header.get(key));
        	}
        }
        
        CloseableHttpResponse  response = null;
        StatusLine statusLine = null;
        try {
            response = httpClient.execute(httppost);
            statusLine = response.getStatusLine();
            jsonObj.put("protocolVersion", statusLine.getProtocolVersion());
            jsonObj.put("reasonPhrase",  statusLine.getReasonPhrase());
            jsonObj.put("statusCode", statusLine.getStatusCode());
            if(statusLine.getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                if(httpEntity != null){
                    jsonObj.put("data",EntityUtils.toString(httpEntity));
                }
            }
            //未抛出异常
            code = 2;
            tip = statusLine.toString();
        } catch (ClientProtocolException e) {
        	tip = "postStr ClientProtocolException";
        } catch (IOException e) {
        	tip = "postStr IOException";
        } catch (Exception e) {
        	tip = "postStr Exception";
        }finally{
            try {
            	if(response != null){
            		response.close();
            	}
            } catch (IOException e) {
            }
        }
        
        jsonObj.put("code", code);
        jsonObj.put("tip", tip);
        return jsonObj;
    }
    
    public static JSONObject postStr(String url,Map<String,String> paramMap,int tryTimes){
    	JSONObject jsonObj = null;
    	int loop = tryTimes;
    	while(loop > 0){
    		jsonObj = postStr(url,paramMap);
    		if(jsonObj.getIntValue("code") == 2 && 
    	       jsonObj.getIntValue("statusCode") == 200){
    	        break;
    	    }
    		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
    		loop --;
    	}
    	return jsonObj;
    }
    
    public static boolean postBool(String url,Map<String,String> paramMap){
        boolean result = false;
        JSONObject jsonObj = postStr(url,paramMap);
        if(jsonObj.getIntValue("code") == 2 && 
        	jsonObj.getIntValue("statusCode") == 200){
        	result = true;
        }
        return result;
    }

    public static JSONObject getStr(String url) {
        JSONObject jsonObj = new JSONObject();
        int code = 1;
        String tip = "ok";

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        StatusLine statusLine = null;
        try {
            response = httpClient.execute(httpGet);
            statusLine = response.getStatusLine();
            jsonObj.put("protocolVersion", statusLine.getProtocolVersion());
            jsonObj.put("reasonPhrase",  statusLine.getReasonPhrase());
            jsonObj.put("statusCode", statusLine.getStatusCode());
            if(statusLine.getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                if(httpEntity != null){
                    jsonObj.put("data", EntityUtils.toString(httpEntity));
                }
            }
            //未抛出异常
            code = 2;
            tip = statusLine.toString();
        } catch (ClientProtocolException e) {
            tip = "getStr ClientProtocolException";
        } catch (IOException e) {
            tip = "getStr IOException";
        } catch (Exception e) {
            tip = "getStr Exception";
        }finally{
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
            }
        }
        jsonObj.put("code", code);
        jsonObj.put("tip", tip);
        return jsonObj;
    }
}
