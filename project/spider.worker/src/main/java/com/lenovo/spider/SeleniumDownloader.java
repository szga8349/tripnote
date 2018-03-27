package com.lenovo.spider;

import com.lenovo.exception.LocateException;
import com.lenovo.spider.selenium.SeleniumUtil;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.vo.AuthInfo;
import com.lenovo.spider.vo.AuthLocator;
import com.lenovo.spider.vo.IpInfo;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

import java.io.Closeable;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

        WebDriver.Options manage = webDriver.manage();
        Site site = task.getSite();
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
                Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }

        logger.info("downloading page " + request.getUrl());
        webDriver.get(request.getUrl());
        try {
            Thread.sleep(site.getSleepTime());
        } catch (InterruptedException e) {
        }
        String content = webDriver.getPageSource();
        Page page = new Page();
        page.setRawText(content);
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        return page;
    }

    @Override
    public void setThread(int threadNum) {
    }
}
