package com.lenovo.spider;

import com.alibaba.fastjson.JSON;
import com.lenovo.exception.NetException;
import com.lenovo.spider.common.Config;
import com.lenovo.spider.common.Constant;
import com.lenovo.spider.common.IPCount;
import com.lenovo.spider.common.ThreadPool;
import com.lenovo.spider.interfaces.ConfigInterface;
import com.lenovo.spider.interfaces.HBaseInterface;
import com.lenovo.spider.util.DateUtil;
import com.lenovo.spider.util.LogUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.Logger;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 带数据缓冲的Pipline
 *
 * @author：涂有
 * @date 2017年12月8日 下午2:15:23
 */
public class CachePipline implements Pipeline {

    protected static AtomicInteger uploadCount;
    protected final static String uploadCountPrefixName = "data/uploadCount_";
    protected final static String uploadCountSuffixName = ".txt";
    protected static final String saveFileName = "data/dataQueue.bin";
    protected static Logger dataLogger = LogUtil.getLogger("data");
    protected static Logger exceptionLogger = LogUtil.getLogger("interfaceException");
    protected static Logger statusLogger = LogUtil.getLogger("validSave");

    /**
     * 数据先缓存到这个队列里面，如果上传失败则依旧放回此队列
     * 这样当服务器不可用时，此队列做个缓冲作用，避免数据丢失
     */
    protected static LinkedBlockingQueue<Object[]> dataQueue = new LinkedBlockingQueue<>();

    static {
        restore();
        startUpload();
        ThreadPool.scheduleAtFixedRate(() -> {
            store();
        }, 10, 10, TimeUnit.SECONDS);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        String site = resultItems.get(Constant.site);
        String url = resultItems.get(Constant.urlKey);
        String text = resultItems.get(Constant.originPageKey);
        if (StringUtils.isNotBlank(text)) {
            dataQueue.offer(new Object[]{site, url, text});
        }

        JSON json = resultItems.get(Constant.analyzePageKey);
        if (json != null) {
            dataQueue.offer(new Object[]{site, url, json});
        }
    }

    public static void startUpload() {
        new Thread("uploadToHbase") {
            public void run() {
                while (true) {
                    try {
                        final Object[] data = dataQueue.take();
                        if (data != null && data[2] != null) {
                            try {
                                if (data[2] instanceof JSON) {
                                    HBaseInterface.uploadAnalyzeData((String) data[0], (String) data[1], ((JSON) data[2]).toJSONString());
                                    dataLogger.info("上传成功：{}", data[2].toString());
                                    uploadCount.incrementAndGet();
                                } else {
                                    HBaseInterface.uploadRawPage((String) data[0], (String) data[1], (String) data[2]);
                                }
                            } catch (IOException e) {
                                dataLogger.info("上传失败：{}", data[2].toString());
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e1) {
                                }
                                dataQueue.offer(data);
                            }
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }

            ;
        }.start();
    }

    @SuppressWarnings("unchecked")
    public static void restore() {
        File file = new File(saveFileName);
        if (file.exists() && file.length() > 1) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                dataQueue = (LinkedBlockingQueue<Object[]>) in.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        File uploadCountFile = new File(uploadCountPrefixName + DateUtil.formatToShort(new Date()) + uploadCountSuffixName);
        if (!uploadCountFile.exists()) {
            uploadCount = new AtomicInteger();
        } else {
            try {
                uploadCount = new AtomicInteger(NumberUtils.toInt(FileUtils.readFileToString(uploadCountFile)));
            } catch (IOException e) {
                uploadCount = new AtomicInteger();
            }
        }
    }

    public static void store() {

        File file = new File(uploadCountPrefixName + DateUtil.formatToShort(new Date()) + uploadCountSuffixName);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                final int ipCount = IPCount.getInstance().getIpCount();
                final int upload = uploadCount.get();
                ThreadPool.execute(() -> {
                    while (true) {
                        try {
                            ConfigInterface.uploadCrawlerInfo(Config.get("spiderName"), ipCount, upload);
                            break;
                        } catch (NetException e) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e1) {
                            }
                        }
                    }
                });
                uploadCount.set(0); //重置今日ip统计数
            }
            FileUtils.writeStringToFile(file, String.valueOf(uploadCount.get()));
        } catch (IOException e) {
            exceptionLogger.error("保存上传统计异常", e);
        }
        statusLogger.debug("保存统计：{}，缓冲队列长度：{}", uploadCount.get(), dataQueue.size());

        IPCount.getInstance().store();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFileName, false))) {
            out.writeObject(dataQueue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
