package com.lenovo.spider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;

import com.lenovo.exception.Exceptions;
import com.lenovo.exception.LocateException;
import com.lenovo.spider.common.Config;
import com.lenovo.spider.common.IPCount;
import com.lenovo.spider.common.ThreadPool;
import com.lenovo.spider.interfaces.ConfigInterface;
import com.lenovo.spider.interfaces.HttpLogin;
import com.lenovo.spider.pipeline.JdbcPipline;
import com.lenovo.spider.scheduler.MaFengWoCacheQueueScheduler;
import com.lenovo.spider.selenium.SeleniumUtil;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.vo.AuthInfo;
import com.lenovo.spider.vo.AuthLocator;
import com.lenovo.spider.vo.IpInfo;
import com.lenovo.spider.vo.PageHandle;
import com.lenovo.spider.vo.SiteInfo;
import com.lenovo.spider.vo.UrlInfo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.utils.UrlUtils;

public class SpiderContext {

	/**
	 * 记录活动的spider，方便程序kill时持久化ip，重启后可以继续使用ip
	 */
	protected static Vector<RichSpider> spiders = new Vector<>();
	protected static Logger spiderLogger = LogUtil.getLogger("spider");
	protected static Logger statusLogger = LogUtil.getLogger("status");
	protected static Logger exceptionLogger = LogUtil.getLogger("interfaceException");

