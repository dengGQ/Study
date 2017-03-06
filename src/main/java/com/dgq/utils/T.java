package com.dgq.utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class T {

	private static int count = 0;

	public static void main(String[] args) {

		class MyTimer extends TimerTask {

			@Override
			public void run() {
				count = (count + 1) % 2;

				System.out.println(Thread.currentThread().getName()
						+ " count: " + count);

				new Timer().schedule(new MyTimer(), 0 + count * 2);
			}
		}

		MyTimer myTimer = new MyTimer();

		System.out.println("开始");

		new Timer().schedule(myTimer, 3000);

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " "
					+ new Date().getSeconds());
		}
	}
}