package com.dgq.queue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

	public static void main(String[] args) throws Exception {
		
		
		Cache<Integer, String> cache = new Cache<Integer, String>();
	
		cache.put(1, "dgq1", 5, TimeUnit.SECONDS); 
		cache.put(2, "dgq2", 4,TimeUnit.SECONDS); 
		cache.put(3, "dgq3", 7, TimeUnit.SECONDS);
		cache.put(4, "dgq4", 6, TimeUnit.SECONDS);
		cache.put(5, "dgq5", 8, TimeUnit.SECONDS);
		cache.put(6, "dgq6", 1, TimeUnit.SECONDS);
	
		/*while(!cache.q.isEmpty()){
			DelayElment<Pair<Integer,String>> elment = cache.q.take();
			
			System.out.println(elment.getItem().second);
		}*/
		
		/*for (Iterator iterator = cache.q.iterator(); iterator.hasNext();) {
			DelayElment<Pair<Integer, String>> de = (DelayElment) iterator.next();
			System.out.println(de.getItem().second);
		}*/
		

		Thread.sleep(2000); {
			String str = cache.get(2);
			System.out.println(str);
		}
		
		Thread.sleep(2000); 
		{ 
			String str1 = cache.get(2);
			System.out.println(str1);
		}
		
		
		

		//优先队列
		/*PriorityQueue<Integer> q = new PriorityQueue<Integer>(); 
		 q.add(4);
		 q.add(9); 
		 q.add(2); 
		 q.add(3); 
		 q.add(6); 
		 q.add(1); 
		 q.add(-1);
		 
		 while (!q.isEmpty()) { 
			 System.out.println(q.poll());
		}*/
	}
}
