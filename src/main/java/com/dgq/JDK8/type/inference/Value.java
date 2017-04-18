package com.dgq.JDK8.type.inference;

/**
 * jdk8中增强了jvm的类型推测功能
 * @author dgq
 *
 * @param <T>
 */
public class Value<T> {
	
	public static<T> T defaultValue(){
		return null;
	}
	
	public T getOrDefault(T value, T defaultValue){
		
		return (value != null) ? value : defaultValue;
	}
}
