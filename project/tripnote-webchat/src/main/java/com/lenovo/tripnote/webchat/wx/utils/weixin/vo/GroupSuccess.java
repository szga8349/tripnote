package com.lenovo.tripnote.webchat.wx.utils.weixin.vo;

/**
 * @Description:  拼团信息
 * @Date: 2018/08/07
 * @Author: dujz1
 */
public class GroupSuccess {

//	商品名称
//			{{keyword1.DATA}}
//
//			参团人数
//			{{keyword2.DATA}}
//
//			原价
//			{{keyword3.DATA}}
//
//			拼团价格
//			{{keyword4.DATA}}
//
//			温馨提示
//			{{keyword5.DATA}}

	private String formId;			//预支付id
	private String openId;			//用户id
	private String productName;		//商品名称
	private String groupNum;		//参团人数
	private String rawPrice;		//原价

	public String getProductName() {
		return productName;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setProductName(String productName) {
		this.productName = productName;

	}

	public String getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}

	public String getRawPrice() {
		return rawPrice;
	}

	public void setRawPrice(String rawPrice) {
		this.rawPrice = rawPrice;
	}

	public String getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(String groupPrice) {
		this.groupPrice = groupPrice;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	private String groupPrice;		//拼团价格
	private String tips;			//温馨提示




}
