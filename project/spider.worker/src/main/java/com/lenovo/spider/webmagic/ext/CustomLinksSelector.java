package com.lenovo.spider.webmagic.ext;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.selector.LinksSelector;

import java.util.ArrayList;
import java.util.List;

public class CustomLinksSelector extends LinksSelector {

	@Override
	public List<String> selectList(Element element) {
		// 抽取所有a标签的
		Elements elements = element.select("a");
		Elements frames = element.select("iframe");
		List<String> links = new ArrayList<String>(elements.size() + frames.size());
		for (Element element0 : elements) {
			if (!StringUtil.isBlank(element0.baseUri())) {
				if (!links.contains(element0.attr("abs:href")))
					links.add(element0.attr("abs:href"));
			} else {
				if (!links.contains(element0.attr("href")))
					links.add(element0.attr("href"));
			}
		}
		for (Element frame : frames) {
			String src = frame.attr("src");
			if ("about:blank".equals(src)) {
				continue;
			}

			if (StringUtils.startsWith(src, "http")) {
				if (!links.contains(src))
					links.add(src);
			} else {
				if (!links.contains(frame.attr("abs:src")))
					links.add(frame.attr("abs:src"));
			}
		}
		return links;
	}
}
