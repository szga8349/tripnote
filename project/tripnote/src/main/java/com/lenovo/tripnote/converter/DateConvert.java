package com.lenovo.tripnote.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.lenovo.tripnote.util.TimeUtils;

public class DateConvert implements Converter<String, Date> {

    @Override
    public Date convert(String stringDate) {
       return TimeUtils.getDate(stringDate);
    }

}