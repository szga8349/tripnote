package com.lenovo.spider.util;

import us.codecraft.webmagic.Page;

import java.util.Optional;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 18-1-11
 */
public class PageUtils {

    private PageUtils() {
    }

    public static String trimHtml(Page page) {
        return Optional.ofNullable(page)
                .map(Page::getHtml)
                .map(html -> html.get().replace("\n", "").replace(" ", ""))
                .get();
    }


}
