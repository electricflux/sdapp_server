package com.sdapp.logger;

import java.util.logging.Logger;

/**
 * Singleton logger class
 * @author jayarama
 *
 */
public class SdLogger {

	private static SdLogger logger = new SdLogger();
	private static final Logger log = Logger.getLogger(SdLogger.class.getName());
	
	/**
	 * Private constructor
	 */
	private SdLogger() {}
	
	/**
	 * Return static SdLogger instance
	 * @return
	 */
	public static SdLogger getInstance()
	{
		return logger;
	}
	
	/**
	 * Get logger instance
	 * @return
	 */
	public Logger getLogger()
	{
		return log;
	}
}
