package com.wooboo.dsp.base.log;

import org.apache.log4j.PropertyConfigurator;
public class Logger {

	private final static String LOG4J_NAME = "logMain";
	private final static String LOG4J_PATH = "config/log4j.properties";
	private org.apache.log4j.Logger logger = null;
	private static Boolean init = Boolean.FALSE;

	/**
	 * 
	 */
	public Logger() {
		initLogger();
		logger = org.apache.log4j.Logger.getLogger(LOG4J_NAME);
	}

	public Logger(String logName) {
		initLogger();
		logger = org.apache.log4j.Logger.getLogger(logName);
	}

	public Logger(Class callerClass) {
		initLogger();
		logger = org.apache.log4j.Logger.getLogger(callerClass);
	}

	public static Logger getLogger(Class callerClass) {
		return new Logger(callerClass);
	}

	public static Logger getLogger() {
		return new Logger();
	}

	public static Logger getLogger(String logName) {
		return new Logger(logName);
	}

	private static void initLogger() {
		if (!init.booleanValue()) {
			synchronized (init) {
				if (!init.booleanValue()) {
					//DOMConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource(LOG4J_PATH));
					PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource(LOG4J_PATH));  //log4j.properties 

					init = Boolean.TRUE;
				}
			}
		}
	}

    public  void info(String template,Object ...params){
    	String content = composeLog(template, params);
    	logger.info(content);
    }
	public final void debug(String template,Object ...params){
		String content = composeLog(template, params);
    	logger.debug(content);
	}
    
	public final void trace(String message){
		logger.info(message);
	}
	public final void debug(String message){
		logger.debug(message);
	}
	public final void logDebug(Object message) {
		logger.debug(message);
	}

	public final void logDebug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public final void logError(Object message) {
		logger.error(message);
	}

	public final void logError(Object message, Throwable t) {
		logger.error(message, t);
	}

	public final void logFatal(Object message) {
		logger.fatal(message);
	}

	public final void logFatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	public final void logWarning(Object message) {
		logger.warn(message);
	}

	public final void logWarning(Object message, Throwable t) {
		logger.warn(message, t);
	}

	public final void logInfo(Object message) {
		logger.info(message);
	}

	public final void logInfo(Object message, Throwable t) {
		logger.info(message, t);
	}

	public final boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public final boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	private String composeLog(String template, Object...params) {
        String content = template;
        if (params != null && params.length > 0) {
            try{
                content = String.format(template, params);          
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return content;
	}
	
	
    public static void main(String[] args){
    	Logger loger = Logger.getLogger("Test");
    	loger.logInfo("===============");
    	//loger.logInfo("test");
    }

}
