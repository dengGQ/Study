package com.dgq.JDK8.parameter.names;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Optional;

/**
 * jdk8 新特性可以通过Method类的getParameters方法获取所有的parameter，而parameter中封装了该方法参数的名称
 * 注意：需要通过jdk新的javac编译且带上-parameters参数
 * @author dgq
 *
 */
public class ParameterNames {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		Method method = ParameterNames.class.getMethod("str", String.class, String.class);
		
		for(final Parameter p : method.getParameters()){
			System.out.println(p.getName());
		}
		
	}
	
	public void str(String name, String age){
		Optional<String> a = Optional.ofNullable("23");
	}
}
