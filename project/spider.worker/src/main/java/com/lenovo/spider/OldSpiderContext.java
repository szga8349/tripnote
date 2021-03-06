//package com.lenovo.spider;
//
//import com.lenovo.exception.LocateException;
//import com.lenovo.exception.NetException;
//import com.lenovo.spider.common.Config;
//import com.lenovo.spider.common.Constant;
//import com.lenovo.spider.common.IPCount;
//import com.lenovo.spider.common.ThreadPool;
//import com.lenovo.spider.interfaces.ConfigInterface;
//import com.lenovo.spider.interfaces.HBaseInterface;
//import com.lenovo.spider.interfaces.HttpLogin;
//import com.lenovo.spider.util.LogUtil;
//import com.lenovo.spider.vo.*;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.Logger;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.pipeline.ConsolePipeline;
//import us.codecraft.webmagic.utils.UrlUtils;
//
//import java.util.List;
//import java.util.Map.Entry;
//import java.util.Vector;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 应用程序入口
// *
// * @author：涂有
// * @date 2017年12月7日 下午5:33:55
// */
//public class SpiderContext {
//
//    /**
//     * 记录活动的spider，方便程序kill时持久化ip，重启后可以继续使用ip
//     */
//    protected static Vector<RichSpider> spiders = new Vector<>();
//    protected static Logger spiderLogger = LogUtil.getLogger("spider");
//    protected static Logger statusLogger = LogUtil.getLogger("status");
//    protected static Logger exceptionLogger = LogUtil.getLogger("interfaceException");
//
//    /**
//     * 每个网站的总下载和下载失败的统计
//     */
//    protected static ConcurrentHashMap<String, AtomicInteger> totalCount = new ConcurrentHashMap<>();
//    protected static ConcurrentHashMap<String, AtomicInteger> failCount = new ConcurrentHashMap<>();
//
//    public static void main(String[] args) {
//
//        //监听程序关闭时，持久化及关闭资源，linux用kill不要加-9
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                IPCount.getInstance().store();
//                CachePipline.store();
//                HBaseInterface.close();
//                for (int i = spiders.size() - 1; i >= 0; i--) {
//                    spiders.get(i).stop();
//                }
//            }
//        });
//
//        ThreadPool.scheduleAtFixedRate(() -> printSpiderStatus(), 1, 1, TimeUnit.MINUTES);
//
//        int spiderAmount = Integer.parseInt(Config.get("spiderAmount"));
//        String siteExclude = Config.get(Constant.siteExclude);
//
//        while (true) {
//            statusLogger.info("允许最大爬虫数量：{}， 存活爬虫：{}", spiderAmount, spiders.size());
//            if (spiders.size() > spiderAmount - 1) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException i) {
//                }
//                continue;
//            }
//
//            try {
//                List<SiteInfo> sites = ConfigInterface.selectSite();
//                if (!sites.isEmpty()) {
//                    for (SiteInfo site : sites) {
//                        String siteId = site.getId().toString();
//                        if (StringUtils.contains(siteExclude, siteId) || site.getCrawlerable() != 1) {
//                            // 在排除列表，或不在包含列表中的忽略掉
//                            continue;
//                        }
//
//                        List<IpInfo> ips = ConfigInterface.getIp(site, 1);
//                        if (ips.isEmpty()) {
//                            statusLogger.info("无可用ip，无法启动更多爬虫。");
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException i) {
//                            }
//                            Boolean workWithoutProxy = Boolean.valueOf(Config.get("workWithoutProxy"));
//                            if (workWithoutProxy) {
//                                IpInfo ip = new IpInfo();
//                                ip.setSite(site);
//                                startSpider(ip);
//                            }
//                        } else {
//                            for (IpInfo ip : ips) {
//                                startSpider(ip);
//                            }
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                exceptionLogger.error("发生异常", e);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException i) {
//                }
//            }
//        }
//    }
//
//    public static void startSpider(IpInfo ip) throws NetException, LocateException {
//        SiteInfo site = ip.getSite();
//
//        List<UrlInfo> urls = ConfigInterface.selectUrl(site.getId());
//        if (urls.size() > 0) {
//            Site siteCookie = new Site()
//                    .addHeader("User-Agent", Config.get("UserAgent"))
//                    .setDomain(UrlUtils.getDomain(site.getDomainName()))
//                    .setTimeOut(60000)
//                    .setRetryTimes(3) //HttpClient下载失败的重试次数，中间不会切换ip
//                    .setSleepTime(5000) //请求失败或者成功之后的睡眠时间
//                    .setCycleRetryTimes(0) //请求失败后重试多少次
//                    .setRetrySleepTime(1000); //重试之间睡眠时间
//
//            AuthInfo authInfo = null;
//            AuthLocator locator = null;
//            if (site.getAuthType() != null && (site.getAuthType() == 2 || site.getAuthType() == 3)) {
//                try {
//                    authInfo = ConfigInterface.selectAuth(site.getId());
//                    if (authInfo != null && StringUtils.isNotBlank(authInfo.getCookie())) { //需要cookie的站点
//                        siteCookie.addHeader("Cookie", authInfo.getCookie());
//                    }
//
//                    if (site.getAuthType() == 3) { //需要登录
//                        locator = ConfigInterface.selectAuthLocator(site.getId());
//                        if (locator != null && locator.getLoginType() != null && locator.getLoginType() == 2) {
//                            String cookie = HttpLogin.login(locator.getHttpPostUrl(), authInfo.getCookie(),
//                                    locator.getUsernameLocator(), authInfo.getUsername(),
//                                    locator.getPasswordLocator(), authInfo.getPassword());
//                            siteCookie.addCookie("Cookie", cookie);
//                        }
//                    }
//                } catch (Exception e) {
//                    return;
//                }
//            }
//
//            List<PageHandle> exceptionHandles = ConfigInterface.selectPageHandler(site.getId());
//            TemplateProcessor processor = new TemplateProcessor(siteCookie, urls, site);
//
//            RichSpider spider = new RichSpider(processor, ip, urls, exceptionHandles);
//            processor.setSpider(spider);
//
//            for (UrlInfo url : urls) {
//                if (url.getType() == 1) { //WebMagic
//                    if (spider.getHttpClientDownloader() == null) {
//                        spider.setHttpClientDownloader(new EnhanceErrorProcessHttpDownloader());
//                    }
//                } else { //Selenium
//                    if (spider.getSeleniumDownloader() == null) {
//                        spider.setSeleniumDownloader(new SeleniumDownloader(spider, authInfo, locator));
//                    }
//                }
//            }
//            spider.addPipeline(new CachePipline());
//            spider.addPipeline(new ConsolePipeline());
//            spider.setScheduler(new MultiSitePriorityRedisScheduler(UrlUtils.getDomain(site.getDomainName()) + "_" + site.getId()));
//            spider.addUrl(site.getDomainName());
//            spider.start();
//        }
//    }
//
//    public static void removeSpider(RichSpider spider) {
//        SpiderContext.spiders.remove(spider);
//    }
//
//    public static void addSpider(RichSpider spider) {
//        SpiderContext.spiders.add(spider);
//    }
//
//    public static int spiderCount() {
//        return spiders.size();
//    }
//
//    public static void printSpiderStatus() {
//        spiderLogger.info("---------------{}-----------------", spiders.size());
//        for (Entry<String, AtomicInteger> entry : failCount.entrySet()) {
//            String domain = entry.getKey();
//            int total = totalCount.get(domain).get();
//            int fail = entry.getValue().get();
//            spiderLogger.info("域名：{}，总：{}，失败率：{}", domain, total, fail * 1.0 / total);
//        }
//        for (RichSpider spider : spiders) {
//            spiderLogger.info(spider.toString());
//        }
//    }
//
//    public static void increTotalCount(String name) {
//        totalCount.putIfAbsent(name, new AtomicInteger());
//        totalCount.get(name).incrementAndGet();
//    }
//
//    public static void increFailCount(String name) {
//        failCount.putIfAbsent(name, new AtomicInteger());
//        failCount.get(name).incrementAndGet();
//    }
//}
