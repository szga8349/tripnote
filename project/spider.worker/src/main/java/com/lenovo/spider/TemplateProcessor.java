package com.lenovo.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.exception.NetException;
import com.lenovo.spider.common.Constant;
import com.lenovo.spider.interfaces.ConfigInterface;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.vo.UrlInfo;
import com.lenovo.spider.vo.UrlTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.lenovo.spider.util.UrlUtil.*;

/**
 * 页面处理器
 *
 * @author：涂有
 * @date 2017年12月8日 下午2:14:31
 */
public class TemplateProcessor implements PageProcessor {

    private static final Logger urlLog = LogUtil.getLogger("url");

    protected RichSpider spider;
    protected ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 后台配置的url解析方式
     */
    protected List<UrlInfo> urlRegexs;
    protected Map<Long, List<UrlTemplate>> templates = new HashMap<>();
    protected Site site;

    public TemplateProcessor(Site site, List<UrlInfo> urlRegexs) throws NetException {
        this.site = site;
        this.urlRegexs = urlRegexs;
        for (UrlInfo url : urlRegexs) {
            if (url.getAnalyzePage()) {
                templates.put(url.getId(), ConfigInterface.selectPageTemplate(url.getId()));
            }
        }
    }

    /**
     * 按照模板解析页面拿到数据
     */
    private JSON analyzePage(Page page, List<UrlTemplate> templates, boolean array) {
        JSONObject json = new JSONObject();
        Map<String, List<String>> map = new HashMap<>();
        for (UrlTemplate template : templates) {
            if (StringUtils.isEmpty(template.getLocator())) {
                continue;
            }
            if (template.getType() == 1) { //string类型
                Selectable value = null;
                if (template.getLocatorType() == 1) { //css
                    if (array) {
                        map.put(template.getName(), page.getHtml().css(template.getLocator()).all());
                    } else {
                        value = page.getHtml().css(template.getLocator());
                    }
                } else { //xpath
                    if (array) {
                        map.put(template.getName(), page.getHtml().xpath(template.getLocator()).all());
                    } else {
                        value = page.getHtml().xpath(template.getLocator());
                    }
                }
                if (value == null || StringUtils.isBlank(value.toString())) { //如果不能为空，则直接返回
                    if (template.getImportantLevel() == 2) {
                        return null;
                    }
                } else {
                    json.put(template.getName(), value.toString().trim());
                }
            } else if (template.getChildren() != null) {
                if (template.getType() == 2) { //对象类型
                    json.put(template.getName(), analyzePage(page, template.getChildren(), false));
                } else { //数组类型
                    json.put(template.getName(), analyzePage(page, template.getChildren(), true));
                }
            }
        }

        if (array) {
            Set<String> keySet = map.keySet();
            JSONArray jsonArray = new JSONArray();
            for (String key : keySet) {
                List<String> list = map.get(key);
                for (int i = 0; i < list.size(); i++) {
                    jsonArray.add(new JSONObject());
                }
                break;
            }
            for (String key : keySet) {
                List<String> list = map.get(key);
                for (int i = 0; i < list.size(); i++) {
                    jsonArray.getJSONObject(i).put(key, list.get(i));
                }
            }
            return jsonArray;
        } else {
            return json;
        }
    }

    public void setSpider(RichSpider spider) {
        this.spider = spider;
    }

    @Override
    public void process(Page page) {
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
        }

        //默认不处理
        page.setSkip(true);

        page.putField(Constant.site, getDomainSubject(site.getDomain()));
        page.putField(Constant.urlKey, page.getUrl().toString());
        for (UrlInfo url : urlRegexs) {
            if (page.getUrl().toString().matches(url.getUrl())) {
                if (url.getSavePage() && StringUtils.isNotBlank(page.getHtml().toString())) {//保存网页
                    page.putField(Constant.originPageKey, page.getHtml().toString());
                    page.setSkip(false);
                }
                if (url.getAnalyzePage()) {//解析网页
                    JSON data = analyzePage(page, templates.get(url.getId()), false);
                    if (data != null) {
                        if (data instanceof JSONArray) {
                            JSONArray array = (JSONArray) data;
                            if (array.size() > 0) {
                                //过滤空对象
                                for (int i = array.size() - 1; i >= 0; i--) {
                                    if ("{}".equals(array.getJSONObject(i).toJSONString())) {
                                        array.remove(i);
                                    }
                                }
                                if (array.size() > 0) {
                                    page.putField(Constant.analyzePageKey, data);
                                    page.setSkip(false);
                                }
                            }
                        } else {
                            JSONObject obj = (JSONObject) data;
                            if (!"{}".equals(obj.toJSONString())) { //过滤空对象
                                page.putField(Constant.analyzePageKey, data);
                                page.setSkip(false);
                            }
                        }
                    }
                }
            }
        }

        // 部分三：从页面发现后续的url地址来抓取
        for (UrlInfo u : urlRegexs) {
            List<String> nextUrl = page.getHtml().links().regex(u.getUrl()).all();
            // 根据是否有ｈｔｔｐ：／／开头修正
            nextUrl = checkAndFixUrls(page.getRequest().getUrl().replaceAll(u.getUrl(), ""), nextUrl);
            nextUrl.forEach(urlLog::info);
            page.addTargetRequests(nextUrl);
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


    public static void main(String[] args) {
        String url = "http://xueqiu.com/hq#exchange=CN&firstName=1&secondName=1_0&page=1&page=5&page=4&&page=183";

        System.out.println(url.replaceAll("\\??(&+?page=\\d+)", ""));
    }
}