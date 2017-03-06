package com.dgq.futureMode;

import java.util.concurrent.Callable;

//public class RealName implements Name{
public class RealName implements Callable<String>{
	String name = null;
	
	public RealName(String name){
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		this.name = name;
	}
	
	/*@Override
	public String getName() {
		return name;
	}*/

	@Override
	public String call() throws Exception {
		//利用sleep方法来表示真是业务是非常缓慢的
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
	}
}
