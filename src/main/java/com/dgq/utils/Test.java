package com.dgq.utils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.LinkLoopException;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Business business = new Business(1);
		Business business2 = new Business(2);

		business.setName("线程1");
		business2.setName("线程2");

		business.start();
		business2.start();
	}
}

final class Business extends Thread {
	private int val;

	public Business(int v) {
		this.val = v;
	}

	public synchronized void mainBusines(int v) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			System.out.println(this.currentThread().getName() + "--------v:"
					+ v);
		}
	}

	@Override
	public void run() {
		try {

			mainBusines(val);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
