package com.lenovo.spider.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.selector.Xpath2Selector;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 18-1-30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XpathUtils {

    /**
     * 使用xpath获取指定的内容，默认使用WebMagic中的 xpath 1.0 版本，
     * 当无法解析时，使用saxon支持的xpath 2.0 语法进行解析
     *
     * @param node    父节点
     * @param locator xpath 定位信息
     * @return Optional<Selectable>
     */
    public static Optional<Selectable> safeXpath(Selectable node, String locator) {
        if (node == null || StringUtils.isEmpty(node.toString()) || StringUtils.isEmpty(locator)) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(node.xpath(locator));
        } catch (Exception e) {
            return Optional.of(new PlainText(new Xpath2Selector(locator).selectList(node.toString())));
        }
    }

    /**
     * 使用xpath获取一个指定的节点，暂时只支持 xpath 1.0 语法
     *
     * @param node    父节点
     * @param locator xpath 定位信息
     * @return Optional<Selectable>
     */
    public static Optional<Selectable> xpathNode(Selectable node, String locator) {
        if (node == null || StringUtils.isEmpty(node.toString()) || StringUtils.isEmpty(locator)) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(node.xpath(locator));
        } catch (Exception e) {
            return xpath2Node(node, locator);
        }
    }

    /**
     * 使用xpath获取一组指定的节点，如列表项节点， 暂时支持 xpath 1.0 语法
     *
     * @param node    父节点
     * @param locator xpath 定位信息
     * @return Optional<Selectable>
     */
    public static Optional<List<Selectable>> xpathNodes(Selectable node, String locator) {
        if (node == null || StringUtils.isEmpty(node.toString()) || StringUtils.isEmpty(locator)) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(node.xpath(locator).nodes());
        } catch (Exception e) {
            return xpath2Nodes(node, locator);
        }
    }

    /**
     * 使用xpath 2.0 获取一个指定的节点
     *
     * @param node    父节点
     * @param locator xpath 定位信息
     * @return Optional<Selectable>
     */
    public static Optional<Selectable> xpath2Node(Selectable node, String locator) {
        if (node == null || StringUtils.isEmpty(node.toString()) || StringUtils.isEmpty(locator)) {
            return Optional.empty();
        }
        try {
            Xpath2Selector selector = new Xpath2Selector(locator);
            return Optional.ofNullable(selector.select(node.toString()))
                    .map(Html::create)
                    .map(html -> html.xpath("/html/body/"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * 使用xpath 2.0 语法获取一组指定的节点
     *
     * @param node
     * @param locator
     * @return
     */
    public static Optional<List<Selectable>> xpath2Nodes(Selectable node, String locator) {
        if (node == null || StringUtils.isEmpty(node.toString()) || StringUtils.isEmpty(locator)) {
            return Optional.empty();
        }
        try {
            Xpath2Selector selector = new Xpath2Selector(locator);
            List<Selectable> results = selector.selectList(node.toString())
                    .stream()
                    .filter(StringUtils::isNotBlank)
                    .map(Jsoup::parseBodyFragment)
                    .map(Html::new)
                    .map(html -> html.xpath("/html/body/"))
                    .collect(Collectors.toList());
            return Optional.of(results);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
