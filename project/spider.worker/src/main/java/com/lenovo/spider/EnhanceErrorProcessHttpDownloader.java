package com.lenovo.spider;

import com.lenovo.spider.util.LogUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;
import us.codecraft.webmagic.downloader.HttpUriRequestConverter;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强错误处理的HttpDownloader
 *
 * @author：涂有
 * @date 2017年12月8日 下午2:09:36
 */
public class EnhanceErrorProcessHttpDownloader extends HttpClientDownloader {

    private Logger httpDownloadLogger = LogUtil.getLogger("httpDownload");

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<String, CloseableHttpClient>();

    private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();

    private HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();

    private ProxyProvider proxyProvider;

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }

    private CloseableHttpClient getHttpClient(Site site) {
        if (site == null) {
            return httpClientGenerator.getClient(null);
        }
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                httpClient = httpClients.get(domain);
                if (httpClient == null) {
                    httpClient = httpClientGenerator.getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    @Override
    public Page download(Request request, Task task) {
        if (task == null || task.getSite() == null) {
            throw new NullPointerException("task or site can not be null");
        }
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = getHttpClient(task.getSite());
        Proxy proxy = proxyProvider != null ? proxyProvider.getProxy(task) : null;
        HttpClientRequestContext requestContext = httpUriRequestConverter.convert(request, task.getSite(), proxy);
        Page page = Page.fail();
        long start = System.currentTimeMillis();
        try {
            httpResponse = httpClient.execute(requestContext.getHttpUriRequest(), requestContext.getHttpClientContext());
            page = handleResponse(request, request.getCharset() != null ? request.getCharset() : task.getSite().getCharset(), httpResponse, task);
            long end = System.currentTimeMillis();
            httpDownloadLogger.info("下载网页,时间：{},statusCode：{},isSuccess：{},ip：{}:{}",
                    end - start, page.getStatusCode(), page.isDownloadSuccess(), proxy.getHost(), proxy.getPort());
            onSuccess(request);
            return page;
        } catch (IOException e) {
            long end = System.currentTimeMillis();
            httpDownloadLogger.info("下载网页,时间：{},ip：{}:{},exception",
                    end - start, proxy.getHost(), proxy.getPort(), e);
            onError(request);
            return page;
        } finally {
            if (httpResponse != null) {
                //ensure the connection is released back to pool
                EntityUtils.consumeQuietly(httpResponse.getEntity());
            }
            if (proxyProvider != null && proxy != null) {
                proxyProvider.returnProxy(proxy, page, task);
            }
        }
    }

    @Override
    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task)
            throws IOException {

        Page page = super.handleResponse(request, charset, httpResponse, task);

        //把set-cookie设置回去
        for (Header header : httpResponse.getAllHeaders()) {
            if ("set-cookie".equals(header.getName().toLowerCase())) {
                int index = header.getValue().indexOf(';');
                String keyValue = index >= 0 ? header.getValue().substring(0, index) :
                        header.getValue();
                String[] keyValues = keyValue.split("=");
                if (keyValues.length > 1) {
                    task.getSite().addCookie(keyValues[0], keyValues[1]);
                }
            }
        }
        return page;
    }
}
