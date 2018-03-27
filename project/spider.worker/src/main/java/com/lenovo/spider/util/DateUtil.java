package com.lenovo.spider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 * 
 * @author WangYinPing(王崟平)
 */
public class DateUtil {

	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 格式化为短时间格式
	 * @author：涂有
	 * @date 2017年5月4日 下午7:38:27
	 * @param date
	 */
	public static String formatToShort(Date date){
		
		if(date == null){
			return null;
		}
		return format(date, yyyy_MM_dd);
	}
	
	/**
	 * 格式化为长时间格式
	 * @author：涂有
	 * @date 2017年5月4日 下午7:38:58
	 * @param date
	 */
	public static String formatToFull(Date date){
		
		if(date == null){
			return null;
		}
		return format(date, yyyy_MM_dd_HH_mm_ss_SSS);
	}
	
	/**
	 * 得到一天的开始
	 * @author：涂有
	 * @date 2017年5月11日 下午3:32:28
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getDayStart(Date date){

		if(date == null){
			return null;
		}
		
		Date newDate = (Date)date.clone();
		newDate.setHours(0);
		newDate.setMinutes(0);
		newDate.setSeconds(0);
		return newDate;
	}
	
	/**
	 * 得到一天的结束
	 * @author：涂有
	 * @date 2017年5月11日 下午3:33:29
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getDayEnd(Date date){
		
		if(date == null){
			return null;
		}
		
		Date newDate = (Date)date.clone();
		newDate.setHours(23);
		newDate.setMinutes(59);
		newDate.setSeconds(59);
		return newDate;
	}

	/**
	 * 格式化日期,默认返回yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		String result = null;
		if(date != null){
			result = format(date, yyyy_MM_dd_HH_mm_ss);
		}
		return result;
	}

	/**
	 * 日期格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		String result = null;
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 格式化显示当前日期
	 * 
	 * @param format
	 * @return
	 * @author WangYinPing
	 */
	public static String format(String format) {
		return format(new Date(), format);
	}

	/**
	 * 时间格式化， 传入毫秒
	 * 
	 * @param time
	 * @return
	 */
	public static String dateFormat(long time) {
		return format(new Date(time), yyyy_MM_dd_HH_mm_ss);
	}
	
	/**
	 * 得到某一天以前day天的date
	 * @param d
	 * @param day
	 * @return
	 * @author WangYinPing
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到某一天以后day天的date
	 * @param d
	 * @param day
	 * @return
	 * @author WangYinPing
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	/**
	 * 得到某一天以后day天的date
	 * @param d
	 * @param day
	 * @return
	 * @author WangYinPing
	 */
	public static Date getSecondAfter(Date d, int second) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.SECOND, now.get(Calendar.SECOND) + second);
		return now.getTime();
	}
	
	/**
	 * 两个时间对象
	 * @param dateOne
	 * @param dateTwo
	 * @return 相等返回0,小于返回负数，大于返回正数
	 * @author WangYinPing
	 */
	public static long compareDate(Date dateOne, Date dateTwo){
		return dateOne.getTime() - dateTwo.getTime();
	}
	
	/**
	 * 查时间合法性
	 * @param date
	 * @return 合法返回ture,反之false
	 * @author wangyinping
	 */
	public static boolean checkDate(Date date) {
		boolean result = true;
		if (date != null) {
			String dateStr = DateUtil.format(date);
			if (dateStr.equals("0000-00-00 00:00:00")) {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 获取当天指定小时分钟秒的时间
	 * @return
	 * @author wangyinping
	 */
	public static Date getSyncDate(int hourOfDay,int minute,int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar.getTime();
	}
	
	public static Date parse(String date){
		if(date == null){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_SSS);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			format = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
			try {
				return format.parse(date);
			} catch (ParseException e1) {
				format = new SimpleDateFormat(yyyy_MM_dd);
				try {
					return format.parse(date);
				} catch (ParseException e2) {
				}
			}
		}
		return null;
	}
}
