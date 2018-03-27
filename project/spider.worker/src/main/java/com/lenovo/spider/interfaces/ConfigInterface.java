package com.lenovo.spider.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.exception.NetException;
import com.lenovo.spider.common.Config;
import com.lenovo.spider.common.IPCount;
import com.lenovo.spider.util.ConvertUtil;
import com.lenovo.spider.util.DateUtil;
import com.lenovo.spider.util.HttpUtil;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.vo.AuthInfo;
import com.lenovo.spider.vo.AuthLocator;
import com.lenovo.spider.vo.IpInfo;
import com.lenovo.spider.vo.PageHandle;
import com.lenovo.spider.vo.SiteInfo;
import com.lenovo.spider.vo.UrlInfo;
import com.lenovo.spider.vo.UrlTemplate;

/**
 * 后台接口
 * @author：涂有
 * @date 2017年11月2日 上午10:07:21
 */
public class ConfigInterface {
	
	public final static String baseUrl = Config.get("baseUrl");
	private static Logger exceptionLogger = LogUtil.getLogger("interfaceException");
	private static Logger statusLogger = LogUtil.getLogger("status");
	
	private ConfigInterface(){}
	
	private static List<UrlTemplate> parseToObject(JSONArray array){
		if(array == null){
			return new ArrayList<>(); 
		}
		ArrayList<UrlTemplate> list = new ArrayList<>(array.size());
		for(int i = 0; i < array.size(); i++){
			UrlTemplate urlTemplate = array.getObject(i, UrlTemplate.class);
			JSONArray subArray = array.getJSONObject(i).getJSONArray("children");
			if(subArray != null){
				urlTemplate.setChildren(parseToObject(subArray));
			}
			list.add(urlTemplate);
		}
		return list;
	}

	private static JSONObject check(JSONObject result) throws NetException{
		if(result.getIntValue("statusCode") != 200){
			exceptionLogger.error(result.getString("reasonPhrase"), new NetException(result.getString("reasonPhrase")));
			throw new NetException(result.getString("reasonPhrase"));
		}
		JSONObject outerData =  JSONObject.parseObject(result.getString("data"));
		if (outerData.containsKey("tip") && outerData.getIntValue("code") != 200) {
			exceptionLogger.error(outerData.getString("tip"), new NetException(outerData.getString("tip")));
			throw new NetException(outerData.getString("tip"));
		} else if (outerData.containsKey("msg") && outerData.getIntValue("code") != 0) {
			exceptionLogger.error(outerData.getString("msg"), new NetException(outerData.getString("msg")));
			throw new NetException(outerData.getString("msg"));
		}
		return outerData;
	}
	
