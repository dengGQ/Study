package com.dgq.queue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Cache<K, V> {

	private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<K, V>();

	// 延迟队列
	public DelayQueue<DelayElment<Pair<K, V>>> q = new DelayQueue<DelayElment<Pair<K, V>>>();

	// 线程守护
	private Thread daemonThread;

	public Cache() {

		Runnable daemonTask = new Runnable() {
			@Override
			public void run() {
				daemonCheck();
			}
		};

		daemonThread = new Thread(daemonTask);
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}

	public void daemonCheck() {
		for (;;) {
			try {
				DelayElment<Pair<K, V>> delayItem = q.take();

				Pair<K, V> item = delayItem.getItem();
				
				cacheObjMap.remove(item.first, item.second);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加缓存对象
	 * 
	 * @param key
	 * @param value
	 * @param time
	 * @param unit
	 */
	public void put(K key, V value, long time, TimeUnit unit) {
		V oldValue = cacheObjMap.put(key, value);

		if (oldValue != null) {
			q.remove(key);
		}

		long nanotime = TimeUnit.NANOSECONDS.convert(time, unit);

		q.put(new DelayElment<Pair<K, V>>(new Pair<K, V>(key, value), nanotime));
	}

	public V get(K key) {
		return cacheObjMap.get(key);
	}
}
