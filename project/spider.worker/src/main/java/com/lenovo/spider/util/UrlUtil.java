package com.lenovo.spider.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
                .map(s -> s.replace("www.", ""))
                .map(s -> s.replace(".com", ""))
                .map(s -> s.replace(".cn", ""))
                .get();
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
        return !StringUtils.startsWith(url, "http://") && !StringUtils.startsWith(url, "https://");
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

    private final static String[] hex = {
            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F",
            "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F",
            "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F",
            "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F",
            "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F",
            "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F",
            "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F",
            "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
            "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF",
            "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF",
            "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF",
            "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF",
            "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF",
            "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"
    };
    private final static byte[] val = {
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
            0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F
    };

    public static String escape(String s) {
        StringBuilder sbuf = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i);
            if (ch == ' ') {                        // space : map to '+'
                sbuf.append('+');
            } else if ('A' <= ch && ch <= 'Z') {    // 'A'..'Z' : as it was
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {    // 'a'..'z' : as it was
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {    // '0'..'9' : as it was
                sbuf.append((char) ch);
            } else if (ch == '-' || ch == '_'       // unreserved : as it was
                    || ch == '.' || ch == '!'
                    || ch == '~' || ch == '*'
                    || ch == '/' || ch == '('
                    || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch <= 0x007F) {              // other ASCII : map to %XX
                sbuf.append('%');
                sbuf.append(hex[ch]);
            } else {                                // unicode : map to %uXXXX
                sbuf.append('%');
                sbuf.append('u');
                sbuf.append(hex[(ch >>> 8)]);
                sbuf.append(hex[(0x00FF & ch)]);
            }
        }
        return sbuf.toString();
    }

    public static String unescape(String s) {
        StringBuilder sbuf = new StringBuilder();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int ch = s.charAt(i);
            if (ch == '+') {                        // + : map to ' '
                sbuf.append(' ');
            } else if ('A' <= ch && ch <= 'Z') {    // 'A'..'Z' : as it was
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') {    // 'a'..'z' : as it was
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') {    // '0'..'9' : as it was
                sbuf.append((char) ch);
            } else if (ch == '-' || ch == '_'       // unreserved : as it was
                    || ch == '.' || ch == '!'
                    || ch == '~' || ch == '*'
                    || ch == '/' || ch == '('
                    || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch == '%') {
                int cint = 0;
                if ('u' != s.charAt(i + 1)) {         // %XX : map to ascii(XX)
                    cint = (cint << 4) | val[s.charAt(i + 1)];
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    i += 2;
                } else {                            // %uXXXX : map to unicode(XXXX)
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    cint = (cint << 4) | val[s.charAt(i + 3)];
                    cint = (cint << 4) | val[s.charAt(i + 4)];
                    cint = (cint << 4) | val[s.charAt(i + 5)];
                    i += 5;
                }
                sbuf.append((char) cint);
            }
            i++;
        }
        return sbuf.toString();
    }

    /**
     * 尝试对url进行url转码
     *
     * @param url
     * @return
     */
    public static String tryUnescape(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            String unescape = unescape(url);
            if (StringUtils.isNotEmpty(unescape)) {
                return unescape;
            } else {
                return url;
            }
        }
    }

    /**
     * 尝试对请求参数部分进行转义
     *
     * @param url
     * @return
     */
    public static String tryUnescapeParamQuery(String url) {
        if (StrUtil.isEmpty(url)) {
            return url;
        }
        Map<String, List<String>> paramMap = getParamMap(url);
        if (CollUtil.isEmpty(paramMap)) {
            return url;
        }
        paramMap = Maps.transformValues(paramMap, strings -> strings != null ? strings.stream().map(UrlUtil::tryUnescape).collect(Collectors.toList()) : null);
        String query = mapToQuery(paramMap);
        return url.replaceAll("\\?.*", "").concat("?").concat(query);
    }

}
