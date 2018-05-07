package com.lenovo.spider.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import us.codecraft.webmagic.Request;

@SuppressWarnings("serial")
public class LenovoPriorityQueue extends PriorityQueue<Request> {

	// 匿名Comparator实现
	public static Comparator<Request> requestPriorityComparator = new Comparator<Request>() {

		@Override
		public int compare(Request c1, Request c2) {
			return (int) (c1.getPriority() - c2.getPriority());
		}
	};

	public PriorityQueue<Request> queue = new PriorityQueue<>(requestPriorityComparator);
	
	
	private List<Request> list = new ArrayList<Request>();

	@Override
	public boolean add(Request e) {
        if(list.contains(e)){
        	return false;
        }
        list.add(e);
        return queue.add(e);
	}

	@Override
	public Request poll() {
		
		return queue.poll();
	}
}