	/**
	 * 每个网站的总下载和下载失败的统计
	 */
	protected static ConcurrentHashMap<String, AtomicInteger> totalCount = new ConcurrentHashMap<>();
	protected static ConcurrentHashMap<String, AtomicInteger> failCount = new ConcurrentHashMap<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		// 注册退出Hook
		registerShutdownHook();
		// 每秒打印爬虫状态
		ThreadPool.scheduleAtFixedRate(SpiderContext::printSpiderStatus, 1, 1, TimeUnit.MINUTES);
		// 尝试循环启动Spider
		loopTryStartSpider();
	}

	/**
	 * 注册虚拟机关闭Hook，保证在程序退出时，持久化IP、已抓取的数据，及关闭资源等操作
	 */
	protected static void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			IPCount.getInstance().store();
			CachePipline.store();
			for (int i = spiders.size() - 1; i >= 0; i--) {
				spiders.get(i).stop();
			}
		}));
	}

	private static List<SiteInfo> getSiteInfos() {
		List<SiteInfo> infos = new ArrayList<>();
		SiteInfo /*siteInfo = new SiteInfo();
		siteInfo.setAuthType(1);
		siteInfo.setDomainName("http://www.rruu.com/local/");
		siteInfo.setCrawlerable(1);
		siteInfo.setId(1l);
		infos.add(siteInfo);*/
		siteInfo = new SiteInfo();
		siteInfo.setAuthType(1);
		siteInfo.setDomainName("http://www.mafengwo.cn/mdd/");
		siteInfo.setCrawlerable(1);
		siteInfo.setId(2l);
		infos.add(siteInfo);
		return infos;
	}

	/**
	 * 循环尝试创建Spider并启动
	 */
	protected static void loopTryStartSpider() {
		for (;;) {
			try {
				// 获取要爬取的站点
				List<SiteInfo> sites = getSiteInfos();// ConfigInterface.selectSite();
				if (CollUtil.isEmpty(sites)) {
					Exceptions.throwIgnoreCurrentLoopException("没有可爬的站点");
				}
				// 遍历站点，创建并启动爬虫
				for (SiteInfo site : sites) {
					// 最大可以运行的爬虫数量
					int spiderAmount = Integer.parseInt(Config.get("spiderAmount"));
					statusLogger.info("允许最大爬虫数量：{}， 存活爬虫：{}", spiderAmount, spiders.size());
					if (spiders.size() >= spiderAmount) {
						// 允许超过允许值，等待
						Exceptions.throwIgnoreCurrentLoopException("爬虫数量以达最大值");
					}

					// 只爬crawlerable为1的站点
					if (site.getCrawlerable() != 1) {
						continue;
					}
					createSpider(site).forEach(spider -> {
						spiders.add(spider);
						spider.addPipeline(new JdbcPipline());
						spider.setScheduler(new MaFengWoCacheQueueScheduler("pull"));
						spider.addUrl(site.getDomainName());
						spider.start();
					});
				}
			} catch (Exception e) {
				if (!(e instanceof Exceptions.IgnoreCurrentLoopException)) {
					exceptionLogger.error("发生异常", e);
				}
				sleep1Second();
			}
		}
	}

	/**
	 * 根据站点创建一个针对站点的爬虫 使用这个方法创建的爬虫只有与站点信息相关的属性，没有Pipeline，Scheduler，StartUrl等属性
	 * 为了方便进行测试
	 *
	 * @param siteInfo
	 *            要爬取的站点信息
	 * @return 针对站点的爬虫
	 */
	private static List<UrlInfo> getUrlInfos(Long id) {
		Map<Long, List<UrlInfo>> siteUrlInfos = new HashMap<>();
		if (!siteUrlInfos.containsKey(id)) {
			List<UrlInfo> urls = new ArrayList<UrlInfo>();// ConfigInterface.selectUrl(site.getId());
			
			switch (Integer.parseInt(id.toString())) {
			case 1:
				//任游网采集配置
			{
				UrlInfo info = new UrlInfo();
				// 页面是否分析
				info.setId(2l);
				info.setAnalyzePage(false);
				info.setSavePage(true);
				info.setNextPageType(1);
				info.setHasNextPage(true);
				info.setType(1);
				info.setUrl("https?://www\\.rruu\\.com/local-p*/");
				urls.add(info);
				info = new UrlInfo();
				// 页面是否分析
				info.setId(3l);
				info.setAnalyzePage(true);
				info.setSavePage(true);
				//info.setNextPageType(1);
				info.setHasNextPage(false);
				info.setType(1);
				info.setUrl("https?://www\\.rruu\\.com/\\w+/\\d+.html");
				urls.add(info);
			}
				break;
			case 2:
				//猫途鹰
			  {
				 //分析酒店详细信息
				UrlInfo info = new UrlInfo();
				// 页面是否分析
				info.setId(4l);  //景点详情
				info.setAnalyzePage(true);
				info.setSavePage(false);
				//info.setNextPageType(false);
				info.setHasNextPage(false);
				info.setType(2);
				info.setSort(2d);
				info.setUrl("https?://www\\.mafengwo\\.cn/hotel/\\d+.html");
				urls.add(info);
				
				 //分析POI详细信息
				info = new UrlInfo();
				// 页面是否分析
				info.setId(5l);  //景点详情
				info.setAnalyzePage(true);
				info.setSavePage(false);
				//info.setNextPageType(false);
				info.setHasNextPage(false);
				info.setSort(0d);
				info.setType(2);
				info.setUrl("https?://www\\.mafengwo\\.cn/poi/\\d+.html");
				urls.add(info);
				
				info = new UrlInfo();
				//取目的地下的酒店列表
				info.setId(6l);
				info.setAnalyzePage(false);
				info.setSavePage(false);
				info.setNextPageType(1);
				info.setHasNextPage(false);
				info.setType(2);
				info.setSort(3d);
				info.setNextPageLocator("//div[@class='page-hotel']/a[@class='ti _j_pageitem prev']");
				info.setUrl("https?://www\\.mafengwo\\.cn/hotel/\\d+/.*");
				urls.add(info);
				
				//取目的地下的景点信息
				info = new UrlInfo();
				info.setId(7l);
				info.setAnalyzePage(false);
				info.setSavePage(false);
				info.setNextPageType(1);
				info.setHasNextPage(false);
				info.setType(2);
				info.setSort(1d);
				info.setNextPageLocator("//div[@class='page-hotel']/a[@class='ti _j_pageitem prev']");
				info.setUrl("https?://www\\.mafengwo\\.cn/jd/\\d+/gonglve.html");
				urls.add(info);
				//取目的地下的餐饮信息
				info = new UrlInfo();
				info.setId(8l);
				info.setAnalyzePage(false);
				info.setSavePage(false);
				info.setNextPageType(1);
				info.setHasNextPage(false);
				info.setType(2);
				info.setSort(1d);
				info.setNextPageLocator("//div[@class='page-hotel']/a[@class='ti _j_pageitem prev']");
				info.setUrl("https?://www\\.mafengwo\\.cn/cy/\\d+/gonglve.html");
				urls.add(info);
				//取目的地下的购物信息
				info = new UrlInfo();
				info.setId(9l);
				info.setAnalyzePage(false);
				info.setSavePage(false);
				info.setNextPageType(1);
				info.setHasNextPage(false);
				info.setType(2);
				info.setSort(1d);
				info.setNextPageLocator("//div[@class='page-hotel']/a[@class='ti _j_pageitem prev']");
				info.setUrl("https?://www\\.mafengwo\\.cn/yl/\\d+/gonglve.html");
				urls.add(info);
				//取目的地下的娱乐信息
				info = new UrlInfo();
				info.setId(10l);
				info.setAnalyzePage(false);
				info.setSavePage(false);
				info.setNextPageType(1);
				info.setHasNextPage(false);
				info.setType(2);
				info.setSort(1d);
				info.setNextPageLocator("//div[@class='page-hotel']/a[@class='ti _j_pageitem prev']");
				info.setUrl("https?://www\\.mafengwo\\.cn/gw/\\d+/gonglve.html");
				urls.add(info);
				
				//按照目的地进行解析
				info = new UrlInfo();
				info.setId(11l);
				info.setAnalyzePage(false);
				info.setSavePage(false);
				info.setNextPageType(1);
				info.setHasNextPage(false);
				info.setType(2);
				info.setSort(4d);
				info.setUrl("https?://www\\.mafengwo\\.cn/travel-scenic-spot/mafengwo/\\d+.html");
				urls.add(info);
			  }
				break;
			default:
				break;
			}
			siteUrlInfos.put(id, urls);
			return urls;
		}
		return siteUrlInfos.get(id);
	}
	
	private static List<IpInfo> getIpInfos(SiteInfo siteInfo){
		List<IpInfo> ipinfos = new ArrayList<>();
		IpInfo ip = new IpInfo();
		ip.setDeadline("2018-05-18 11:09:04");
        ip.setIp("114.239.201.167");
        ip.setPort("4317");
        ip.setSite(siteInfo);
		ipinfos.add(ip);
		/*ip = new IpInfo();
		ip.setDeadline("2018-05-03 11:43:02");
        ip.setIp("1.70.119.0");
        ip.setPort("4387");
		ipinfos.add(ip);
		IpInfo ip = new IpInfo();
		ip.setDeadline("2018-05-03 12:49:11");
        ip.setIp("115.223.212.175");
        ip.setPort("9000");
		ipinfos.add(ip);*/
		return ipinfos;
	}

	public static List<RichSpider> createSpider(SiteInfo siteInfo) throws Exception {
		List<RichSpider> list = new ArrayList<>();
		// 根据站点查询链接
		List<UrlInfo> urls = getUrlInfos(siteInfo.getId());// ConfigInterface.selectUrl(siteInfo.getId());
		if (CollUtil.isEmpty(urls)) {
			return list;
		}

		// 需要WebDriver，但已经不能创建（达到池上限）时跳过该网站
		// 原来是阻塞，但是实际使用中，这里阻塞了会影响到并不使用WebDriver的爬虫，导致即使未达总的爬虫上限，也会停止创建新的爬虫。
		if (!checkWhenNeedWebDriver(urls)) {
			return list;
		}

		// 每次获取ip的数量
		List<IpInfo> ips = getIpInfos(siteInfo);//ConfigInterface.getIp(siteInfo, 1);//getIpInfos();//
		if (CollUtil.isEmpty(ips)) {
			Boolean workWithoutProxy = Boolean.valueOf(Config.get("workWithoutProxy"));

			if (!workWithoutProxy) {
				Exceptions.throwIgnoreCurrentLoopException("没有可用IP，无法启动更多爬虫");
			}
			// 添加一个默认的空代理，即不使用代理爬取
			IpInfo ip = new IpInfo();
			ip.setSite(siteInfo);
			ips.add(ip);
		}

		for (IpInfo ip : ips) {
			Site siteCookie = createSite(siteInfo);
			Pair<AuthInfo, AuthLocator> pair = prepareLoginInfo(siteInfo, siteCookie);
			TemplateProcessor processor = new TemplateProcessor(siteCookie, urls, siteInfo);

			List<PageHandle> exceptionHandles = new ArrayList<>();//ConfigInterface.selectPageHandler(siteInfo.getId());
			RichSpider spider = new RichSpider(processor, ip, urls, exceptionHandles);
			processor.setSpider(spider);
			setDownloader(spider, urls, pair);
			list.add(spider);
		}

		return list;
	}

	/**
	 * 设置下载器
	 *
	 * @param spider
	 *            爬虫
	 * @param urls
	 *            要爬取的链接模版
	 * @param pair
	 *            登录需要信息
	 * @throws LocateException
	 */
	private static void setDownloader(RichSpider spider, List<UrlInfo> urls, Pair<AuthInfo, AuthLocator> pair)
			throws LocateException {
		for (UrlInfo url : urls) {
			if (url.getType() == 1 && spider.getHttpClientDownloader() == null) { // WebMagic
				spider.setHttpClientDownloader(new EnhanceErrorProcessHttpDownloader());
			} else if (url.getType() == 2 && spider.getSeleniumDownloader() == null) { // Selenium
				spider.setSeleniumDownloader(new SeleniumDownloader(spider, pair.getLeft(), pair.getRight()));
			}
		}
	}

	/**
	 * 准备登录用的信息
	 *
	 * @param siteInfo
	 *            站点
	 * @param siteCookie
	 *            爬虫站点配置
	 * @return Pair&lt;AuthInfo, AuthLocator&gt;
	 * @throws Exception
	 *             网络请求异常
	 */
	private static Pair<AuthInfo, AuthLocator> prepareLoginInfo(SiteInfo siteInfo, Site siteCookie) throws Exception {
		AuthInfo authInfo = null;
		AuthLocator locator = null;
		if (siteInfo.getAuthType() == 2) {
			authInfo = ConfigInterface.selectAuth(siteInfo.getId());
			if (authInfo != null && StrUtil.isNotBlank(authInfo.getCookie())) {
				siteCookie.addHeader("Cookie", authInfo.getCookie());
			}
		}
		if (siteInfo.getAuthType() == 3) {
			locator = ConfigInterface.selectAuthLocator(siteInfo.getId());
			if (authInfo != null && locator != null && locator.getLoginType() == 2) {
				String cookie = HttpLogin.login(locator.getHttpPostUrl(), authInfo.getCookie(),
						locator.getUsernameLocator(), authInfo.getUsername(), locator.getPasswordLocator(),
						authInfo.getPassword());
				siteCookie.addCookie("Cookie", cookie);
			}
		}
		return Pair.of(authInfo, locator);
	}

	/**
	 * 创建PageProcessor使用的站点配置
	 *
	 * @param siteInfo
	 * @return
	 */
	private static Site createSite(SiteInfo siteInfo) {
		return Site.me().addHeader("User-Agent", Config.get("UserAgent"))
				.setDomain(UrlUtils.getDomain(siteInfo.getDomainName())).setTimeOut(60000).setRetryTimes(3) // HttpClient下载失败的重试次数，中间不会切换ip
				.setSleepTime(5000) // 请求失败或者成功之后的睡眠时间
				.setCycleRetryTimes(0) // 请求失败后重试多少次
				.setRetrySleepTime(1000); // 重试之间睡眠时间
	}

	/**
	 * 根据站点信息创建一个标识，用于队列名称
	 *
	 * @param siteInfo
	 * @return
	 */
	protected static String getSiteIdentity(SiteInfo siteInfo) {
		return UrlUtils.getDomain(siteInfo.getDomainName()) + "_" + siteInfo.getId();
	}

	/**
	 * 需要WebDriver时，检查是否能够创建WebDriver
	 *
	 * @param urls
	 *            网站要爬的链接模版
	 * @return 是否需要并且能创建WebDriver
	 */
	public static boolean checkWhenNeedWebDriver(List<UrlInfo> urls) {
		return !checkNeedWebDriver(urls) || SeleniumUtil.checkCanNewWebDriver();
	}

	/**
	 * 检查是否需要WebDriver
	 *
	 * @param urls
	 * @return
	 */
	public static boolean checkNeedWebDriver(List<UrlInfo> urls) {
		return CollUtil.isNotEmpty(CollUtil.filter(urls, (Filter<UrlInfo>) urlInfo -> urlInfo.getType() == 2));
	}

	/**
	 * 线程睡眠一秒
	 */
	protected static void sleep1Second() {
		trySleep(1000);
	}

	/**
	 * 尝试睡眠
	 *
	 * @param time
	 */
	public static void trySleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException ignore) {
		}
	}

	private static void printSpiderStatus() {
		spiderLogger.info("---------------{}-----------------", spiders.size());
		for (Map.Entry<String, AtomicInteger> entry : failCount.entrySet()) {
			String domain = entry.getKey();
			int total = totalCount.get(domain).get();
			int fail = entry.getValue().get();
			spiderLogger.info("域名：{}，总：{}，失败率：{}", domain, total, fail * 1.0 / total);
		}
		for (RichSpider spider : spiders) {
			spiderLogger.info(spider.toString());
		}
	}

	public static void removeSpider(RichSpider spider) {
		SpiderContext.spiders.remove(spider);
	}

	public static void addSpider(RichSpider spider) {
		SpiderContext.spiders.add(spider);
	}

	public static int spiderCount() {
		return spiders.size();
	}

	public static void increTotalCount(String name) {
		totalCount.putIfAbsent(name, new AtomicInteger());
		totalCount.get(name).incrementAndGet();
	}

	public static void increFailCount(String name) {
		failCount.putIfAbsent(name, new AtomicInteger());
		failCount.get(name).incrementAndGet();
	}
}
