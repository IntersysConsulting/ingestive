package com.intersys.util;

import java.util.Map;


public interface IIngestionLogger {

		//  Admin
	
	public void configure(Map<String, Object> config);
	
	public boolean isEnabled();
	
		//  Normal logging 
		
	public void finest(String className, String methodName, String msg);

	public void fine(String className, String methodName, String msg);

	public void info(String className, String methodName, String msg);

	public void warning(String className, String methodName, String msg);

	public void warning(String className, String methodName, String msg, Throwable t);

	public void severe(String className, String methodName, String msg);

	public void severe(String className, String methodName, String msg, Throwable t);

		//  Stream activity 
		 
	public void emit(String className, String methodName, String componentName, String componentId, String toStream);
		
	public void execute(String className, String methodName, String componentName, String componentId, String fromStream, String fromComponent);
		
}
