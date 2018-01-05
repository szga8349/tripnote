package com.lenovo.tripnote.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.log4j.Log4j;
@Log4j
public class TimeUtils {
	public static Date getDate(String dateString){
		if (StringUtils.isEmpty(dateString))
			return null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateformat.parse(dateString);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
			e.fillInStackTrace();
		}
		return null;
	}
}
