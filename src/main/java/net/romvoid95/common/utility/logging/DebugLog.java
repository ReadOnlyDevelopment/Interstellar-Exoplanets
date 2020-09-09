package net.romvoid95.common.utility.logging;

import org.apache.logging.log4j.Logger;

import lombok.Setter;
import net.romvoid95.api.registry.IReadOnlyMod;

public class DebugLog {
	//region Constants

	private static final int SPAM_COUNTER_LIMIT = 100;
	private static final int SPAM_TIME_LIMIT    = 5;

	//endregion

	//region Fields

	private final Logger       logger;
	private final IReadOnlyMod mod;

	private String  lastOutput         = "";
	private long    lastSpamTimer      = 0;
	private int     spamCounter        = 0;
	private boolean userInformedOfSpam = false;

	/**
	 * If true, this will also log messages to standard output ({@link System#out}), but only if
	 * {@link IModBase#isDevBuild()} returns true as well.
	 */
	@Setter
	private boolean allowStdOut = true;

	//endregion

	/**
	 * Constructor.
	 *
	 * @param logger The {@link Logger} to use. Typically would be the mod's main logger, but can be
	 *               anything.
	 * @param mod    The mod this will belong to. Used to determine whether or not it's a "dev
	 *               build".
	 */
	public DebugLog(Logger logger, IReadOnlyMod mod) {
		this.logger = logger;
		this.mod    = mod;
	}

	/**
	 * Log a debug message. In a distribution build, this will only show the message in {@literal
	 * debug.log}. In dev, this will also print directly to {@link System#out}.
	 * <p>
	 * Spam detection and prevention: This function will prevent the same line from being printed
	 * twice in a row, but duplicate messages will still be logged. If a certain number of
	 * duplicates are logged consecutively, a warning is logged to inform the user of the probable
	 * bug.
	 *
	 * @param msg    The message
	 * @param params Format parameters for the message
	 */
	public void log (String msg, Object... params) {
		String        output             = logger.getMessageFactory().newMessage(msg, params).getFormattedMessage();
		final boolean isSameAsLastOutput = output.equals(lastOutput);

		logger.debug(output);
		spamDetection(isSameAsLastOutput);

		// Print to standard out in dev, for easy monitoring
		if (mod.isDev() && allowStdOut) {
			// Anti-spam feature
			if (!isSameAsLastOutput) {
				System.out.println(output);
				lastOutput = output;
			}
		}
	}

	private void spamDetection (boolean isSameAsLastOutput) {
		// Spam detection, just in case something slips into a distribution build
		if (isSameAsLastOutput) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastSpamTimer > SPAM_TIME_LIMIT) {
				++spamCounter;
				lastSpamTimer = currentTime;
				if (spamCounter > SPAM_COUNTER_LIMIT && !userInformedOfSpam) {
					// Uh-oh
					logger.warn("Mod '{}' ({}) seems to be spamming the debug log. This is probably a bug! Include"
							+ " your debug.log when reporting this.", mod.getModId(), mod.getModName());
					userInformedOfSpam = true;
				}
			}
			else {
				lastSpamTimer = currentTime;
			}
		}
		else {
			spamCounter = 0;
		}
	}
}
