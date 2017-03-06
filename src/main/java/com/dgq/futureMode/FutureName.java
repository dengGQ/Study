package com.dgq.futureMode;

public class FutureName implements Name{
	
	RealName realName = null;
	boolean isReady = false;
	
	public synchronized void setRealName(RealName name){
		if(isReady){
			return;
		}
		this.realName = name;
		this.isReady = true;
		notifyAll();
	}
	
	@Override
	public synchronized String getName() {
		/*if(!isReady){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realName.getName();*/
		return null;
	}
}
