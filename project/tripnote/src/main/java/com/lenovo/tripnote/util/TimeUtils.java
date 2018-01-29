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
}
