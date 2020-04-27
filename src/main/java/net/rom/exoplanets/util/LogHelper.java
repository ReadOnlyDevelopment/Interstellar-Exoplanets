package net.rom.exoplanets.util;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.launchwrapper.Launch;
import net.rom.exoplanets.ExoInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class LogHelper.
 */
public class LogHelper {
	
	/** The log. */
	private static Logger LOG = LogManager.getFormatterLogger(ExoInfo.NAME);

	/** The Constant obf. */
	public static final boolean obf;

	static {
		boolean flag = true;
		try {
			flag = (Launch.classLoader.getClassBytes("net.minecraft.world.World") == null);
		} catch (IOException iOException) {
		}
		obf = flag;
	}

	private static void log(Level logLevel, Object object) {
		LOG.log(logLevel, String.valueOf(object));
	}

	private static void log(Level logLevel, Object object, Throwable throwable) {
		LOG.log(logLevel, String.valueOf(object), throwable);
	}

	/**
	 * Dev.
	 *
	 * @param object the object
	 */
	public static void dev(Object object) {
		if (!obf)
			log(Level.INFO, "[DEV]: " + object);
	}
	
	/**
	 * Catching.
	 *
	 * @param throwable the throwable
	 */
	public static void catching(Throwable throwable) {
		LOG.catching(throwable);
	}

	/**
	 * All.
	 *
	 * @param object the object
	 */
	public static void all(Object object) {

		log(Level.ALL, object);
	}

	/**
	 * Debug.
	 *
	 * @param object the object
	 */
	public static void debug(Object object) {
		log(Level.DEBUG, object);
	}

	/**
	 * Error.
	 *
	 * @param object the object
	 */
	public static void error(Object object) {
		log(Level.ERROR, object);
	}

	/**
	 * Fatal.
	 *
	 * @param object the object
	 */
	public static void fatal(Object object) {
		log(Level.FATAL, object);
	}

	/**
	 * Info.
	 *
	 * @param object the object
	 */
	public static void info(Object object) {
		log(Level.INFO, object);
	}

	/**
	 * Trace.
	 *
	 * @param object the object
	 */
	public static void trace(Object object) {
		log(Level.TRACE, object);
	}

	/**
	 * Warn.
	 *
	 * @param object the object
	 */
	public static void warn(Object object) {
		log(Level.WARN, object);
	}

	/**
	 * Formatted all.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public static void formatted_All(String format, Object... object) {
		log(Level.ALL, String.format(format, object));
	}

	/**
	 * Formatted debug.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public static void formatted_Debug(String format, Object... object) {
		log(Level.DEBUG, String.format(format, object));
	}

	/**
	 * Formatted error.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public static void formatted_Error(String format, Object... object) {
		log(Level.ERROR, String.format(format, object));
	}

	/**
	 * Formatted fatal.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public static void formatted_Fatal(String format, Object... object) {
		log(Level.FATAL, String.format(format, object));
	}

	/**
	 * Formatted info.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public static void formatted_Info(String format, Object... object) {
		log(Level.INFO, String.format(format, object));
	}

	/**
	 * Formatted trace.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public static void formatted_Trace(String format, Object... object) {
		log(Level.TRACE, String.format(format, object));
	}

	/**
	 * Formatted warn.
	 *
	 * @param format the format
	 * @param object the object
	 */
	public static void formatted_Warn(String format, Object... object) {
		log(Level.WARN, String.format(format, object));
	}

	/**
	 * Throwable all.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public static void throwable_All(String object, Throwable throwable) {
		log(Level.ALL, object, throwable);
	}

	/**
	 * Throwable debug.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public static void throwable_Debug(String object, Throwable throwable) {
		log(Level.DEBUG, object, throwable);
	}

	/**
	 * Throwable error.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public static void throwable_Error(String object, Throwable throwable) {
		log(Level.ERROR, object, throwable);
	}

	/**
	 * Throwable fatal.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public static void throwable_Fatal(String object, Throwable throwable) {
		log(Level.FATAL, object, throwable);
	}

	/**
	 * Throwable info.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public static void throwable_Info(String object, Throwable throwable) {
		log(Level.INFO, object, throwable);
	}

	/**
	 * Throwable trace.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public static void throwable_Trace(String object, Throwable throwable) {
		log(Level.TRACE, object, throwable);
	}

	/**
	 * Throwable warn.
	 *
	 * @param object the object
	 * @param throwable the throwable
	 */
	public static void Throwable_Warn(String object, Throwable throwable) {
		log(Level.WARN, object, throwable);
	}

	/**
	 * Big all.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public static void bigAll(String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		all("****************************************");
		formatted_All("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			formatted_All("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		all("****************************************");
	}

	/**
	 * Big debug.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public static void bigDebug(String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		debug("****************************************");
		formatted_Debug("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			formatted_Debug("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		debug("****************************************");
	}

	/**
	 * Big error.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public static void bigError(String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		error("****************************************");
		formatted_Error("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			formatted_Error("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		error("****************************************");
	}

	/**
	 * Big fatal.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public static void bigFatal(String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		fatal("****************************************");
		formatted_Fatal("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			formatted_Fatal("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		fatal("****************************************");
	}

	/**
	 * Big info.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public static void bigInfo(String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		info("****************************************");
		formatted_Info("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			formatted_Info("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		info("****************************************");
	}

	/**
	 * Big trace.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public static void bigTrace(String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		trace("****************************************");
		formatted_Trace("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			formatted_Trace("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		trace("****************************************");
	}

	/**
	 * Big warn.
	 *
	 * @param format the format
	 * @param data the data
	 */
	public static void bigWarn(String format, Object... data) {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		warn("****************************************");
		formatted_Warn("* " + format, data);
		for (int i = 2; i < 8 && i < trace.length; i++) {
			formatted_Warn("*  at %s%s", new Object[] { trace[i].toString(), (i == 7) ? "..." : "" });
		}
		warn("****************************************");
	}

}
