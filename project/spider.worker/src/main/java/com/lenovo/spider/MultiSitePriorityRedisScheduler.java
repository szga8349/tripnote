package com.lenovo.spider;

import com.alibaba.fastjson.JSON;
import com.lenovo.spider.common.Config;
import com.lenovo.spider.common.Constant;
import com.lenovo.spider.util.LogUtil;
import com.lenovo.spider.util.UrlUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * 指定把爬虫队列key的后缀，这样每个站点可以分别指定单独的队列
 *
 * @author：涂有
 * @date 2017年12月7日 下午3:59:50
 */
public class MultiSitePriorityRedisScheduler extends RedisScheduler {

    protected Logger timeLogger = LogUtil.getLogger("time");

    protected static final String QUEUE_PREFIX = "queue_";
    protected static final String SET_PREFIX = "set_";
    protected static final String ITEM_PREFIX = "item_";

    /**
     * 爬虫队列key的后缀
     */
    protected String keySuffix;

    /**
     * 使用统一的redis连接池
     */
    public static final JedisPool pool = new JedisPool(Config.get("redisIP"),
            Integer.parseInt(Config.get("redisPort")));

    private MultiSitePriorityRedisScheduler(JedisPool pool) {
        super(pool);
    }

    public MultiSitePriorityRedisScheduler(String keySuffix) {
        this(pool);
        this.keySuffix = keySuffix;
    }

    /**
     * 使用redis优先级队列实现
     *
     * @param task
     * @return
     */
    @Override
    public synchronized Request poll(Task task) {
        long start = System.currentTimeMillis();
        return useJedis(jedis -> {
            String queueKey = getQueueKey(task);
            Tuple tuple = poll(jedis, queueKey);
            if (tuple == null) {
                return null;
            }
            String url = tuple.getElement();
            String key = ITEM_PREFIX + task.getUUID();
            String field = DigestUtils.sha1Hex(url);
            byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
            if (bytes != null) {
                Request o = JSON.parseObject(new String(bytes), Request.class);
                // 对url尝试转码
//                o.setUrl(UrlUtil.tryUnescapeParamQuery(o.getUrl()));
                o.setPriority((long) tuple.getScore());
                return o;
            }
            Request request = new Request(url).setPriority((long) tuple.getScore());
            long end = System.currentTimeMillis();
            timeLogger.info("redis poll：{}，from：{}，get url：{}", end - start, queueKey, request);
            return request;
        });
    }

    /**
     * 使用优先级队列
     *
     * @param request
     * @param task
     */
    @Override
    protected void pushWhenNoDuplicate(Request request, Task task) {
        useJedis(jedis -> {
            jedis.zadd(getQueueKey(task), request.getPriority(), request.getUrl());
            if (request.getExtras() != null) {
                String field = DigestUtils.shaHex(request.getUrl());
                String value = JSON.toJSONString(request);
                jedis.hset((ITEM_PREFIX + task.getUUID()), field, value);
            }
            return null;
        });
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


    @Override
    protected boolean noNeedToRemoveDuplicate(Request request) {
        return super.noNeedToRemoveDuplicate(request) ||
                (request.getExtra(Constant.DOM_EVENT) != null && request.getExtra(Constant.DOM_EVENT) instanceof DomEvent);
    }

    /**
     * 从队列中取一个元素
     *
     * @param key 键
     * @return 链接元素
     */
    private Tuple poll(Jedis jedis, String key) {
        do {
            jedis.watch(key);
            Set<Tuple> set = jedis.zrangeWithScores(key, 0, 0);
            if (set.isEmpty()) {
                jedis.unwatch();
                return null;
            }
            Tuple tuple = set.iterator().next();
            String val = tuple.getElement();
            try (Transaction t = jedis.multi()) {
                t.zrem(key, val);
                List<Object> results = t.exec();
                long num = results.size() == 0 ? 0 : (long) results.get(0);
                if (num > 0) {
                    return tuple;
                }
            } catch (IOException ignore) {
            }
        } while (true);
    }


    private <T> T useJedis(Function<Jedis, T> function) {
        try (Jedis jedis = pool.getResource()) {
            return function.apply(jedis);
        }
    }
}
