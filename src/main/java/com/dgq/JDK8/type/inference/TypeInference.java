
package com.dgq.JDK8.type.inference;

public class TypeInference {
	
	public static void main(String[] args) {
		
		Value<String> value = new Value<String>();
		
		value.getOrDefault("dgq", Value.defaultValue());
		
		
	}
}
