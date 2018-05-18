package com.lenovo.spider;

import static com.lenovo.spider.util.PageUtils.trimHtml;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import com.lenovo.exception.LocateException;
import com.lenovo.spider.common.IPCount;
import com.lenovo.spider.interfaces.ConfigInterface;
import com.lenovo.spider.util.ConvertUtil;
import com.lenovo.spider.util.DateUtil;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.util.Statistics;
import com.lenovo.spider.vo.AuthInfo;
import com.lenovo.spider.vo.AuthLocator;
import com.lenovo.spider.vo.IpInfo;
import com.lenovo.spider.vo.PageHandle;
import com.lenovo.spider.vo.UrlInfo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * 爬虫
 * 重写了Spider的run方法，由于run方法及其引用的几个方法都是private的不能继承
 * 所以直接重写修改了对应方法的一些逻辑
 *
 * @author：涂有
 * @date 2017年12月8日 下午2:43:42
 */
public class RichSpider extends Spider {

    protected String id = UUID.randomUUID().toString();
    protected IpInfo ip;
    protected static IPCount ipPool = IPCount.getInstance();
    protected Logger statusLogger = LogUtil.getLogger("status");
    protected Logger timeLogger = LogUtil.getLogger("time");
    protected Logger pageExceptionLogger = LogUtil.getLogger("pageException");

    /**
     * 后台配置的url下载方式
     */
    protected List<UrlInfo> urls;
    protected List<PageHandle> exceptionHandles;

    /**
     * 复写，默认为false
     */
    protected boolean exitWhenComplete = false;

    protected HttpClientDownloader httpClientDownloader;
    protected SeleniumDownloader seleniumDownloader;

    protected List<SpiderListener> spiderListeners;
    protected final AtomicLong pageCount = new AtomicLong(0);
    protected Date startTime;
    protected int emptySleepTime = 30000;

    protected int failCount;

    public RichSpider(PageProcessor pageProcessor, IpInfo ip, List<UrlInfo> urls, List<PageHandle> exceptionHandles) {
        super(pageProcessor);
        this.ip = ip;
        this.urls = urls;
        this.exceptionHandles = exceptionHandles;
        SpiderContext.addSpider(this);
    }

    @Override
    public void test(String... urls) {
        for (String url : urls) {
            this.test(new Request(url));
        }
    }

    public void test(Request... requests) {
        initComponent();
        for (Request request : requests) {
            this.processRequest(request);
        }
    }

    @Override
    public void run() {
        checkRunningStat();
        initComponent();
        logger.info("Spider {} started!", getUUID());
        startTime = new Date();
        while (!Thread.currentThread().isInterrupted() && stat.get() == STAT_RUNNING) {

            //检查ip是否过期
            if (ip != null && ip.getDeadline() != null
                    && System.currentTimeMillis() > DateUtil.parse(ip.getDeadline()).getTime()) {
            	reSetProxyIP(null);
                continue;
            }

            final Request request = scheduler.poll(this);
            if (request == null) {
                if (threadPool.getThreadAlive() == 0 && exitWhenComplete) {
                    break;
                }
                //睡眠一下，避免空循环浪费网络及cpu资源
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            } else {
                //Spider中下面的语句是提交到线程池处理的，我没有提交到线程池
                //方便处理完再到redis拿链接，避免拿链接的速度太快，处理跟不上导致线程池堆积
                try {
                    pageCount.incrementAndGet();
                    SpiderContext.increTotalCount(getSite().getDomain());
                    processRequest(request);
                    onSuccess(request);
                } catch (Exception e) {
                	if(e instanceof org.openqa.selenium.WebDriverException){//代理IP被封后 重新获取代理IP信息 然后在做爬取操作
                		reSetProxyIP(request);
                	}
                    logger.error("process request error: {}, url:{}", ip, request.getUrl(), e);
                }
            }
        }
        stat.set(STAT_STOPPED);
        // release some resources
        if (destroyWhenExit) {
            close();
        }
        logger.info("Spider {} closed! {} pages downloaded.", getUUID(), pageCount.get());
    }
   public void reSetProxyIP(Request request){
	   try {
			List<IpInfo> ipinfos = ConfigInterface.getIp(this.ip.getSite(), 1);
			if(ipinfos!=null && !ipinfos.isEmpty()){
				ip = ipinfos.get(0);
				logger.info("obtain ip proxy to use",ip);
				if(seleniumDownloader!=null){
					AuthInfo authInfo = seleniumDownloader.authInfo;
					AuthLocator locator = seleniumDownloader.locator;
					seleniumDownloader.close();
					seleniumDownloader = null;
					setDownloader(this,this.urls,authInfo,locator);
				}else{
					setDownloader(this,this.urls,null,null);
				}
			}
			else{
				logger.error("can't obtain ip proxy to use");
			}
			if(request!=null){//请求不为空 处理请求
				 processRequest(request);
                 onSuccess(request);
			}
		} catch (Exception e1) {
			if(e1 instanceof  WebDriverException){//出现查找出来的IP代理 不能用 继续寻找下一个代理IP
				reSetProxyIP(request);
			}
			e1.printStackTrace();
			logger.error("can obtain ip error",e1.fillInStackTrace());
		}
   }
    //此方法没做修改
    protected void checkRunningStat() {
        while (true) {
            int statNow = stat.get();
            if (statNow == STAT_RUNNING) {
                throw new IllegalStateException("Spider is already running!");
            }
            if (stat.compareAndSet(statNow, STAT_RUNNING)) {
                break;
            }
        }
    }

