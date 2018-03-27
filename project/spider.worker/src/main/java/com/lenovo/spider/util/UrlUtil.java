package com.lenovo.spider.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description：处理url的工具类
 * @author：涂有
 * @date 2015年11月20日 上午1:27:23
 */
public class UrlUtil {

    public static final Pattern DOMAIN_PATTERN = Pattern.compile("^http://([0-9a-zA-Z\\.]+)(/.*|\\?.*)?$");

    /**
     * @param params
     * @return
     * @Description：从map转换为url参数字符串
     */
    public static String mapToQuery(Map<String, List<String>> params) {

        StringBuffer sb = new StringBuffer();
        if (params == null) {
            return sb.toString();
        }

        Set<String> keys = params.keySet();
        for (String key : keys) {
            List<String> values = params.get(key);
            if (values != null) {
                for (String value : values) {
                    sb.append(key);
                    sb.append("=");
                    sb.append(value);
                    sb.append("&");
                }
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }

    /**
     * @param url
     * @return
     * @Description：将url参数字符串转换为map
     */
    public static Map<String, List<String>> getParamMap(String url) {

        Map<String, List<String>> map = new TreeMap<>();

        if (StringUtils.isBlank(url)) {
            return map;
        }

        StringBuffer sb = new StringBuffer(url);
        int index = sb.indexOf("#");
        if (index >= 0) {
            sb.deleteCharAt(index);
            url = sb.toString();
        }

        String query = null;
        if (url.indexOf("?") >= 0) {
            String[] array = url.split("\\?");
            query = array[1];
        } else {
            query = url;
        }

        String[] param = query.split("&");
        for (String p : param) {
            String[] keyValue = p.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];

                List<String> searchs = map.get(key);
                if (searchs == null) {
                    searchs = new ArrayList<>();
                    map.put(key, searchs);
                }
                searchs.add(value);
            }
        }
        return map;
    }

    /**
     * 为一个url后面拼接一个参数
     *
     * @param url
     * @param name    参数名称
     * @param value   参数值
     * @param replace 如果发现重复的是否替换
     * @return
     */
    public static String addParam(String url, String name, String value, boolean replace) {

        Map<String, List<String>> params = getParamMap(url);
        List<String> searchs = params.get(name);
        if (searchs == null || replace) {
            searchs = new ArrayList<>();
            params.put(name, searchs);
        }
        searchs.add(value);

        String prefix = "";
        if (url.indexOf("?") >= 0) {
            String[] array = url.split("\\?");
            prefix = array[0];
        } else if (url.indexOf("&") < 0) {
            prefix = url;
        }
        return prefix + "?" + mapToQuery(params);
    }

    /**
     * 从一个url中取出域名
     *
     * @param url 指定的url
     * @return
     */
    public static String getDomain(String url) {
        Matcher matcher = DOMAIN_PATTERN.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * 根据给定的ｕｒｌ获取域名主体
     * 例：　ｗｗｗ．ｂａｉｄｕ．ｃｏｍ　－》　ｂａｉｄｕ
     *
     * @param url
     * @return
     */
    public static String getDomainSubject(String url) {
        String domain = StringUtils.startsWith(url, "http://") ? getDomain(url) : url;
        return Optional.ofNullable(domain)
                .map(s -> s.replace("www.", "").replace(".com", "")).get();
    }

    /**
     * 从一个url中取出域名的MD5值
     *
     * @param url
     * @return
     */
    public static String getMd5Domain(String url) {
        return DigestUtils.md5Hex(getDomain(url));
    }

    /**
     * 简单判断给定的url是否为ｕｒｌ参数部分
     * 判断依据：　是否以ｈｔｔｐ：／／开头
     *
     * @param url
     * @return
     */
    public static boolean isUrlParameter(String url) {
        return !StringUtils.startsWith(url, "http://");
    }

    /**
     * 将参数字串追加到ｕｒｌ之后
     *
     * @param url
     * @param parameterStr
     * @return
     */
    public static String concatParameter(String url, String parameterStr) {
        if (StringUtils.containsAny(url, '?', '&')) {
            return url + "&" + parameterStr;
        } else {
            return url + "?" + parameterStr;
        }
    }

    public static void main(String[] args) {
        System.out.println(getDomainSubject("http://www.baidu.com"));
        System.out.println(getDomainSubject("http://www.baidu.com/sss"));
        System.out.println(getDomainSubject("http://www.baidu.com?wd="));
        System.out.println(getDomainSubject("http://www.baidu.com/sss?wd="));

    }

}
