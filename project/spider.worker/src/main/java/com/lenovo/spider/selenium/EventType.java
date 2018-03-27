package com.lenovo.spider.selenium;

import org.openqa.selenium.WebDriver;

import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 18-1-25
 */
public enum EventType {
    /**
     * 点击
     */
    CLICK(1, DomEventHandlers::click),
    /**
     * 滚动
     */
    SCROLL(2, DomEventHandlers::scroll),
    /**
     * 执行脚本
     */
    EXEC_SCRIPT(3, DomEventHandlers::execScript);

    private int type;
    private BiConsumer<WebDriver, String> handle;

    EventType(int type, BiConsumer<WebDriver, String> handle) {
        this.type = type;
        this.handle = handle;
    }

    /**
     * 根据type序号获取对应的枚举类型
     *
     * @param type
     * @return
     */
    public static EventType of(Integer type) {
        if (type == null) {
            return null;
        }

        for (EventType eventType : values()) {
            if (eventType.type == type) {
                return eventType;
            }
        }
        return null;
    }

    /**
     * 执行事件
     *
     * @param webDriver
     * @param locator
     */
    public void invoke(WebDriver webDriver, String locator) {
        this.handle.accept(webDriver, locator);
    }
}
