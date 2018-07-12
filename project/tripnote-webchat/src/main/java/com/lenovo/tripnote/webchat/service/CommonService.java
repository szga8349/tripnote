package com.lenovo.tripnote.webchat.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lenovo.tripnote.webchat.vo.TokenVo;
import com.lenovo.tripnote.webchat.vo.UploadFileVo;


public interface CommonService {

	/**
	 * @param account
	 * @param files
	 * @param model:模块 根据不同的模块将图片放到不同的文件夹下
	 * @return
	 */
	List<UploadFileVo> upload(TokenVo account, Map<String, MultipartFile> files,String model);
	

}
