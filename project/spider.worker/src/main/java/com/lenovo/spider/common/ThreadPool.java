package com.lenovo.spider.common;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 统一线程池
 * @author：涂有
 * @date 2017年12月7日 下午4:11:49
 */
public class ThreadPool {

	public final static ScheduledExecutorService threadPool = 
			Executors.newScheduledThreadPool(Integer.parseInt(Config.get("threadPoolSize")));

	private ThreadPool(){}
	
	public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
            long initialDelay,
            long period,
            TimeUnit unit){
		return threadPool.scheduleAtFixedRate(command, initialDelay, period, unit);
	}
	
	public static <T> Future<T> submit(Callable<T> task){
		 return threadPool.submit(task);
	}
	
	public static Future<?> submit(Runnable task){
		return threadPool.submit(task);
	}
	
	public static void execute(Runnable command){
		threadPool.execute(command);
	}
}
