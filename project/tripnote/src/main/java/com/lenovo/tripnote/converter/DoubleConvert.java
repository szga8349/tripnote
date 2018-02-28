package com.lenovo.tripnote.converter;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class DoubleConvert implements Converter<String, Double> {

    @Override
    public Double convert(String stringDate) {
    	if(StringUtils.isEmpty(stringDate) || StringUtils.equals(stringDate, "null"))
            return null;
    	else return Double.valueOf(stringDate);
    }
}  