	/**
	 * 返回所有的站点
	 * @author：涂有
	 * @date 2017年12月7日 下午1:53:03
	 * @return
	 * @throws NetException
	 */
	public static List<SiteInfo> selectSite() throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("pageSize", String.valueOf(Integer.MAX_VALUE));
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/siteInfo/select", map);
		JSONArray array = check(result).getJSONObject("data").getJSONArray("rows");
		return ConvertUtil.convert(array, SiteInfo.class);
	}
	
	/**
	 * 获取搜索根链接信息
	 * @author：涂有
	 * @date 2017年12月7日 下午2:15:31
	 * @param siteId 站点id
	 * @return
	 * @throws NetException
	 */
	public static List<UrlInfo> selectUrl(long siteId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("siteId", String.valueOf(siteId));
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/urlInfo/select", map);
		JSONArray array = check(result).getJSONObject("data").getJSONArray("rows");
		return ConvertUtil.convert(array, UrlInfo.class);
	}
	
	/**
	 * 获取页面模板
	 * @author：涂有
	 * @date 2017年12月7日 下午5:47:05
	 * @param urlId
	 * @return
	 * @throws NetException
	 */
	public static List<UrlTemplate> selectPageTemplate(long urlId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("urlInfoId", String.valueOf(urlId));
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/urlTemplate/getUrlTemplateBySiteUrl", map);
		JSONArray array = check(result).getJSONArray("data");
		return parseToObject(array);
	}
	
	/**
	 * 获取指定站点登录账号信息
	 * @author：涂有
	 * @date 2017年12月7日 下午2:27:22
	 * @param siteId
	 * @return
	 * @throws NetException
	 */
	public static AuthInfo selectAuth(long siteId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("siteId", String.valueOf(siteId));
		map.put("pageSize", "1");
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/authInfo/getUsersForSpider", map);
		JSONArray array = check(result).getJSONArray("data");
		List<AuthInfo> list = ConvertUtil.convert(array, AuthInfo.class);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * 获取登录定位信息
	 * @author：涂有
	 * @date 2017年12月7日 下午2:34:43
	 * @param siteId 站点
	 * @return
	 * @throws NetException
	 */
	public static AuthLocator selectAuthLocator(long siteId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("siteId", String.valueOf(siteId));
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/authLocator/select", map);
		JSONArray array = check(result).getJSONObject("data").getJSONArray("rows");
		List<AuthLocator> list = ConvertUtil.convert(array, AuthLocator.class);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * 得到站点异常处理配置
	 * @author：涂有
	 * @date 2017年12月7日 下午3:19:46
	 * @param siteId 站点id
	 * @return
	 * @throws NetException
	 */
	public static List<PageHandle> selectPageHandler(long siteId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("siteId", String.valueOf(siteId));	
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/pageHandle/select", map);
		JSONArray array = check(result).getJSONObject("data").getJSONArray("rows");
		return ConvertUtil.convert(array, PageHandle.class);
	}
	
	/**
	 * 获取ip
	 * @author：涂有
	 * @date 2017年11月10日 下午1:57:11
	 * @param count 获取数量
	 * @return
	 * @throws NetException
	 */
	public static List<IpInfo> getIp(SiteInfo site, int count) throws NetException{
		if(IPCount.getInstance().getIpCount() >= IPCount.ipMaxAmountForDay){
			return new ArrayList<>();
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("num", String.valueOf(count));
		map.put("siteInfoId", String.valueOf(site.getId()));
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/ipInfo/selectForSpider", map);
		JSONArray array = check(result).getJSONArray("data");
		List<IpInfo> ips = ConvertUtil.convert(array, IpInfo.class);
		for(IpInfo ip : ips){
			ip.setSite(site);
			IPCount.getInstance().addCount();
		}
		return ips;
	}
	
	/**
	 * 确定一个ip是否可用
	 * @author：涂有
	 * @date 2017年12月7日 下午4:55:58
	 * @param ipId
	 * @param siteId
	 * @return
	 * @throws NetException 
	 */
	public static boolean canUse(long ipId, long siteId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("ipInfoId", String.valueOf(ipId));
		map.put("siteInfoId", String.valueOf(ipId));
		map.put("available", "2"); //查询是否禁用
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/ipInfo/selectForSpider", map);
		return check(result).getJSONArray("data").size() > 0 ? false : true;
	}

	/**
	 * 上传抓取统计数据
	 * @author：涂有
	 * @date 2017年12月7日 下午2:54:38
	 * @throws NetException 
	 */
	public static void uploadCrawlerInfo(String name, int usedIpNum, int crawlMsgNum) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("usedIpNum", String.valueOf(usedIpNum));
		map.put("crawlMsgNum", String.valueOf(crawlMsgNum));
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/crawlerInfo/insert", map);
		check(result);
	}

	/**
	 * 设置账号信息失效
	 * @author：涂有
	 * @date 2017年12月8日 下午3:45:38
	 * @param siteId
	 * @return
	 * @throws NetException
	 */
	public static void setAuthUserInvalid(long authId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("siteId", String.valueOf(authId));
		map.put("userStatus", "2");
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/authInfo/updateById", map);
		check(result);
	}
	
	/**
	 * 设置cookie失效
	 * @author：涂有
	 * @date 2017年12月8日 下午3:50:56
	 * @param authId
	 * @throws NetException
	 */
	public static void setAuthCookieInvalid(long authId) throws NetException{
		Map<String, String> map = new HashMap<>();
		map.put("siteId", String.valueOf(authId));
		map.put("cookieStatus", "2");
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/authInfo/updateById", map);
		check(result);
	}
	
	/**
	 * 休眠ip
	 * @author：涂有
	 * @date 2017年12月22日 下午5:04:56
	 * @param ip
	 * @param minutes 分钟
	 * @throws NetException
	 */
	public static void setIpSleeping(IpInfo ip, int minutes){
		
		if(System.currentTimeMillis() >= DateUtil.parse(ip.getDeadline()).getTime() - minutes * 60 * 1000){
			return;
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("id", String.valueOf(ip.getId()));
		map.put("siteId", String.valueOf(ip.getSite().getId()));
		map.put("sleeping", String.valueOf(minutes));
		
		JSONObject result = HttpUtil.postStr(baseUrl + "/ipInfo/setSleeping", map);
		try {
			check(result);
			statusLogger.error("睡眠ip：{}", ip.toString());
		} catch (NetException e) {
			exceptionLogger.error("睡眠ip异常：{}", e.getMessage());
		}
	}
}
