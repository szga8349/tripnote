package com.lenovo.spider.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Test {
	public static void main(String[] args) {
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.execute(() -> {
			while(true){
				try {
					Thread.sleep(1000);
					System.out.println("1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		service.execute(() -> {
			while(true){
				try {
					Thread.sleep(1000);
					System.out.println("2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
