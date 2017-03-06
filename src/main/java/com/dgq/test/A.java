package com.dgq.test;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
public class A implements AInter{
	public static String a = "abc";
	static{
		System.out.println("A init");
		//a = "bcd";
	}
	public A(){
		System.out.println("A instance");
	}
	
	public static void main(String[] args) {
		byte[] b = {-26, -75, -73};
		
		ByteBuffer bb = ByteBuffer.allocate(3);
		
		bb.put(b, 0, 2);
		bb.flip();
		bb.position(0);
		
		CharBuffer buffer = UTF_8.decode(bb);
		
		char charAt = buffer.charAt(0);
		
		System.out.println(charAt);
	}
}
