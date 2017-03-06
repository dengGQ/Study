package com.dgq.futureMode;

public class Client {
	
	public Name getResult(final String name){
		final FutureName futureName = new FutureName();
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				RealName realName = new RealName(name);
				futureName.setRealName(realName);
			}
		}).start();*/
		
		return futureName;
	}
}
