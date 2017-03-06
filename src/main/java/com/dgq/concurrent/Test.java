package com.dgq.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import org.apache.commons.lang.math.*;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		MyCallable myCallable = new MyCallable();
		MyRunnable myRunnable = new MyRunnable();
		
		/*FutureTask<Integer> futureTask = new FutureTask<Integer>(myCallable);
		Thread thread = new Thread(futureTask);
		thread.start();
		
		FutureTask<Integer> futureTask2 = new FutureTask<Integer>(myCallable);
		Thread thread1 = new Thread(futureTask2);
		thread1.start();
		
		Callable<Integer> callable = Executors.privilegedCallable(myCallable);
		Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);
		
		
		*/
		/*System.out.println("主线程for循环执行完毕..");
		Callable<String> callable2 = Executors.callable(myRunnable, "dgq");
		Future<?> future = Executors.newSingleThreadExecutor().submit(callable2);
		Object object = future.get();
		System.out.println(object);*/
		
		/*Integer sum2 = futureTask2.get();
		System.out.println("sum2: " + sum2);
		
		Integer sum = futureTask.get();
		System.out.println("sum: " + sum);

		Integer sum3 = future.get();
		System.out.println("sum3:" + sum3);*/
		Random random = new Random();
		while(true){
			new JVMRandom().nextInt();
			System.out.println(random.nextInt(100));
		}
	}
	
	static class MyCallable implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			int sum = 0;
			for (int i = 0; i < 100; i++) {
				System.out.println("Thread Name "+Thread.currentThread().getName() + " " + i);
				sum++;
			}

			return sum;
		}
	}
	
	static class MyRunnable implements Runnable{
		@Override
		public void run() {
			System.out.println("Thread Name ——"+Thread.currentThread().getName());
		}
		
	}
}
