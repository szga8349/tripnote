package com.lenovo.tripnote.web;

import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.lenovo.tripnote.util.TimeUtils;

@SuppressWarnings("rawtypes")
public class StringToDateConverter extends DateTimeConverter {

	public StringToDateConverter() {
	}

	public StringToDateConverter(Object defaultValue) {
		super(defaultValue);
	}

	protected Class getDefaultType() {
		return java.util.Date.class;
	}

	@Override
	protected Object convertToType(Class targetType, Object value) throws Exception {
		if (value instanceof String) {
			return TimeUtils.getDate((String) value);
		}
		return super.convertToType(targetType, value);
	}
}
