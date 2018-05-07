package com.lenovo.spider.scheduler;

import com.lenovo.spider.common.Constant;

import us.codecraft.webmagic.Request;

// Referenced classes of package us.codecraft.webmagic.scheduler:
//            DuplicateRemovedScheduler, MonitorableScheduler

public class MaFengWoCacheQueueScheduler extends LenovoFileCacheQueueScheduler {

	public MaFengWoCacheQueueScheduler(String filePath) {
		super(filePath);
	}

	@Override
	public boolean parentValid(Request request) {
		if (request.getExtras() ==null)
			return true;
		String url = (String) request.getExtras().get(Constant.parentKey);
		String currenturl = request.getUrl();
		if(currenturl.indexOf("/poi/")>0){//当前需要的解析的页面
			  if(url.indexOf("/jd/") > 0 || url.indexOf("/cy/") > 0
						|| url.indexOf("/gw/") > 0 || url.indexOf("/yl/") > 0){
				  return true;
			  }
			  else return false;
		}
		return true;
	}

}
