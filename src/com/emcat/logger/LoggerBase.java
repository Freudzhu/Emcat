package com.emcat.logger;

abstract class LoggerBase implements logger{
	
	int level;

	@Override
	public void log(int logLevel, String msg) {
		// TODO Auto-generated method stub
		if(level < logLevel){
			log(msg);
		}
	}

	abstract public void log(String msg);
}
