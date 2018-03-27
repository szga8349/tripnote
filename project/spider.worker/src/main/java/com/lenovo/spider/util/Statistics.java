package com.lenovo.spider.util;

/**
 * 统计工具
 * @author：涂有
 * @date 2017年12月8日 下午3:40:02
 */
public class Statistics {

	/**
	 * 统计源字符串中目标字符串出现的次数
	 * @author：涂有
	 * @date 2017年12月8日 下午3:40:31
	 * @param src
	 * @param dest
	 * @return
	 */
	public static int count(String src, String dest){
		int count = 0;
	    int index = 0;
	    while ((index = src.indexOf(dest, index)) != -1) {
	        index = index + dest.length();
	        count++;
	    }
	    return count;
	}
}
