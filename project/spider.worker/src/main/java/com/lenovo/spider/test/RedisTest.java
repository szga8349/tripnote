package com.lenovo.spider.test;

import com.lenovo.spider.MultiSiteRedisScheduler;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {

	public static void main(String[] args) {
		
		JedisPool pool = MultiSiteRedisScheduler.pool;
		Jedis jedis = pool.getResource();
		
		String name = "queue_www.qichacha.com";
		
		String lpop = jedis.lpop(name);
		System.out.println(lpop);
		jedis.rpush(name, lpop);
		jedis.close();
		pool.close();
	}
}
