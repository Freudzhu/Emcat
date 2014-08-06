package com.emcat.einterface;

public interface Context extends Container{
	public void addServletMaping(String path,String servlet);
	public Mapper getMapper();
	public void setMapper(Mapper mapper);
}
