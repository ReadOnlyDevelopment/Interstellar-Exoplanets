/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.romvoid95.common.utility.logging;

import java.util.*;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.*;

import net.romvoid95.ExoInfo;

public class Logging {

	private Logger logger          = LogManager.getLogger(ExoInfo.NAME);
	private String lastDebugOutput = "";

	public Logger getLogger () {
		return logger;
	}

	public void catching (Throwable t) {
		this.logger.catching(t);
	}

	public void debug (String msg, Object... params) {
		this.logger.debug(msg, params);

		String newOutput = this.logger.getMessageFactory().newMessage(msg, params).getFormattedMessage();
		if (!newOutput.equals(lastDebugOutput)) {
			info("[DEBUG] " + newOutput);
			this.lastDebugOutput = newOutput;
		}
	}

	public void error (String msg, Object... params) {
		this.logger.error(msg, params);
	}

	public void fatal (String msg, Object... params) {
		this.logger.fatal(msg, params);
	}

	public void info (String msg, Object... params) {
		this.logger.info(msg, params);
	}

	public void log (Level level, String msg, Object... params) {
		this.logger.log(level, msg, params);
	}

	public void trace (String msg, Object... params) {
		this.logger.trace(msg, params);
	}

	public void warn (String msg, Object... params) {
		this.logger.warn(msg, params);
	}

	public void warn (Throwable t, String msg, Object... params) {
		this.logger.warn(msg, params);
		this.logger.catching(t);
	}

	public void noticableWarning (boolean trace, String string) {
		List<String> list = new ArrayList<>();
		list.add(string);
		noticableWarning(trace, list);
	}

	public void noticableWarning (boolean trace, List<String> lines) {
		this.error("********************************************************************************");

		for (final String line : lines) {
			for (final String subline : wrapString(line, 78, false, new ArrayList<>())) {
				this.error("* " + subline);
			}
		}

		if (trace) {
			final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			for (int i = 2; (i < 8) && (i < stackTrace.length); i++) {
				this.warn("*  at {}{}", stackTrace[i].toString(), i == 7 ? "..." : "");
			}
		}

		this.error("********************************************************************************");
	}

	private static List<String> wrapString (String string, int lnLength, boolean wrapLongWords, List<String> list) {
		final String lines[] = WordUtils.wrap(string, lnLength, null, wrapLongWords).split(SystemUtils.LINE_SEPARATOR);
		Collections.addAll(list, lines);
		return list;
	}

}
