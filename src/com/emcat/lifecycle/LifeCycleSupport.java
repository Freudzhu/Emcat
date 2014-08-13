package com.emcat.lifecycle;

import java.util.ArrayList;

public class LifeCycleSupport implements LifeCycle{
	
	ArrayList<LifecycleListener> lifecycleListeners;
	
	public LifeCycleSupport(){
		lifecycleListeners = new ArrayList<LifecycleListener>();
	}

	@Override
	public void start() throws LifeCycleException {
		// TODO Auto-generated method stub
		throw new LifeCycleException("Not Support");
	}

	@Override
	public void stop() throws LifeCycleException {
		// TODO Auto-generated method stub
		throw new LifeCycleException("Not Support");
	}

	@Override
	public void addLifeCycleListener(LifecycleListener listener) {
		lifecycleListeners.add(listener);
		
	}

	@Override
	public void removeLifeCycleListener(LifecycleListener listener) {
		// TODO Auto-generated method stub
		lifecycleListeners.remove(listener);
	}

	public void fireLifeListenner(String type) {
		
		ListenerEvent e = new ListenerEvent(this, type);
		
		for(LifecycleListener listener : lifecycleListeners){
			
			listener.fireLifeListenner(e);
		}
		
	}

}

	
