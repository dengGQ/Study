package com.dgq.futureMode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Test {
	
	public static void main(String[] args) {
		
		HashMap<String,Object> map = new HashMap<String, Object>();//数组+单向链表，继承AbstractMap，实现了Map接口， 线程不安全，允许键/值 为null
		Map<Object,Object> hashtable = new Hashtable<Object,Object>();//数组+单向链表，继承Dictionary, 实现了Map接口，线程安全，不允许键/值 为null
		LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object,Object>(); //数组+双向链表，  继承hashMap, 实现了map
		ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>(); // 键/值都不可以为null
		TreeMap<String,Object> map2 = new TreeMap<String,Object>();//RBTree，继承AbstractMap，实现了NavigableMap接口

		Set<Object> set = new HashSet<Object>(); // hashMap, 继承AbstractSet， 实现了Set接口，元素作为map的key，value是同一个Object对象
		Set<Object> set2 = new TreeSet<Object>(); // TreeMap, 继承AbstractSet， 实现了NavigableSet接口, 元素作为map的key，value是同一个Object对象
		
		concurrentHashMap.put(null, "000");
		/*Client c = new Client();
		
		Name result = c.getResult("dgq");
		
		System.out.println("---------");
		String name = result.getName();
		System.out.println(name);
		System.out.println("---------1");*/
		
		/*FutureTask<String> futureTask = new FutureTask<String>(new RealName("dgq"));
		
		ExecutorService execuService = Executors.newFixedThreadPool(1);
		
		execuService.submit(futureTask);
		
		try {
			String string = futureTask.get();
			
			System.out.println(string);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
}
