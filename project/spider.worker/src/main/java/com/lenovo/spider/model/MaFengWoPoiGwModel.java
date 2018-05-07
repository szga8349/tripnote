package com.lenovo.spider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**马蜂窝POI(购物)数据模板 
 * @author shijy2
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class MaFengWoPoiGwModel {
	@ExtractBy("//div[@class='title']/h1/text()")
	private String Titile;
	@ExtractBy("//dt[contains(text(), '简介')]/following-sibling::dd/allText()")
	private String Summary;
	@ExtractBy("//li[@class='item-tel']/div[@class='content']/text()")
	private String Phone;
	@ExtractBy("//li[@class='item-site']/div[@class='content']/a/text()")
	private String Url;
	@ExtractBy("//dt[contains(text(), '交通')]/following-sibling::dd/allText()")
	private String Traffic;
	@ExtractBy("//a[@class='cover']/img/@src")
	private String Imageurl;
	@ExtractBy("//dt[contains(text(), '门票')]/following-sibling::dd/allText()")
	private String Ticket;
	@ExtractBy("//li[@class='item-time']/div[@class='content']/text()")
	private String Opentime;
	@ExtractBy("//div[@class='address']/text()")
	private String Location ;
}
