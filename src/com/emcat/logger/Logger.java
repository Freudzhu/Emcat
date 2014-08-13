package com.emcat.logger;

public interface Logger {
	
	public static final int FATAL = Integer.MIN_VALUE;  
	public static final int ERROR = 1;  
	public static final int WARNING = 2;  
	public static final int INFORMATION = 3;  
	public static final int DEBUG = 4;
	
	public int getLevel();
	public void setLevel(int level);
	public void log(int logLevel,String msg);
	public void log(String msg);
}
