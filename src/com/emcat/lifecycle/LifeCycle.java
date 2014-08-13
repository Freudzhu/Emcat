package com.emcat.lifecycle;

public interface LifeCycle {
	
	public void start() throws LifeCycleException;
	public void stop() throws LifeCycleException;
	public void addLifeCycleListener(LifecycleListener listener) throws LifeCycleException;
	public void removeLifeCycleListener(LifecycleListener listener) throws LifeCycleException;
	
	
	//life event type
	public static String START_EVENT = "start_event";
	public static String STOP_EVENT = "stop_event";
	public static String BEFORE_START_EVENT = "before_start_event";
	public static String AFTER_START_EVENT = "after_start_event";
	public static String BEFORE_STOP_EVENT = "before_stop_event";
	public static String AFTER_STOP_EVENT = "after_stop_event";

}
					