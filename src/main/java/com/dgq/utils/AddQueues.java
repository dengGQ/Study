package com.dgq.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.dgq.common.GlobalVariables;

public class AddQueues implements Runnable {

	private Object object;

	public AddQueues(Object obj) {
		this.object = obj;
	}

	@Override
	public void run() {
		try {
			// System.out.println("开始放入数据： "+object);

			GlobalVariables.queue.put(object);

			// System.out.println(Thread.currentThread().getName() + "已经放了数据，" +
			// "队列目前有" + GlobalVariables.queue.size() + "个数据");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			GlobalVariables.pool.submit(new AddQueues(i));
		}

		final List<Object> list = new ArrayList<Object>();

		int drainTo = GlobalVariables.queue.drainTo(list);

		TimerTask timer = new TimerTask() {
			@Override
			public void run() {
				System.out.println(GlobalVariables.queue.size());
				System.out.println(list);
			}
		};

		timer.wait(1000);
	}
}
