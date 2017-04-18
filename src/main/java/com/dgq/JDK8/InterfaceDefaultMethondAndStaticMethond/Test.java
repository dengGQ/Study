package com.dgq.JDK8.InterfaceDefaultMethondAndStaticMethond;

public class Test {
	public static void main(String[] args) {
		
		Defaulable defaulable = DefaultableFactory.create(DefaultableImpl :: new);
		
		Defaulable defaulable2 = DefaultableFactory.create(OverridableImpl::new);
		
		System.out.println(defaulable.notRequired());
		System.out.println(defaulable2.notRequired());
	}
}
