package com.lenovo.spider.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * 转换工具类
 * @author：涂有
 * @date 2017年12月7日 下午5:36:44
 */
public class ConvertUtil {

	/**
	 * 把jsonArray转换成java值对象数组
	 * @author：涂有
	 * @date 2017年12月8日 下午2:02:27
	 * @param array
	 * @param c
	 * @return
	 */
	public static <C> List<C> convert(JSONArray array, Class<C> c){
		ArrayList<C> list = new ArrayList<>(array.size());
		for(int i = 0; i < array.size(); i++){
			list.add(array.getObject(i, c));
		}
		return list;
	}
	
	/**
	 * 计算两个时间相差的时分秒
	 * @author：涂有
	 * @date 2017年12月8日 下午2:04:11
	 * @param endTime 截止时间
	 * @param startTime 开始时间
	 * @return
	 */
	public static long[] computeTime(long endTime, long startTime){
    	long interval = endTime - startTime;
    	long hour = interval/1000/60/60;
    	interval -= hour * 1000 * 60 * 60;
    	long minute = interval/1000/60;
    	interval -= minute * 1000 * 60;
    	long second = interval / 1000;
    	return new long[]{hour, minute, second};
    }
}
