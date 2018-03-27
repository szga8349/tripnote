package com.lenovo.spider.webmagic.ext;

import com.alibaba.fastjson.JSONObject;
import com.lenovo.spider.vo.UrlTemplate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.LinksSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.lenovo.spider.util.XpathUtils.*;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 18-2-2
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemExtractor {

    public static final int STRING = 1;
    public static final int OBJECT = 2;
    public static final int ARRAY = 3;

    /**
     * 根据链接模版集合提取结果
     *
     * @param node
     * @param templateList
     * @param item
     */
    public static JSONObject extract(Selectable node, List<UrlTemplate> templateList, JSONObject item) {
        for (UrlTemplate urlTemplate : templateList) {
            int type = Optional.ofNullable(urlTemplate.getType()).orElse(0);
            switch (type) {
                case STRING:
                    safeXpath(node, urlTemplate.getLocator())
                            .map(Selectable::get)
                            .ifPresent(str -> item.put(urlTemplate.getName(), str));
                    break;
                case OBJECT:
                    extractObjectFromChildren(node, urlTemplate, item);
                    break;
                case ARRAY:
                    extractArrayFromChildren(node, urlTemplate, item);
                    break;
                default:
                    break;
            }
        }
        return item;
    }

    /**
     * 根据给定的链接模版提取对象
     *
     * @param node
     * @param urlTemplate
     * @param item
     */
    public static void extractObjectFromChildren(Selectable node, UrlTemplate urlTemplate, JSONObject item) {
        if (CollectionUtils.isNotEmpty(urlTemplate.getChildren())) {
            JSONObject obj = new JSONObject();
            if (StringUtils.isEmpty(urlTemplate.getLocator())) {
                // 没有定义父节点位置的
                extract(node, urlTemplate.getChildren(), obj);
            } else {
                // 定义了父节点位置的
                xpathNode(node, urlTemplate.getLocator())
                        .ifPresent(parent -> extract(parent, urlTemplate.getChildren(), obj));
            }
            if (MapUtils.isNotEmpty(obj)) {
                // 不为空的写入页面结果中
                item.put(urlTemplate.getName(), obj);
            }
        }
    }

    /**
     * 根据给定的链接模版提取数组
     *
     * @param node
     * @param urlTemplate
     * @param item
     */
    public static void extractArrayFromChildren(Selectable node, UrlTemplate urlTemplate, JSONObject item) {
        if (CollectionUtils.isNotEmpty(urlTemplate.getChildren())) {
            // 对象数组
            xpathNodes(node, urlTemplate.getLocator())
                    .ifPresent(nodes -> {
                        List<JSONObject> arr = nodes.stream()
                                .map(subNode -> extract(subNode, urlTemplate.getChildren(), new JSONObject()))
                                .filter(MapUtils::isNotEmpty).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(arr)) {
                            item.put(urlTemplate.getName(), arr);
                        }
                    });
        } else {
            // 简单文本数组
            safeXpath(node, urlTemplate.getLocator())
                    .map(Selectable::all)
                    .filter(CollectionUtils::isNotEmpty)
                    .ifPresent(strs -> item.put(urlTemplate.getName(), strs));
        }
    }

    /**
     * 从指定节点中获取所有的link信息，包括[a, frame]
     *
     * @param node
     * @return
     */
    public static Selectable extractLinks(Selectable node) {
        return node.selectList(new CustomLinksSelector());
    }
}
