package com.dgq.serializable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Map<String,Object> linkedMap = new LinkedHashMap<String, Object>();
		
		linkedMap.put("A", "aaa");
		linkedMap.put("B", "bbb");
		linkedMap.put("C", "ccc");
		linkedMap.put("D", "ddd");
		linkedMap.put("E", "eee");
		linkedMap.put("F", "fff");
		Set<String> keySet = linkedMap.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println();
		}
		
	}
}
