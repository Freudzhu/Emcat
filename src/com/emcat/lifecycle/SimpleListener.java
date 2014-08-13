package com.emcat.lifecycle;

public class SimpleListener implements LifecycleListener{

	@Override
	public void fireLifeListenner(ListenerEvent e) {
		// TODO Auto-generated method stub
		String type = e.getType();
		if(type.equals(LifeCycle.BEFORE_START_EVENT)){
			System.out.println("Listen the before start event");
		}
	}

}
