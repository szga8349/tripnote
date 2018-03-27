package com.lenovo.spider.selenium;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.lenovo.spider.selenium.SeleniumUtil.findBy;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 18-1-26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DomEventHandlers {

    /**
     * 对指定对象执行点击事件
     *
     * @param driver
     * @param locator
     */
    public static void click(WebDriver driver, String locator) {
        findBy(driver, By.xpath(locator)).ifPresent(WebElement::click);
    }

    /**
     * @param driver
     * @param locator
     */
    public static void scroll(WebDriver driver, String locator) {
        findBy(driver, By.xpath(locator)).ifPresent(target ->
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", target));

    }

    /**
     * 执行脚本
     * @param driver
     * @param script
     */
    public static void execScript(WebDriver driver, String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }
}
