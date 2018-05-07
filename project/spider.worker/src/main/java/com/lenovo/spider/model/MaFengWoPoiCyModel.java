package com.lenovo.spider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**马蜂窝POI(餐饮)数据模板 
 * @author shijy2
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class MaFengWoPoiCyModel {
	@ExtractBy("//div[@class='title']/h1/text()")
	private String Titile;
	@ExtractBy("//h3[contains(text(), '简介')]/following-sibling::p/text()")
	private String Summary;
	@ExtractBy("//h3[contains(text(), '电话')]/following-sibling::p/text()")
	private String Phone;
	@ExtractBy("//li[@class='item-site']/div[@class='content']/a/text()")
	private String Url;
	@ExtractBy("//h3[contains(text(), '交通')]/following-sibling::p/text()")
	private String Traffic;
	@ExtractBy("//div[@class='pic-r']/a/img/@src")
	private String Imageurl;
	@ExtractBy("//dt[contains(text(), '门票')]/following-sibling::dd/allText()")
	private String Ticket;
	@ExtractBy("//h3[contains(text(), '开放时间')]/following-sibling::p/allText()")
	private String Opentime;
	@ExtractBy("//h3[contains(text(), '地址')]/following-sibling::p/text()")
	private String Location ;
}