    //此方法没做修改
    protected void destroyEach(Object object) {
        if (object instanceof Closeable) {
            try {
                ((Closeable) object).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void setDownloader(RichSpider spider, List<UrlInfo> urls, AuthInfo left,AuthLocator right)
			throws LocateException {
		for (UrlInfo url : urls) {
			if (url.getType() == 1 && spider.getHttpClientDownloader() == null) { // WebMagic
				spider.setHttpClientDownloader(new EnhanceErrorProcessHttpDownloader());
			} else if (url.getType() == 2 && spider.getSeleniumDownloader() == null) { // Selenium
				spider.setSeleniumDownloader(new SeleniumDownloader(spider, left, right));
			}
		}
	}
    /**
     * 判断使用哪种下载器
     *
     * @param url
     * @author：涂有
     * @date 2017年12月8日 下午2:51:54
     */
    public Downloader shouldUseDownload(String url) {
        for (UrlInfo urlInfo : urls) {
            if (url.matches(urlInfo.getUrl())) {
//            if (urlInfo.getUrl().matches(url)) {
                if (urlInfo.getType() == 2) {
                    return seleniumDownloader;
                } else {
                    return httpClientDownloader;
                }
            }
        }
        return httpClientDownloader == null ? seleniumDownloader : httpClientDownloader;
    }

    /**
     * 重写了这方法，加入了异常检测
     *
     * @param request
     * @author：涂有
     * @date 2017年12月25日 下午3:30:15
     */
    protected void processRequest(Request request) {
        Downloader downloader = shouldUseDownload(request.getUrl());
        Page page = null;
        if (downloader instanceof EnhanceErrorProcessHttpDownloader) {
            page = ((EnhanceErrorProcessHttpDownloader) downloader).download(request, this);
        } else {
            page = downloader.download(request, this);
        }
        if (!StringUtils.isBlank(page.getRawText())) {
        	
        	if(org.apache.commons.lang.StringUtils.indexOf(page.getRawText(), "Can't access chrome://theme/IDR_ERROR_NETWORK_GENERIC from an untrusted")>0){
        		throw new WebDriverException("proxy Ip can't open url");
        	}
            //检测是否异常
            for (PageHandle handler : exceptionHandles) {
                int count = 0;
                if (handler.getJudgeWay() == 1) { //包含文本
                    count = Statistics.count(trimHtml(page), handler.getContent());
                } else if (handler.getJudgeWay() == 2) { //xpath存在
                    count = page.getHtml().xpath(handler.getContent()).all().size();
                } else if (handler.getJudgeWay() == 3) { //css存在
                    count = page.getHtml().css(handler.getContent()).all().size();
                }
                if (count >= handler.getOccurTimes()) {
                    if (handler.getRecraw() == 1) { //重新抓取
                        forceAddReqeust(page.getRequest());
                    }
                    pageExceptionLogger.info("url:{}, spider:{}", request.getUrl(), toString());
                    ConfigInterface.setIpSleeping(ip, 12);
                    SpiderContext.increFailCount(getSite().getDomain());
                    stop();
                    return;
                }
            }
        }

        if (page.isDownloadSuccess()) {
            onDownloadSuccess(request, page);
        } else {
            onDownloaderFail(request);
        }
    }

    protected void onDownloadSuccess(Request request, Page page) {
        if (site.getAcceptStatCode().contains(page.getStatusCode())) {
            pageProcessor.process(page);
            extractAndAddRequests(page, spawnUrl);
            if (!page.getResultItems().isSkip()) {
                for (Pipeline pipeline : pipelines) {
                    pipeline.process(page.getResultItems(), this);
                }
            }
        } else {
            logger.warn("page status code error, url:{},  {}", request.getUrl(), ip == null ? "no ip" : ip.toString());
            //如果页面状态码不对，则保存当前url并关闭爬虫
            forceAddReqeust(request);
            failCount++;
            SpiderContext.increFailCount(getSite().getDomain());
        }
        sleep(site.getSleepTime());
        return;
    }

    protected void onDownloaderFail(Request request) {
        if (site.getCycleRetryTimes() == 0) {
            sleep(site.getSleepTime());
        }
        //如果页面状态码不对，则保存当前url并关闭爬虫
        logger.warn("downloader fail,url:{},{}", request.getUrl(), ip.toString());
        forceAddReqeust(request);
        failCount++;
        SpiderContext.increFailCount(getSite().getDomain());
    }

    @Override
    protected void onError(Request request) {
        super.onError(request);
        //保存当前url并关闭爬虫
        forceAddReqeust(request);
        failCount++;
        SpiderContext.increFailCount(getSite().getDomain());
        sleep(site.getSleepTime());
       
    }

    //没做修改
    protected void doCycleRetry(Request request) {
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        } else {
            int cycleTriedTimes = (Integer) cycleTriedTimesObject;
            cycleTriedTimes++;
            if (cycleTriedTimes < site.getCycleRetryTimes()) {
                addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes));
            }
        }
        sleep(site.getRetrySleepTime());
    }

