package net.romvoid95.common.utility;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.romvoid95.core.ExoInfo;

public class ExoLogger {
	
	private static Logger LOG = LogManager.getFormatterLogger(ExoInfo.NAME);

	private static void log(Level logLevel, Object object) {
		LOG.log(logLevel, String.valueOf(object));
	}

	private static void log(Level logLevel, Object object, Throwable throwable) {
		LOG.log(logLevel, String.valueOf(object), throwable);
	}
	
	/**
	 * Catching.
	 *
	 * @param throwable the throwable
	 */
	public void catching(Throwable throwable) {
		LOG.catching(throwable);
	}

	/**
	 * All.
	 *
	 * @param object the object
	 */
	public void all(Object object) {

		log(Level.ALL, object);
	}

	/**
	 * Debug.
	 *
	 * @param object the object
	 */
	public void debug(Object object) {
		log(Level.DEBUG, object);
	}

	/**
	 * Error.
	 *
	 * @param object the object
	 */
	public void error(Object object) {
		log(Level.ERROR, object);
	}

	/**
	 * Fatal.
	 *
	 * @param object the object
	 */
	public void fatal(Object object) {
		log(Level.FATAL, object);
	}

	/**
	 * Info.
	 *
	 * @param object the object
	 */
	public void info(Object object) {
		log(Level.INFO, object);
	}

	/**
	 * Trace.
	 *
	 * @param object the object
	 */
	public void trace(Object object) {
		log(Level.TRACE, object);
	}

	/**
	 * Warn.
	 *
	 * @param object the object
	 */
	public void warn(Object object) {
		log(Level.WARN, object);
	}

	/**
	 * Formatted all.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void all(String format, Object... object) {
		log(Level.ALL, String.format(format, object));
	}

	/**
	 * Formatted debug.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void debug(String format, Object... object) {
		log(Level.DEBUG, String.format(format, object));
	}

	/**
	 * Formatted error.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void error(String format, Object... object) {
		log(Level.ERROR, String.format(format, object));
	}

	/**
	 * Formatted fatal.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void fatal(String format, Object... object) {
		log(Level.FATAL, String.format(format, object));
	}

	/**
	 * Formatted info.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void info(String format, Object... object) {
		log(Level.INFO, String.format(format, object));
	}

	/**
	 * Formatted trace.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void trace(String format, Object... object) {
		log(Level.TRACE, String.format(format, object));
	}

	/**
	 * Formatted warn.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public void warn(String format, Object... object) {
		log(Level.WARN, String.format(format, object));
	}

}
