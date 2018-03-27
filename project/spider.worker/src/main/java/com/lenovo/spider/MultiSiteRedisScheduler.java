package com.lenovo.spider;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.lenovo.spider.common.Config;
import com.lenovo.spider.util.LogUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 指定把爬虫队列key的后缀，这样每个站点可以分别指定单独的队列
 * 
 * @author：涂有
 * @date 2017年12月7日 下午3:59:50
 */
public class MultiSiteRedisScheduler extends RedisScheduler {
	
	protected Logger timeLogger = LogUtil.getLogger("time");

	protected static final String QUEUE_PREFIX = "queue_";
	protected static final String SET_PREFIX = "set_";
	protected static final String ITEM_PREFIX = "item_";

	/** 爬虫队列key的后缀 */
	protected String keySuffix;

	/** 使用统一的redis连接池 */
	public static final JedisPool pool = new JedisPool(Config.get("redisIP"),
			Integer.parseInt(Config.get("redisPort")));

	private MultiSiteRedisScheduler(JedisPool pool) {
		super(pool);
	}

	public MultiSiteRedisScheduler(String keySuffix) {
		this(pool);
		this.keySuffix = keySuffix;
	}
	
	@Override
	public synchronized Request poll(Task task) {
		long start = System.currentTimeMillis();
		Jedis jedis = pool.getResource();
        try {
        	String queueKey = getQueueKey(task);
            String url = jedis.lpop(queueKey);
            if (url == null) {
                return null;
            }
            String key = ITEM_PREFIX + task.getUUID();
            String field = DigestUtils.sha1Hex(url);
            byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
            if (bytes != null) {
                Request o = JSON.parseObject(new String(bytes), Request.class);
                return o;
            }
            Request request = new Request(url);
            long end = System.currentTimeMillis();
            timeLogger.info("redis poll：{}，from：{}，get url：{}", end - start, queueKey, request);
            return request;
        } finally {
           jedis.close();
        }
	}

	@Override
	protected String getSetKey(Task task) {
		return SET_PREFIX + keySuffix;
	}

	@Override
	protected String getQueueKey(Task task) {
		return QUEUE_PREFIX + keySuffix;
	}

	@Override
	protected String getItemKey(Task task) {
		return ITEM_PREFIX + keySuffix;
	}
}
