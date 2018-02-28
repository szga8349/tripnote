package com.lenovo.tripnote.converter;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class LongConvert implements Converter<String, Long> {

    @Override
    public Long convert(String stringDate) {
    	if(StringUtils.isEmpty(stringDate) || StringUtils.equals(stringDate, "null"))
            return null;
    	else return Long.valueOf(stringDate);
    }
}  