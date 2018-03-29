package com.lenovo.spider;

import static com.lenovo.spider.util.UrlUtil.concatParameter;
import static com.lenovo.spider.util.UrlUtil.getDomainSubject;
import static com.lenovo.spider.util.UrlUtil.isUrlParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lenovo.exception.NetException;
import com.lenovo.spider.common.Constant;
import com.lenovo.spider.selenium.EventType;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.vo.IpInfo;
import com.lenovo.spider.vo.SiteInfo;
import com.lenovo.spider.vo.UrlInfo;
import com.lenovo.spider.vo.UrlTemplate;
import com.lenovo.spider.webmagic.ext.ItemExtractor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 页面处理器
 *
 * @author：涂有
 * @date 2017年12月8日 下午2:14:31
 */
public class TemplateProcessor implements PageProcessor {

    private static final Logger urlLog = LogUtil.getLogger("url");
    protected RichSpider spider;
    protected SiteInfo siteInfo;
    protected ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 后台配置的url解析方式
     */
    protected List<UrlInfo> urlRegexs;
    protected Map<Long, List<UrlTemplate>> templates = new HashMap<>();
    protected Site site;

    public TemplateProcessor(Site site, List<UrlInfo> urlRegexs, SiteInfo siteInfo) throws NetException {
        this.site = site;
        this.siteInfo = siteInfo;
        this.urlRegexs = urlRegexs;
        for (UrlInfo url : urlRegexs) {
            if (url.getAnalyzePage()) {
                templates.put(url.getId(), getUrlTemplate(url.getId()));
            }
        }
    }
    private List<UrlTemplate> getUrlTemplate(Long id){
    	Map<Long,List<UrlTemplate>> template = new HashMap<>();
    	{   //任游详情解析模板
    		List<UrlTemplate> l = new ArrayList<UrlTemplate>();
    		UrlTemplate urlT = new UrlTemplate();
    		urlT.setType(1);
    		urlT.setLocator("//div[@class='product-head']/h1/text()");
    		urlT.setName("title");
    		l.add(urlT);
    		urlT = new UrlTemplate();
    		urlT.setType(1);
    		urlT.setLocator("//div[@class='product-head']/p[@class='en']/text()");
    		urlT.setName("entitle");
    		l.add(urlT);
    		urlT = new UrlTemplate();
    		urlT.setType(1);
    		urlT.setLocator("//div[@class='p-attr']/span[contains(text(), '城市')]/i/text()");
    		urlT.setName("city");
    		l.add(urlT);
    		template.put(3l, l);
    		
    	}
    	{   //猫途鹰详情解析模板
    		List<UrlTemplate> l = new ArrayList<UrlTemplate>();
    		UrlTemplate urlT = new UrlTemplate();
    		urlT.setType(1);
    		urlT.setLocator("//div[@class='product-head']/h1/text()");
    		urlT.setName("title");
    		l.add(urlT);
    		urlT = new UrlTemplate();
    		urlT.setType(1);
    		urlT.setLocator("//div[@class='product-head']/p[@class='en']/text()");
    		urlT.setName("entitle");
    		l.add(urlT);
    		urlT = new UrlTemplate();
    		urlT.setType(1);
    		urlT.setLocator("//div[@class='p-attr']/span[contains(text(), '城市')]/i/text()");
    		urlT.setName("city");
    		l.add(urlT);
    		template.put(4l, l);
    		
    	}
    	return template.get(id);
    }

    public void setSpider(RichSpider spider) {
        this.spider = spider;
    }

    public Long getSiteId() {
        return Optional.ofNullable(spider.getIp())
                .map(IpInfo::getSite)
                .map(SiteInfo::getId)
                .orElse(0L);
    }

    @Override
    public void process(Page page) {
        /*try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
        }*/

        //默认不处理
        page.setSkip(true);

        page.putField(Constant.site, getDomainSubject(site.getDomain()) + "_" + getSiteId());
        page.putField(Constant.urlKey, page.getUrl().toString());
        for (UrlInfo url : urlRegexs) {
            if (page.getUrl().toString().matches(url.getUrl())) {
                if (url.getSavePage() && StringUtils.isNotBlank(page.getHtml().toString())) {
                    // 保存网页
                    page.putField(Constant.originPageKey, page.getHtml().toString());
                    page.setSkip(false);
                }
                if (url.getAnalyzePage()) {
                    // 解析网页
                	System.out.println(page.getHtml());
                    JSONObject data = ItemExtractor.extract(page.getHtml(), templates.get(url.getId()), new JSONObject());
                    if (MapUtils.isNotEmpty(data)) {
                        page.putField(Constant.analyzePageKey, data);
                        page.setSkip(false);
                    }
                }
                if (url.getHasNextPage()) {
                    // 含有下一页元素，需要使用selenium进行点击的需要发送带事件
                    addDomEventRequest(page, url.getNextPageType(), url.getNextPageLocator());
                }
            }
        }

        Selectable allLinks = ItemExtractor.extractLinks(page.getHtml());
        // 部分三：从页面发现后续的url地址来抓取
        for (UrlInfo u : urlRegexs) {
            // 页面直接获取的链接
            List<String> nextUrl = allLinks.regex(u.getUrl()).all();
            // 根据是否有ｈｔｔｐ：／／开头修正
//            nextUrl = checkAndFixUrls(page.getRequest().getUrl().replaceAll(u.getUrl(), ""), nextUrl);
            nextUrl.forEach(urlLog::info);
            page.addTargetRequests(nextUrl, Optional.ofNullable(u.getSort()).map(Double::longValue).orElse((long) Integer.MAX_VALUE));
        }
    }

    protected List<String> checkAndFixUrls(String curUrl, List<String> nextUrl) {
        return nextUrl.stream().map(url ->
                isUrlParameter(url) ? concatParameter(curUrl, url) : url
        ).collect(Collectors.toList());
    }

    @Override
    public Site getSite() {
        return site;
    }

    private void addDomEventRequest(Page page, Integer nextPageType, String eventTargetLocator) {
        EventType eventType = EventType.of(nextPageType);
        if (eventType == null) {
            return;
        }
        Request request = new Request(page.getUrl().toString());
        request.putExtra(Constant.DOM_EVENT, new DomEvent(eventType, eventTargetLocator));
        if (eventType == EventType.SCROLL || this.siteInfo.getId() == 56) {
            // 如果是滚动到下一页的，page的url中不会有当前页的状态，所以从request的附加参数中提取
            request.putExtra(Constant.EXECUTE_COUNT,
                    MapUtils.getIntValue(page.getRequest().getExtras(), Constant.EXECUTE_COUNT, 1));
        }
        page.addTargetRequest(request);
    }
}