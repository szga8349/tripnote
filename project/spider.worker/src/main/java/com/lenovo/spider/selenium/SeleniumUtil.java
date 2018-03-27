package com.lenovo.spider.selenium;

import com.google.common.base.Function;
import com.lenovo.exception.LocateException;
import com.lenovo.spider.common.Config;
import com.lenovo.spider.vo.AuthInfo;
import com.lenovo.spider.vo.AuthLocator;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;
import java.util.concurrent.Semaphore;

@Slf4j
public class SeleniumUtil {

    public final static DesiredCapabilities phantomjsDesiredCapabilities = DesiredCapabilities.phantomjs();
    private static final int timeout = 5;
    private static final int interval = 1;
    private static final int webDriverAmount = Integer.parseInt(Config.get("webDriverAmount"));

    /**
     * 用于控制webDriver实例个数
     */
    private static Semaphore semaphore = new Semaphore(webDriverAmount, true);

    static {
        String driverType = Config.get("webDriverType");
        if ("chrome".equals(driverType)) {
            System.getProperties().setProperty("webdriver.chrome.driver", Config.get("webDriverPath"));
        } else {

            System.getProperties().setProperty("phantomjs.binary.path", Config.get("webDriverPath"));

            //设置参数
            phantomjsDesiredCapabilities.setCapability("phantomjs.page.settings.userAgent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
            phantomjsDesiredCapabilities.setCapability("phantomjs.page.customHeaders.User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
            //ssl证书支持
            phantomjsDesiredCapabilities.setCapability("acceptSslCerts", true);
            //截屏支持
            phantomjsDesiredCapabilities.setCapability("takesScreenshot", true);
            //css搜索支持
            phantomjsDesiredCapabilities.setCapability("cssSelectorsEnabled", true);
            //js支持
            phantomjsDesiredCapabilities.setJavascriptEnabled(true);
        }
    }

    public static boolean checkCanNewWebDriver() {
        return semaphore.availablePermits() > 0;
    }

    public static WebDriver newWebDriver(String proxyIpAndPort) {
        return "chrome".equals(Config.get("webDriverType")) ?
                SeleniumUtil.newChromeDriver(proxyIpAndPort) :
                SeleniumUtil.newPhantomJSDriver(proxyIpAndPort);
    }

    public static WebDriver newWebDriver() {
        return "chrome".equals(Config.get("webDriverType")) ?
                SeleniumUtil.newChromeDriver() :
                SeleniumUtil.newPhantomJSDriver();
    }

    public static PhantomJSDriver newPhantomJSDriver() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        }
        PhantomJSDriver driver = new PhantomJSDriver(phantomjsDesiredCapabilities);
        return driver;
    }

    public static PhantomJSDriver newPhantomJSDriver(String proxyIpAndPort) {
        DesiredCapabilities phantomjsDesiredCapabilities = DesiredCapabilities.phantomjs();
        //设置参数
        phantomjsDesiredCapabilities.setCapability("phantomjs.page.settings.userAgent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
        phantomjsDesiredCapabilities.setCapability("phantomjs.page.customHeaders.User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
        //ssl证书支持
        phantomjsDesiredCapabilities.setCapability("acceptSslCerts", true);
        //截屏支持
        phantomjsDesiredCapabilities.setCapability("takesScreenshot", true);
        //css搜索支持
        phantomjsDesiredCapabilities.setCapability("cssSelectorsEnabled", true);
        //js支持
        phantomjsDesiredCapabilities.setJavascriptEnabled(true);
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
        phantomjsDesiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        }
        PhantomJSDriver driver = new PhantomJSDriver(phantomjsDesiredCapabilities);
        return driver;
    }

    public static ChromeDriver newChromeDriver() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        }
        ;
        ChromeDriver driver = new ChromeDriver();
        return driver;
    }

    public static ChromeDriver newChromeDriver(String proxyIpAndPort) {
        DesiredCapabilities phantomjsDesiredCapabilities = DesiredCapabilities.chrome();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
        phantomjsDesiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        }
        ChromeDriver driver = new ChromeDriver(phantomjsDesiredCapabilities);
        return driver;
    }

    public static void destoryWebDriver(WebDriver driver) {
        driver.quit();
        semaphore.release();
    }

    /**
     * 查找并等待元素加载
     *
     * @param driver
     * @param by
     */
    public static Optional<WebElement> findBy(WebDriver driver, By by) {
        if (driver == null || by == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(new WebDriverWait(driver, timeout, interval)
                    .until((Function<WebDriver, WebElement>) d -> d.findElement(by)));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * 执行登录操作
     *
     * @param driver
     * @param account 用户名密码
     * @param locator 填写的位置信息
     * @throws LocateException
     * @author：涂有
     * @date 2017年12月13日 下午3:24:10
     */
    public static void login(WebDriver driver, AuthInfo account, AuthLocator locator) throws LocateException {
        if (locator.getUsernameLocator() == null || locator.getPasswordLocator() == null
                || locator.getSubmitButtonLocator() == null) {
            throw new LocateException();
        }

        driver.get(locator.getLoginUrl());
        WebElement element = null;
        if (locator.getUsernameLocatorType() == 1) {
            element = driver.findElement(By.cssSelector(locator.getUsernameLocator()));
        } else {
            element = driver.findElement(By.xpath(locator.getUsernameLocator()));
        }
        if (element == null) {
            throw new LocateException();
        }
        element.sendKeys(account.getUsername());

        if (locator.getPasswordLocatorType() == 1) {
            element = driver.findElement(By.cssSelector(locator.getPasswordLocator()));
        } else {
            element = driver.findElement(By.xpath(locator.getPasswordLocator()));
        }
        if (element == null) {
            throw new LocateException();
        }
        element.sendKeys(account.getPassword());

        if (locator.getSubmitButtonLocatorType() == 1) {
            element = driver.findElement(By.cssSelector(locator.getSubmitButtonLocator()));
        } else {
            element = driver.findElement(By.xpath(locator.getSubmitButtonLocator()));
        }
        if (element == null) {
            throw new LocateException();
        }
        element.click();
    }

}
