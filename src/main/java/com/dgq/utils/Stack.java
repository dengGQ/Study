package com.dgq.utils;

public interface Stack<E> {
	/**
	 * 入栈
	 * @param t
	 * @return
	 */
	public boolean push(E e);
	
	/**
	 * 栈顶元素出栈
	 * @return
	 */
	public E pop();
	
	
	/**
	 * 获取栈顶元素
	 * @return
	 */
	public E peek();
	
	/**
	 * 栈元素个数
	 * @return
	 */
	public int length();
	
	/**
	 * 判断栈是否为空
	 * @return
	 */
	public boolean isEmpty();
}
