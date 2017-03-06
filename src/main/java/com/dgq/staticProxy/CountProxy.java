package com.dgq.staticProxy;

import com.dgq.JDKdynamicProxy.CountImp;
import com.dgq.JDKdynamicProxy.DynamicProxyClass;

/**
 * 1.接口：代理类需要实现一个接口，这个接口和委托类的接口是一样的，这样proxy才能和委托类行为表现一致
 * 
 * 2.方法: 由于接口限制，proxy类中也要有interface中的各个方法，这就造成了代码重复
 * 
 * @author dgq
 * 
 */
public class CountProxy implements Count {

	private CountImp countImp;

	public CountProxy(CountImp countImp) {
		this.countImp = countImp;
	}

	@Override
	public void queryCount() {
		System.out.println("事务处理前....");

		countImp.queryCount();

		System.out.println("事务处理后....");
	}

	@Override
	public void updateCount() {

		System.out.println("事务处理前....");

		countImp.queryCount();

		System.out.println("事务处理后....");
	}

	public static void main(String[] args) {

		CountProxy proxy = new CountProxy(new CountImp());
		proxy.queryCount();

	}

}
