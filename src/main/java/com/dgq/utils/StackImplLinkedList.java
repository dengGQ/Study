package com.dgq.utils;

import java.util.LinkedList;

/**
 * 基于linkedList实现的栈
 * @author dgq
 *
 */
public class StackImplLinkedList<E> implements Stack<E>{
	private LinkedList<E> l = new LinkedList<E>();
	
	public boolean isEmpty(){
		return l.isEmpty();
	}
	
	public boolean push(E e){
		l.addFirst(e);
		return true;
	}
	
	public E peek(){
		return l.getFirst(); 
	}
	
	public E pop(){
		return l.removeFirst();
	}

	@Override
	public int length() {
		return l.size();
	}
	
	
}
