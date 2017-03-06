package com.dgq.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 栈的顺序存储结构实现
 * 基于数组实现的顺序栈
 * @author dgq
 *
 * @param <E>
 */
public class StackImplArray<E> implements Stack<E>{
	private static final long serialVersionUID = 1L;
	private E[] item;
	private int top = 0; //栈顶指针
	private int capacity = 0; // 栈容量
	public StackImplArray(){
		this(10);
	}
	
	public StackImplArray(int capacity){
		if(capacity <= 0)
			throw new IllegalArgumentException("栈容量必须大于0");
		else
			this.capacity = capacity;
			item = (E[])new Object[capacity];
	}
	@Override
	public boolean push(E e){
		if(top >= capacity)
			resize(2*capacity);
		item[top++] = e;
		
		return true;
	}
	@Override
	public E peek(){
		if(top == 0)
			throw new RuntimeException("空栈异常");
		else
			return item[top-1];
	}
	@Override
	public E pop(){
		E i = item[--top];
		
		item[top] = null;
		
		if(top < item.length/4)
			resize(item.length/2);
		
		return i;
	}
	
	@Override
	public int length() {
		return item.length;
	}
	
	@Override
	public boolean isEmpty() {
		return top == 0;
	}
	private void resize(int newCapacity){
		E[] n = (E[])new Object[newCapacity];
		System.arraycopy(item, 0, n, 0, capacity);
		this.capacity = newCapacity;
		item = n;
	}
	
	public static void main(String[] args) {
		Stack<Integer> sa = new StackImplArray<Integer>(3);
		System.out.println(sa.isEmpty());

		sa.push(2);
		sa.push(3);
		System.out.println(sa.isEmpty());
		
		sa.peek();
		System.out.println(sa.isEmpty());

		sa.pop();
		System.out.println(sa.isEmpty());
		
		
	}
}