    @Override
    public void stop() {
        ConfigInterface.setIpSleeping(ip, 0);
        long[] time = ConvertUtil.computeTime(System.currentTimeMillis(), startTime.getTime());
        statusLogger.debug("{}，使用时长：{}时{}分{}秒", ip, time[0], time[1], time[2]);
        if (seleniumDownloader != null) {
            seleniumDownloader.close(); //关闭浏览器内核
        }
        SpiderContext.removeSpider(this);
        super.stop();
    }

    /**
     * 强制加入重试队列
     *
     * @param request
     * @author：涂有
     * @date 2017年12月5日 上午10:18:22
     */
    public void forceAddReqeust(Request request) {
        int cycleTriedTimes = Optional.ofNullable(request.getExtra(Request.CYCLE_TRIED_TIMES))
                .map(i -> (int) i)
                .orElse(0);
        request.putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes + 1);
        if (cycleTriedTimes % 3 == 0) {
            // 每重试3次优先级降低1
            request.setPriority(request.getPriority() + 1);
        } else if (cycleTriedTimes % 5 == 0) {
            // 每重试10次优先级增加1
            request.setPriority(request.getPriority() - 1);
        }
        addRequest(request);
    }


    public HttpClientDownloader getHttpClientDownloader() {
        return httpClientDownloader;
    }

    public void setHttpClientDownloader(EnhanceErrorProcessHttpDownloader httpClientDownloader) {
        this.httpClientDownloader = httpClientDownloader;
        if (ip != null && ip.getIp() != null) {
            this.httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy(ip.getIp(), ip.getPort())));
        }
    }

    public SeleniumDownloader getSeleniumDownloader() {
        return seleniumDownloader;
    }

    public void setSeleniumDownloader(SeleniumDownloader seleniumDownloader) {
        this.seleniumDownloader = seleniumDownloader;
    }

    public IpInfo getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "[startTime=" + DateUtil.format(startTime) +
                ", 失败率=" + (pageCount.get() == 0 ? 0 : failCount * 1.0f / pageCount.get()) +
                ", pageCount=" + pageCount.get() + ", ip=" + ip + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RichSpider other = (RichSpider) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
