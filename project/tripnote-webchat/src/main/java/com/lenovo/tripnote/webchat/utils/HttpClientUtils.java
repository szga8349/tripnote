package com.lenovo.tripnote.webchat.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpClientUtils {
    public static String excute(String url){
    	BufferedReader reader = null;
    	try {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			URI url1 = new URI(url);
			ClientHttpResponse response = factory.createRequest(url1, HttpMethod.GET).execute();
			 int status = response.getRawStatusCode();
			if(status==HttpStatus.OK.value()){
				reader = new  BufferedReader(new InputStreamReader(response.getBody()));
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while((line=reader.readLine())!=null){
					buffer.append(line);
				}
				return buffer.toString();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
    	return null;
    }
}
