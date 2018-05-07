package com.lenovo.spider.util;

public class UrlType {
	/**根据url判断出poi类型
	 * @param url
	 * @return
	 */
	public static int getPoiType(String url){
		if (url.indexOf("/jd/") > 0) {// 景点类型
               return 2;
		} else if (url.indexOf("/cy/") > 0) {// 餐饮类型
               return 1;
		} else if (url.indexOf("/gw/") > 0) {// 购物
               return 3;
		} else if (url.indexOf("/yl/") > 0) {// 娱乐
               return 4;
		}else return 0;
	}
}
