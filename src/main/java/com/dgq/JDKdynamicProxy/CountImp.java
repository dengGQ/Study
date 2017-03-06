package com.dgq.JDKdynamicProxy;

public class CountImp implements Count {

	@Override
	public void queryCount() {
		System.out.println("查看账户...............");
	}

	@Override
	public void updateCount() {
		System.out.println("修改账户...............");
	}

}
