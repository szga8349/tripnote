package com.lenovo.tripnote.webchat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;
import com.lenovo.tripnote.webchat.service.BProductService;
import com.lenovo.tripnote.webchat.utils.HttpClientUtils;
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
		vo.setData(HttpClientUtils.excute(url));
		return vo;
	}

	@RequestMapping(value = "/decryptID/{id}")
	@ResponseBody
	public ResultVo decryptID(@PathVariable  String id) {
		ResultVo vo = new ResultVo();
		vo.setCode(Result.SUCESSFUL);
		//String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+this.appid+"&secret="+this.secret+"&js_code="+code+"&grant_type=authorization_code";
		vo.setData(id);
		return vo;
	}
}
