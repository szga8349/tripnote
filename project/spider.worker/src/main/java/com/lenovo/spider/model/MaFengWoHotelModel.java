package com.lenovo.spider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**马蜂窝酒店数据模板
 * @author shijy2
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class MaFengWoHotelModel {
	@ExtractBy("//div[@class='main-title']/h1/text()")
	private String Titile;
	@ExtractBy("//div[@class='en-title']/span/text()")
	private String EnTitle;
	@ExtractBy("//div[@class='location']/span/@title")
	private String Address;
	@ExtractBy("//div[@class='img-big _j_album_trigger clickstat ']/img/@src")
	private String ImageUrl;
	@ExtractBy("//div[@class='img-big _j_album_trigger clickstat ']/div[@class='exp']/p/text()")
	private String introduction;
	@ExtractBy("//div[@id='_j_hotel_info']//span[contains(text(), '入住时间:')]/following-sibling::span/allText()")
	private String checkInTime;
	@ExtractBy("//div[@id='_j_hotel_info']//span[contains(text(), '离店时间:')]/following-sibling::span/allText()")
	private String checkOuTime;
	/**
	 * 酒店攻略
	 */
	@ExtractBy("//div[@id='_j_hotel_info']//dt[contains(text(), '酒店攻略')]/following-sibling::dd/allText()")
	private String strategy;
}
