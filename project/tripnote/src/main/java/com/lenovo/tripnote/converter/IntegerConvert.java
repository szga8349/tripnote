package com.lenovo.tripnote.converter;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class IntegerConvert implements Converter<String, Integer> {

    @Override
    public Integer convert(String stringDate) {
    	if(StringUtils.isEmpty(stringDate) || StringUtils.equals(stringDate, "null"))
            return null;
    	else return Integer.valueOf(stringDate);
    }
}  