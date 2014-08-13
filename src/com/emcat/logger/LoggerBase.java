package com.emcat.logger;

abstract class LoggerBase implements Logger{
	
	int level;

	@Override
	public void log(int logLevel, String msg) {
		// TODO Auto-generated method stub
		if(logLevel < level){
			log(msg);
		}
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub
		this.level = level;
	}

	abstract public void log(String msg);
}
