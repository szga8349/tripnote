package com.lenovo.spider;

import com.lenovo.spider.common.Constant;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class LenovoFileCacheQueueScheduler extends FileCacheQueueScheduler{

	public LenovoFileCacheQueueScheduler(String filePath) {
		super(filePath);
	}

	@Override
	protected boolean noNeedToRemoveDuplicate(Request request) {
		return super.noNeedToRemoveDuplicate(request) ||
	                (request.getExtra(Constant.DOM_EVENT) != null && request.getExtra(Constant.DOM_EVENT) instanceof DomEvent);
	}

	@Override
	public synchronized Request poll(Task task) {
		// TODO Auto-generated method stub
		return super.poll(task);
	}

	@Override
	public void push(Request request, Task task) {
		// TODO Auto-generated method stub
		super.push(request, task);
	}

}
