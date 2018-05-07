package com.lenovo.spider.pipeline;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.lenovo.spider.common.Constant;
import com.lenovo.spider.model.DataModel;
import com.lenovo.tripnote.service.JdbcService;

import lombok.extern.log4j.Log4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Log4j
public class JdbcPipline implements Pipeline {

    protected LinkedBlockingQueue<DataModel> dataQueue = new LinkedBlockingQueue<DataModel>();
    
	private ApplicationContext context;

	public JdbcPipline() {
		try {
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
			startUpload(context);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e.fillInStackTrace());
		}
	}
	public void startUpload(ApplicationContext context) {
		LoadDataToStore store = new LoadDataToStore(dataQueue,context.getBean(JdbcService.class));
		store.start();
    }

	@Override
	public void process(ResultItems resultitems, Task task) {
		JSON json = resultitems.get(Constant.analyzePageKey);
		if (json != null) {
			String url = resultitems.get(Constant.urlKey);
			DataModel model = new DataModel();
			model.setUrl(url);
			model.setParentUrl(resultitems.get(Constant.parentKey));
			model.setJsonString(json);
			try {
				dataQueue.put(model);
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error(e.getMessage(),e.fillInStackTrace());
			}
		}
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

}
