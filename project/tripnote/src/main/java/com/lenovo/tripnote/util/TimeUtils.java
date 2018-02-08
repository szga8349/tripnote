package com.lenovo.tripnote.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.log4j.Log4j;

@Log4j
public class TimeUtils {
	public static Date getDate(String dateString) {
		if (StringUtils.isEmpty(dateString))
			return null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateformat.parse(dateString);
		} catch (ParseException e) {
			try {
				dateformat = new SimpleDateFormat("HH:mm");
				return dateformat.parse(dateString);
			} catch (ParseException e1) {
				log.error(e1.getMessage(), e1);
				e1.printStackTrace();
			}
		}
		return null;
	}

	public static Date getAfterDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	public static String getDateString(Date date, String string) {
		SimpleDateFormat dateformat = new SimpleDateFormat(string);
		return dateformat.format(date);
	}

	public static int getDayofweek(String date) {
		Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date(System.currentTimeMillis()));
		if (date.equals("")) {
			cal.setTime(new Date(System.currentTimeMillis()));
		} else {
			cal.setTime(new Date(getDate(date).getTime()));
		}
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	public static int getDayofweek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

}
