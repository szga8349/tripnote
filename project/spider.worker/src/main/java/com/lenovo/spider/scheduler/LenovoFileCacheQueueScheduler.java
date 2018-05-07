package com.lenovo.spider.scheduler;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.lenovo.spider.common.Constant;
import com.lenovo.spider.queue.LenovoPriorityQueue;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

// Referenced classes of package us.codecraft.webmagic.scheduler:
//            DuplicateRemovedScheduler, MonitorableScheduler

public class LenovoFileCacheQueueScheduler extends DuplicateRemovedScheduler
		implements MonitorableScheduler, Closeable {

	public LenovoFileCacheQueueScheduler(String filePath) {
		this.filePath = System.getProperty("java.io.tmpdir");
		fileUrlAllName = ".urls.txt";
		fileCursor = ".cursor.txt";
		cursor = new AtomicInteger();
		inited = new AtomicBoolean(false);
		if (!filePath.endsWith("/") && !filePath.endsWith("\\"))
			filePath = (new StringBuilder()).append(filePath).append("/").toString();
		this.filePath = filePath;
		initDuplicateRemover();
	}

	private void flush() {
		fileUrlWriter.flush();
		fileCursorWriter.flush();
	}

	private void init(Task task) {
		this.task = task;
		File file = new File(filePath);
		if (!file.exists())
			file.mkdirs();
		readFile();
		initWriter();
		initFlushThread();
		inited.set(true);
		logger.info("init cache scheduler success");
	}

	private void initDuplicateRemover() {
		setDuplicateRemover(new DuplicateRemover() {

			public boolean isDuplicate(Request request, Task task) {
				if (!inited.get())
					init(task);
				return !urls.add(request.getUrl());
			}

			public void resetDuplicateCheck(Task task) {
				urls.clear();
			}

			public int getTotalRequestsCount(Task task) {
				return urls.size();
			}
		});
	}

	private void initFlushThread() {
		flushThreadPool = Executors.newScheduledThreadPool(1);
		flushThreadPool.scheduleAtFixedRate(new Runnable() {

			public void run() {
				flush();
			}
		}, 10L, 10L, TimeUnit.SECONDS);
	}

	private void initWriter() {
		try {
			fileUrlWriter = new PrintWriter(new FileWriter(getFileName(fileUrlAllName), true));
			fileCursorWriter = new PrintWriter(new FileWriter(getFileName(fileCursor), false));
		} catch (IOException e) {
			throw new RuntimeException("init cache scheduler error", e);
		}
	}

	private void readFile() {
		try {
			queue = new LenovoPriorityQueue();
			urls = new LinkedHashSet<String>();
			readCursorFile();
			readUrlFile();
		} catch (FileNotFoundException e) {
			logger.info(
					(new StringBuilder()).append("init cache file ").append(getFileName(fileUrlAllName)).toString());
		} catch (IOException e) {
			logger.error("init file error", e);
		}
	}

	private void readUrlFile() throws IOException {
		BufferedReader fileUrlReader = null;
		fileUrlReader = new BufferedReader(new FileReader(getFileName(fileUrlAllName)));
		int lineReaded = 0;
		do {
			String line;
			if ((line = fileUrlReader.readLine()) == null)
				break;
			if(StringUtils.isEmpty(line))
				continue;
			boolean exsite = false;
			String[] lines = line.split(",");
			if (urls.contains(lines[0].trim())) {
				exsite = true;
			}
			urls.add(lines[0].trim());
			if (++lineReaded > cursor.get() && !exsite) {
				Request request = new Request(lines[0]).setPriority(Long.valueOf(lines[1]));
				Map<String,Object> extras = new HashMap<String,Object>();
				extras.put(Constant.parentKey,lines[2] );
				request.setExtras(extras);
				queue.add(request);
			}
		} while (true);
		if (fileUrlReader != null)
			IOUtils.closeQuietly(fileUrlReader);
	}

	private void readCursorFile() throws IOException {
		BufferedReader fileCursorReader = null;
		fileCursorReader = new BufferedReader(new FileReader(getFileName(fileCursor)));
		String line;
		while ((line = fileCursorReader.readLine()) != null)
			cursor = new AtomicInteger(NumberUtils.toInt(line));
		if (fileCursorReader != null)
			IOUtils.closeQuietly(fileCursorReader);
	}

	public void close() throws IOException {
		flushThreadPool.shutdown();
		fileUrlWriter.close();
		fileCursorWriter.close();
	}

	private String getFileName(String filename) {
		return (new StringBuilder()).append(filePath).append(task.getUUID()).append(filename).toString();
	}

	protected void pushWhenNoDuplicate(Request request, Task task) {
		if (!inited.get())
			init(task);
		if(parentValid(request) && queue.add(request))
		   fileUrlWriter.println(request.getUrl() + "," + request.getPriority()+","+request.getExtra(Constant.parentKey));

	}
	/**验证 parent不是景点酒店等信息
	 * @param request
	 * @return
	 */
	public boolean parentValid(Request request){
		return true;
	}

	public synchronized Request poll(Task task) {
		if (!inited.get())
			init(task);
		fileCursorWriter.println(cursor.incrementAndGet());
		return (Request) queue.poll();
	}

	public int getLeftRequestsCount(Task task) {
		return queue.size();
	}

	public int getTotalRequestsCount(Task task) {
		return getDuplicateRemover().getTotalRequestsCount(task);
	}

	private String filePath;
	private String fileUrlAllName;
	private Task task;
	private String fileCursor;
	private PrintWriter fileUrlWriter;
	private PrintWriter fileCursorWriter;
	private AtomicInteger cursor;
	private AtomicBoolean inited;
	private PriorityQueue<Request> queue;
	private Set<String> urls;
	private ScheduledExecutorService flushThreadPool;

}
