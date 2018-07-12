package com.lenovo.tripnote.webchat.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lenovo.tripnote.webchat.service.CommonService;
import com.lenovo.tripnote.webchat.vo.TokenVo;
import com.lenovo.tripnote.webchat.vo.UploadFileVo;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@PropertySource(value="classpath:conf/common.properties",encoding="UTF-8")
public class CommonServiceImpl implements CommonService {
	@Value("${impage.path}")
	private String uploadPath = ".";
	/**
	 * 上传文件后返回的url路径 根据配置文件中得到 主要为了图片和应用可以进行分离
	 */
	@Getter
	@Value("${impage.url}")
	private String imageUrl = "http://localhost:8080";

	@Override
	public List<UploadFileVo> upload(TokenVo account, Map<String, MultipartFile> files, String model) {
		List<UploadFileVo> uploadFiles = new ArrayList<UploadFileVo>();
		UploadFileVo uploadfile = new UploadFileVo();
		for (Entry<String, MultipartFile> entry : files.entrySet()) {
			InputStream input = null;
			FileOutputStream fileout = null;
			try {
				MultipartFile file = entry.getValue();
				input = file.getInputStream();
				//获取文件名后缀
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String newFile = UUID.randomUUID().toString()+"."+extension;
				File parentFile = new File(uploadPath, model);
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				File outParent = new File(parentFile, account.getUserId().toString());
				if (!outParent.exists()) {
					outParent.mkdirs();
				}
				File out = new File(outParent, newFile);
				if (!out.exists()) {
					out.createNewFile();
				}
				fileout = new FileOutputStream(out);
				IOUtils.copy(input, fileout);
				
				imageUrl = imageUrl.endsWith("/")?imageUrl:imageUrl+"/";
				uploadfile.setFileurl(imageUrl+model+"/"+account.getUserId()+"/"+newFile);
				uploadfile.setFilename(out.getName());
				uploadFiles.add(uploadfile);
				//break;
			} catch (Exception e) {//多个文件上传时 如果有一个上传失败 即判定为失败
				log.error("上传图片失败", e);
				uploadFiles.clear();
				break;
			} finally {
				if (fileout != null) {
					try {
						fileout.close();
					} catch (IOException e) {
						log.error("上传图片失败", e);
					}
				}
			}
		}
		return uploadFiles;
	}

}