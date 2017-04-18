package com.dgq.JDK8.InterfaceDefaultMethondAndStaticMethond;

public interface Defaulable {
	
	default String notRequired(){
		return "Default implementation";
	}
}
