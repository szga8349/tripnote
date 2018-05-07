package com.lenovo.spider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**马蜂窝POI(景点)数据模板 
 * @author shijy2
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class MaFengWoPoiJdModel {
	@ExtractBy("//div[@class='title']/h1/text()")
	private String Titile;
	@ExtractBy("//div[@class='summary']/text()")
	private String Summary;
	@ExtractBy("//li[@class='tel']/div[@class='content']/text()")
	private String Phone;
	@ExtractBy("//li[@class='item-site']/div[@class='content']/a/text()")
	private String Url;
	@ExtractBy("//dt[contains(text(), '交通')]/following-sibling::dd/allText()")
	private String Traffic;
	@ExtractBy("//div[@class='pic-big']/img/@src")
	private String Imageurl;
	@ExtractBy("//dt[contains(text(), '门票')]/following-sibling::dd/allText()")
	private String Ticket;
	@ExtractBy("//dt[contains(text(), '开放时间')]/following-sibling::dd/allText()")
	private String Opentime;
	@ExtractBy("//div[@class='mod mod-location']/div[@class='mhd']/p/text()")
	private String Location ;
}
