package com.dgq.JDK8.InterfaceDefaultMethondAndStaticMethond;

import java.util.function.Supplier;

public interface DefaultableFactory {
	static Defaulable create(Supplier<Defaulable> supplier){
		return supplier.get();
	}
}
