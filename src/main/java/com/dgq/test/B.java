package com.dgq.test;

public class B extends A implements BInter{
	public static String b = a;
	static{
		System.out.println("B init");
	}
	
	public B(){
		System.out.println("B instance");
	}
	public static void add(){
		System.out.println("add---------");
	}
}
