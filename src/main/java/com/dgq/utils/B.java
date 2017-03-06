package com.dgq.utils;

public class B extends A {
	static {
		System.out.println("B init..........");
	}

	public static final String HELLO = "hello.........";

	public B() {

		System.out.println("B instance............");
	}
}
