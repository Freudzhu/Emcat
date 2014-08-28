package com.emcat.session;

import com.emcat.lifecycle.LifeCycle;
import com.emcat.lifecycle.LifeCycleException;
import com.emcat.lifecycle.LifeCycleSupport;
import com.emcat.lifecycle.LifecycleListener;

public class StandrdManager extends ManagerBase implements LifeCycle,Runnable{
	
	private int checkInterval = 60;
	 
	protected LifeCycleSupport lifecycle = new LifeCycleSupport();
	 
	private Thread thread = null;
	 
	private Boolean threadDone = false; 
	 
	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		 threadStart();
		
	}
	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		threadStop();
	}
	@Override
	public void addLifeCycleListener(LifecycleListener listener)
			throws LifeCycleException {
		// TODO Auto-generated method stub
		lifecycle.addLifeCycleListener(listener);
	}
	@Override
	public void removeLifeCycleListener(LifecycleListener listener)
			throws LifeCycleException {
		// TODO Auto-generated method stub
		lifecycle.removeLifeCycleListener(listener);
	}
	private void threadStart(){
		Thread t =new Thread(this);
		threadDone = false;
		t.setDaemon(true);
		t.start();
	}
	private void threadStop(){
		threadDone = true;
		thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            ;
        }
        thread = null;
	}
	private void threadSleep(){
		try {
			Thread.sleep(checkInterval * 1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!threadDone){
			threadSleep();
			processExpire();
		}
	}
	private void processExpire() {
		// TODO Auto-generated method stub
		long timeNow = System.currentTimeMillis();
		Session[] sessions = findSessions();
		
		for(Session session:sessions){
			if(!session.getValid()){
				continue;
			}
			int maxInactiveInterval = session.getMaxInactiveInterval();
            if (maxInactiveInterval < 0)
                continue;
            int timeIdle =  (int) ((timeNow - session.getLastAccessedTime()) / 1000L);
            if (timeIdle >= maxInactiveInterval) {
            	session.invalidate();
            }
		}
		
	}
	public int getCheckInterval() {
		return checkInterval;
	}
	public void setCheckInterval(int checkInterval) {
		this.checkInterval = checkInterval;
	}
	 
}
