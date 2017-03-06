package com.dgq.utils;

public class A {
	static {
		System.out.println("A init.......");
	}
	public static int value = 123;

	public A() {

		System.out.println("A instance............");
	}
	
	public static void main(String[] args) {
		A a = new A();
		
	}
}
 