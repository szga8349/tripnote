package com.lenovo.spider.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.Logger;

import com.lenovo.spider.util.DateUtil;
import com.lenovo.spider.util.LogUtil;

/**
 * IP统计
 * @author：涂有
 * @date 2017年10月30日 下午4:30:58
 */
public class IPCount {
	
	private Logger logger = LogUtil.getLogger("ipFetch");

	private static IPCount pool;
	
	private final String ipCountPrefixName = "data/ipCount_";
	private final String ipCountSuffixName = ".txt";
	private AtomicInteger ipCount;
	
	public static final int ipMaxAmountForDay = Integer.parseInt(Config.get("ipMaxAmountForDay"));
	
	protected IPCount(){
		
		restore();
	}
	
	public static IPCount getInstance(){
		if(pool == null){
			synchronized(IPCount.class){
				if(pool == null){
					pool = new IPCount();
				}
			}
		}
		return pool;
	}
	
	/**
	 * 恢复持久化的ip
	 * @author：涂有
	 * @date 2017年11月10日 下午4:19:59
	 */
	public void restore(){
		File ipCountFile = new File(ipCountPrefixName + DateUtil.formatToShort(new Date()) + ipCountSuffixName);
		if(!ipCountFile.exists()){
			ipCount = new AtomicInteger();
		}else{
			try {
				ipCount = new AtomicInteger(NumberUtils.toInt(FileUtils.readFileToString(ipCountFile)));
			} catch (IOException e) {
				ipCount = new AtomicInteger();
			}
		}
		logger.info("今日获取IP：{}", ipCount.get());
	}
	
	/**
	 * 持久化ip池
	 * @author：涂有
	 * @date 2017年11月10日 下午6:16:43
	 */
	public void store(){
		try {
			File ipCountFile = new File(ipCountPrefixName + DateUtil.formatToShort(new Date()) + ipCountSuffixName);
			if(!ipCountFile.exists()){
				if(!ipCountFile.getParentFile().exists()){
					ipCountFile.getParentFile().mkdirs();
				}
				ipCountFile.createNewFile();
				ipCount.set(0); //重置今日ip统计数
			}
			FileUtils.writeStringToFile(ipCountFile, String.valueOf(ipCount.get()));
			logger.info("今日获取IP：{}", ipCount.get());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getIpCount() {
		return ipCount.get();
	}
	
	public void addCount(){
		ipCount.incrementAndGet();
	}
}
