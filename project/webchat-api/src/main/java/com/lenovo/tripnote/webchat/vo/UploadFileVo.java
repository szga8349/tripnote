package com.lenovo.tripnote.webchat.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadFileVo {
	
	/**
	 * 上传后的文件名
	 */
	private String filename;
	/**
	 * 上传后访问的url地址
	 */
	private String fileurl;

}
