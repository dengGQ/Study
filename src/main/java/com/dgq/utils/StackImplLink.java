package com.dgq.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 栈的链式存储结构的实现
 * @author dgq
 *
 * @param <E>
 */
public class StackImplLink<E> implements Stack<E>{
	
	private Node<E> top; // 栈顶元素
	
	private int size; //当前栈大小
	
	private class Node<E>{
		E e;
		Node<E> next;
		public Node(E e, Node next){
			this.e = e;
			this.next = next;
		}
	}
	
	public StackImplLink(){
		this.top = null;
	}
	
	@Override
	public int length(){
		return size;
	}
	
	@Override
	public boolean isEmpty(){
		return size == 0;
	}
	
	@Override
	public boolean push(E e){
		top = new Node(e, top);
		size++;
		
		return true;
	}
	
	@Override
	public E peek(){
		if(isEmpty())
			throw new RuntimeException("空栈异常");
		else
			return top.e;
	}
	
	@Override
	public E pop(){
		if(isEmpty())
			throw new RuntimeException("空栈异常");
		
		E value = top.e;
		top = top.next;
		size--;
		
		return value;
	}
	public static void main(String[] args) {
		StackImplLink<Integer> sl = new StackImplLink<Integer>();
		sl.push(12);
		System.out.println(sl.peek());
		
		System.out.println(sl.pop());
		
		System.out.println(sl.length());
		System.out.println(sl.isEmpty());
		
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		
		PriorityBlockingQueue<Object> queue = new PriorityBlockingQueue<>();
	}
}
