package com.intersys.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngestionLogger implements IIngestionLogger {
	
	private static IngestionLogger instance = null;
	
	private boolean configured = false;
	private boolean enabled = true;

	private static HashMap<String, Logger> classLoggers = new HashMap<String, Logger>();

	private IngestionLogger() {
		
	}
	
	public static IngestionLogger getInstance() {
		if (instance == null) {
			instance = new IngestionLogger();
		}
		return instance;	
	}

	private boolean isEnabled(String property, Map<String, Object> config) {
		String setting = (String) config.get(property);
		return ("true".equalsIgnoreCase(setting));
	}

	@Override
	public void finest(String className, String methodName, String msg) {
		if (!enabled) { return; }
		getLoggerFor(className).logp(Level.FINEST,className,methodName,msg);
	}

	@Override
	public void fine(String className, String methodName, String msg) {
		if (!enabled) { return; }
		getLoggerFor(className).logp(Level.FINE,className,methodName,msg);
	}

	@Override
	public void info(String className, String methodName, String msg) {
		if (!enabled) { return; }
		getLoggerFor(className).logp(Level.INFO,className,methodName,msg);
	}

	@Override
	public void warning(String className, String methodName, String msg) {
		if (!enabled) { return; }
		getLoggerFor(className).logp(Level.WARNING,className,methodName,msg);
	}

	@Override
	public void warning(String className, String methodName, String msg, Throwable t) {
		if (!enabled) { return; }
		getLoggerFor(className).logp(Level.WARNING,className,methodName,msg,t);
	}

	@Override
	public void severe(String className, String methodName, String msg) {
		if (!enabled) { return; }
		getLoggerFor(className).logp(Level.SEVERE,className,methodName,msg);
	}

	@Override
	public void severe(String className, String methodName, String msg, Throwable t) {
		if (!enabled) { return; }
		getLoggerFor(className).logp(Level.SEVERE,className,methodName,msg,t);
	}
	
	@Override
	public void emit(String className, String methodName, String componentName, String componentId, String toStream) {
		if (!enabled) { return; }

	}
	
	@Override
	public void execute(String className, String methodName, String componentName, String componentId, String fromStream, String fromComponent) {
		if (!enabled) { return; }

	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public synchronized void configure(Map<String, Object> config) {
		
		if (configured) { return; }

		if (isEnabled("event.java.logging",config)) {
			enabled = true;
		}

		configured = true;
	}

	private Logger getLoggerFor(String key) {
		Logger logger = classLoggers.get(key);
		if (logger == null) {
			logger = Logger.getLogger(key);
			classLoggers.put(key, logger);
		}
		return logger;
	}

}
