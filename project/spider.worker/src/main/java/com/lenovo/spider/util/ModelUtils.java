package com.lenovo.spider.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.lenovo.spider.model.MaFengWoPoiJdModel;
import com.lenovo.spider.vo.UrlTemplate;

import us.codecraft.webmagic.model.annotation.ExtractBy;

/**
 * 模板转换
 * 
 * @author shijy2
 *
 */
public class ModelUtils {
	public static void main(String[] args) throws Exception {
		getUrlTemPlate(MaFengWoPoiJdModel.class);
	}

	/**
	 * 将model对象转换成模板数组对象
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static List<UrlTemplate> getUrlTemPlate(Class<?> clazz) {
		List<UrlTemplate> urlTemplates = new ArrayList<UrlTemplate>();
		try {
			Field files[] = clazz.getDeclaredFields();
			for (Field fild : files) {
				UrlTemplate tempLate = new UrlTemplate();
				Annotation[] annotations = fild.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof ExtractBy) {
						String by = ((ExtractBy) annotation).value();
						tempLate.setLocator(by);
					}
				}
				tempLate.setType(1);
				//String value = (String) clazz.getMethod("get" + fild.getName()).invoke(obj);
				tempLate.setName(fild.getName());
				urlTemplates.add(tempLate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlTemplates;
	}
}
