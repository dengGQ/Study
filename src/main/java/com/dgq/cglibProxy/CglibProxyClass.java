package com.dgq.cglibProxy;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理，cglib是针对类来实现代理的，
 * 他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理
 * 
 * @author dgq
 * 
 */
public class CglibProxyClass<T> implements MethodInterceptor {

	private Object target;

	public T getInstance(T target) {
		this.target = target;

		Enhancer enhancer = new Enhancer();

		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);

		return (T)enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		if(Objects.equals(method.getName(), "queryCount")){
			
			System.out.println("proxy start........");
			
			Object invokeSuper = proxy.invokeSuper(obj, args);
			
			System.out.println("proxy end........");
			
			return null;
		}
		
		proxy.invokeSuper(obj, args);
		return null;
	}

	public static void main(String[] args) {

		CglibProxyClass<Count> cglibProxyClass = new CglibProxyClass<Count>();

		Count count = cglibProxyClass.getInstance(new Count());
		
//		count.updateCount();
		count.queryCount();
		
	}
}
