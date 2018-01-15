package com.lenovo.tripnote.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lenovo.tripnote.entity.BAccount;

public interface CommonService {

	/**
	 * @param account
	 * @param files
	 * @param model:模块 根据不同的模块将图片放到不同的文件夹下
	 * @return
	 */
	String upload(BAccount account, Map<String, MultipartFile> files,String model);

}
