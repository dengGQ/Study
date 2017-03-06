package com.dgq.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/**
 * LRU缓存 LinkedHashMap 继承方式的实现
 * @author dgq
 *
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V>{
	
	private static int MAX_CACHE_SIZE = 5;
	
	public LRUCache(){
		this(MAX_CACHE_SIZE);
	}
	
	public LRUCache(int size){
		super((int)Math.ceil(size/0.75f)+1, 0.75f, true);
		MAX_CACHE_SIZE = size;
	}
	@Override
	protected boolean removeEldestEntry(
			java.util.Map.Entry<K, V> eldest) {
		return size() > MAX_CACHE_SIZE ;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		for(Map.Entry<K, V> entry : entrySet()){
			sb.append(String.format("%s:%s,", entry.getKey(), entry.getValue()));
		}
		sb.setCharAt(sb.lastIndexOf(","), '}');
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		/*Map<String, Object> map = new LinkedHashMap<String, Object>((int)Math.ceil(MAX_CACHE_SIZE/0.75f) + 1, 0.75f, true){
			@Override
		    protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
				return size() > MAX_CACHE_SIZE;
		    }
		};*/
		
		LRUCache myMap = new LRUCache();
		myMap.put("a", "A");
		myMap.put("b", "B");
		System.out.println(myMap.toString());
		myMap.put("c", "C");
		myMap.put("d", "D");
		myMap.put("e", "E");
		System.out.println(myMap.toString());
		myMap.put("f", "F");
		System.out.println(myMap.toString());
		
		System.out.println(myMap.size());
		
		
		
	}
}
