package com.lenovo.spider.pipeline;

import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.spider.common.Config;
import com.lenovo.spider.model.DataModel;
import com.lenovo.spider.model.MaFengWoHotelModel;
import com.lenovo.spider.model.MaFengWoPoiJdModel;
import com.lenovo.spider.util.HttpUtil;
import com.lenovo.spider.util.UrlType;
import com.lenovo.tripnote.entity.BHotelWithBLOBs;
import com.lenovo.tripnote.entity.BPoiWithBLOBs;
import com.lenovo.tripnote.service.JdbcService;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoadDataToStore extends Thread {
	private static String httpUrl = Config.get("impage.url");
	private static String imageOutPath = Config.get("impage.path");
	private LinkedBlockingQueue<DataModel> dataQueue;
	private JdbcService jdbcService;

	public LoadDataToStore(LinkedBlockingQueue<DataModel> dataQueue,JdbcService jdbcService) {
		this.setDaemon(true);
		this.setName("LoadDataToStore");
		this.dataQueue = dataQueue;
		this.jdbcService = jdbcService;
	}

	@Override
	public void run() {

		while (true) {
			try {
				final DataModel data = dataQueue.take();
				if (data != null) {
					JSON json = data.getJsonString();
					if (json instanceof JSONObject) {

						if (data.getUrl().indexOf("/hotel/") > 0)// 酒店数据
						{
							MaFengWoHotelModel hotelModel = json.toJavaObject(MaFengWoHotelModel.class);
							try {
								hotelModel.setImageUrl(HttpUtil.downLoadImage(hotelModel.getImageUrl(), imageOutPath, httpUrl));
								jdbcService.addHotel(convertHotel(hotelModel));
							} catch (Exception e) {
								System.out.println(String.format("出现异常地址：%s,上一层连接地址:%s", data.getUrl(),data.getParentUrl()));
								log.error(e.getMessage(),e.fillInStackTrace());
							}
				
						} else if (data.getUrl().indexOf("/poi/") > 0) {// poi数据
							MaFengWoPoiJdModel poiModel = json.toJavaObject(MaFengWoPoiJdModel.class);
							try {
								poiModel.setImageurl(HttpUtil.downLoadImage(poiModel.getImageurl(), imageOutPath, httpUrl));
								jdbcService.addPoi(convertPoi(poiModel,UrlType.getPoiType(data.getParentUrl())));
							} catch ( Exception e ) {
								System.out.println(String.format("出现异常地址：%s,上一层连接地址:%s", data.getUrl(),data.getParentUrl()));
								log.error(e.getMessage(),e.fillInStackTrace());
							}
						
						}

					} else
						log.info("Json isnot instanceof JsonObject");
				}
			} catch (InterruptedException e) {
			}
		}
	}

	public BPoiWithBLOBs convertPoi(MaFengWoPoiJdModel model,int type){
		BPoiWithBLOBs poi = new BPoiWithBLOBs();
		poi.setNameCn(model.getTitile());
		poi.setType(type);
		poi.setAddress(model.getLocation());
		poi.setTrafficInstructions(model.getTraffic());
		poi.setUrl(model.getUrl());
		poi.setImageurl(model.getImageurl());
		poi.setOpenTime(model.getOpentime());
		poi.setPhone(model.getPhone());
		poi.setAddressInstrations(model.getSummary());
		poi.setSupplier("mafengwo");
		poi.setCreateUserId(0);
		return poi;
	}
	public BHotelWithBLOBs convertHotel(MaFengWoHotelModel model){
		BHotelWithBLOBs poi = new BHotelWithBLOBs();
		poi.setNameCn(model.getTitile());
		poi.setNameEn(model.getEnTitle());
		poi.setImageurl(model.getImageUrl());
		poi.setIntroduction(model.getIntroduction());
		poi.setStrategy(model.getStrategy());
		poi.setCheckInTime(model.getCheckInTime());
		poi.setCheckOuTime(model.getCheckOuTime());
		poi.setAddress(model.getAddress()!=null?model.getAddress().replace("地址：", ""):model.getAddress());
		poi.setType(6);
		poi.setSupplier("mafengwo");
		poi.setCreateUserId(0);
		return poi;
	}
}
