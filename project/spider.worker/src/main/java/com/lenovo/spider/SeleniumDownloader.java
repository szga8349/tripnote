package com.lenovo.spider;

import java.io.Closeable;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.lenovo.exception.LocateException;
import com.lenovo.spider.common.Constant;
import com.lenovo.spider.selenium.EventType;
import com.lenovo.spider.selenium.SeleniumUtil;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.vo.AuthInfo;
import com.lenovo.spider.vo.AuthLocator;
import com.lenovo.spider.vo.IpInfo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * Selenium支持动态js渲染
 *
 * @author：涂有
 * @date 2017年12月8日 上午10:31:36
 */
public class SeleniumDownloader implements Downloader, Closeable {

    private Logger logger = LogUtil.getLogger("download");

    protected ThreadLocalRandom random = ThreadLocalRandom.current();
    protected RichSpider spider;
    protected WebDriver webDriver;
    protected AuthInfo authInfo;
    protected AuthLocator locator;

    public SeleniumDownloader(RichSpider spider, AuthInfo authInfo, AuthLocator locator) throws LocateException {
        this.spider = spider;
        this.authInfo = authInfo;
        this.locator = locator;
        IpInfo ip = spider.getIp();
        if (ip != null && ip.getIp() != null) {
            webDriver = SeleniumUtil.newWebDriver(ip.getIp() + ":" + ip.getPort());
        } else {
            webDriver = SeleniumUtil.newWebDriver();
        }

        if (authInfo != null && locator != null &&
                locator.getLoginType() != null &&
                locator.getLoginType() == 1) { //需要登录
            SeleniumUtil.login(webDriver, authInfo, locator);
        }
    }

    @Override
    public void close() {
        SeleniumUtil.destoryWebDriver(webDriver);
    }

    @Override
    public Page download(Request request, Task task) {
/*
        WebDriver.Options manage = webDriver.manage();
        Site site = task.getSite();*/
//        if (site.getCookies() != null) {
//            for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
//                Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
//                manage.addCookie(cookie);
//            }
//        }

        logger.info("downloading page " + request.getUrl());
        webDriver.get(request.getUrl());
        //sleep(site.getSleepTime());

        Object domEvent = request.getExtra(Constant.DOM_EVENT);
        if (domEvent != null && domEvent instanceof DomEvent) {
            // 有脚本执行或者dom事件的在这里执行
            int executeCount = MapUtils.getInteger(request.getExtras(), Constant.EXECUTE_COUNT, 1);
            handleScriptEvent(webDriver, (DomEvent) domEvent, executeCount);
            if (((DomEvent) domEvent).getEventType() == EventType.SCROLL) {
                // 滚动翻页事件的要增加滚动次数
                request.putExtra(Constant.EXECUTE_COUNT, executeCount + 1);
            }
        }

        String content = webDriver.getPageSource();
        Page page = new Page();
        page.setRawText(content);
        // 这里需要记录页面的真实地址
        System.out.println(String.format("真实地址：%s,上一层连接地址:%s", webDriver.getCurrentUrl(),request.getExtra(Constant.parentKey)));
        page.setUrl(new PlainText(webDriver.getCurrentUrl()));
        page.setRequest(request);
        return page;
    }

    @Override
    public void setThread(int threadNum) {
    }

    /**
     * 执行事件
     *
     * @param webDriver
     * @param domEvent
     */
    private void handleScriptEvent(WebDriver webDriver, DomEvent domEvent, int executeCount) {
        while (executeCount > 0) {
            EventType eventType = domEvent.getEventType();
            if (eventType == null) {
                break;
            }
            eventType.invoke(webDriver, domEvent.getEventTargetLocator());
            sleep(2000);
            executeCount--;
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
