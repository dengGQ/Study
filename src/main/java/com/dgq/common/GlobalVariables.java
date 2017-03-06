package com.dgq.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalVariables {
	public static ExecutorService pool = Executors.newFixedThreadPool(10);
	public static BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(
			10);

	public static Map<String, Object> map = new HashMap<String, Object>();

}
