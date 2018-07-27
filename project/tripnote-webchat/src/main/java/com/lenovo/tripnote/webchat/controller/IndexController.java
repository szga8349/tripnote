package com.lenovo.tripnote.webchat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;
import com.lenovo.tripnote.webchat.service.BProductService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
@Controller
@RequestMapping()
@PropertySource(value="classpath:conf/sms.properties",encoding="UTF-8")
public class IndexController {
	@Resource
	private BProductService bProductService;
	@Value("${webchat.appid}")
	private String appid ;
	@Value("${webchat.secret}")
	private String secret ;
	
	
	@RequestMapping(value = "/index/product")
	@ResponseBody
	public ResultVo myProduct(HttpServletRequest request,BProductSearchVo searchVo) {
		ResultVo vo = new ResultVo();
		if(searchVo.getName()!=null){
			searchVo.setName("%"+searchVo.getName()+"%");
		}
		searchVo.setStatus(1);
		vo.setData(bProductService.searchProduct(searchVo));
		vo.setCode(Result.SUCESSFUL);
		return vo;
	}
	@RequestMapping(value = "/productDetail/doDetail/{id}")
	@ResponseBody
	public ResultVo doDetail(@PathVariable String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		vo.setData(bProductService.detail(Integer.valueOf(id)));
		return vo;
	}
	
	@RequestMapping(value = "/getOAuthResultByCode")
	@ResponseBody
	public ResultVo doOAuthResultByCode(String code) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+this.appid+"&secret="+this.secret+"&js_code="+code+"&grant_type=authorization_code";
		try {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			URI url1 = new URI(url);
			ClientHttpResponse response = factory.createRequest(url1, HttpMethod.GET).execute();
			 int status = response.getRawStatusCode();
			if(status==HttpStatus.OK.value()){
				BufferedReader reader = new  BufferedReader(new InputStreamReader(response.getBody()));
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while((line=reader.readLine())!=null){
					buffer.append(line);
				}
				vo.setData(buffer.toString());
				reader.close();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
