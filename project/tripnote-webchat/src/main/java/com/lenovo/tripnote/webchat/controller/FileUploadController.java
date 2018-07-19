package com.lenovo.tripnote.webchat.controller;

import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lenovo.tripnote.webchat.service.CommonService;
import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.ResultVo;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;


@Controller
@Log4j
public class FileUploadController {
    @Resource
    private CommonService commonService;
	@RequestMapping(value = "/token/upload/{model}/image", method = RequestMethod.POST)
	public @ResponseBody void tokenUploadImage(@PathVariable String model, HttpServletRequest request,HttpServletResponse response) {
		ResultVo vo = new ResultVo();
		try {
			TokenVo token = (TokenVo)request.getAttribute("token");
			MultipartHttpServletRequest multipartRequest = null;
			if(request instanceof MultipartHttpServletRequest){
				 multipartRequest = (MultipartHttpServletRequest)request;
			}
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			vo.setCode(Result.SUCESSFUL);
			String uploadurl = commonService.upload(token, files,model);
			if(uploadurl!=null){//上传成功将url返回给客户端 按照客户端要求返回
				JSONObject result = new JSONObject();
				response.setContentType("application/json;charset=UTF-8");
				//response.setHeader("Content-type", "application/json;charset=UTF-8");
				OutputStream ps = response.getOutputStream();
				// 这句话的意思，使得放入流的数据是utf8格式
				result.put("link",uploadurl);
				ps.write(result.toString().getBytes("UTF-8"));
				ps.close();
			}else{
				JSONObject result = new JSONObject();
				response.setContentType("application/json;charset=UTF-8");
				OutputStream ps = response.getOutputStream();
				ps.write(result.toString().getBytes("UTF-8"));
				ps.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			vo.setCode(Result.FAUL);
			vo.setMessage(e.getMessage());
		}

	}
	@RequestMapping(value = "/upload/{model}/image", method = RequestMethod.POST)
	public @ResponseBody void uploadImage(@PathVariable String model, HttpServletRequest request,HttpServletResponse response) {
		ResultVo vo = new ResultVo();
		try {
			TokenVo token = new TokenVo();
			token.setUserId(0);
			MultipartHttpServletRequest multipartRequest = null;
			if(request instanceof MultipartHttpServletRequest){
				 multipartRequest = (MultipartHttpServletRequest)request;
			}
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			vo.setCode(Result.SUCESSFUL);
			String uploadurl = commonService.upload(token, files,model);
			if(uploadurl!=null){//上传成功将url返回给客户端 按照客户端要求返回
				JSONObject result = new JSONObject();
				response.setContentType("application/json;charset=UTF-8");
				//response.setHeader("Content-type", "application/json;charset=UTF-8");
				OutputStream ps = response.getOutputStream();
				// 这句话的意思，使得放入流的数据是utf8格式
				result.put("image_src",uploadurl);
                JSONObject result1 = new JSONObject();
                result1.put("data",result.toString());
                result1.put("status", "success");
				ps.write(result1.toString().getBytes("UTF-8"));
				ps.close();
			}else{
				JSONObject result = new JSONObject();
				response.setContentType("application/json;charset=UTF-8");
				OutputStream ps = response.getOutputStream();
				ps.write(result.toString().getBytes("UTF-8"));
				ps.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			vo.setCode(Result.FAUL);
			vo.setMessage(e.getMessage());
		}

	}
}
