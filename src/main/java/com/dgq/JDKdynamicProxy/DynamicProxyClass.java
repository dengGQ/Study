package com.dgq.JDKdynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.dgq.staticProxy.Count;

/**
 * 1）Interface：对于JDK proxy，业务类是需要一个Interface的，这也是一个缺陷
 * 
 * 2）Proxy，Proxy 类是动态产生的，这个类在调用Proxy.newProxyInstance(targetCls.getClassLoader,
 * targetCls
 * .getInterface,InvocationHander)之后，会产生一个Proxy类的实例。实际上这个Proxy类也是存在的，不仅仅是类的实例
 * 。这个Proxy类可以保存到硬盘上。
 * 
 * 3） Method:对于业务委托类的每个方法，现在Proxy类里面都不用静态显示出来
 * 
 * 4） InvocationHandler:
 * 这个类在业务委托类执行时，会先调用invoke方法。invoke方法再执行相应的代理操作，可以实现对业务方法的再包装
 * 
 * @author dgq
 * 
 */
public class DynamicProxyClass implements InvocationHandler {

	private Object target;

	public Object bind(Object target) {
		this.target = target;

		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		System.out.println("Proxy start............");

		Object result = method.invoke(target, args);

		System.out.println("Proxy end............");

		return result;
	}

	public static void main(String[] args) {
		DynamicProxyClass dynamicProxyClass = new DynamicProxyClass();

		Count count = (Count) dynamicProxyClass.bind(new CountImp());

		count.queryCount();
	}
}
