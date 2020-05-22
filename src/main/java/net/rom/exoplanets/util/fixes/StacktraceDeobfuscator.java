/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.util.fixes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.rom.exoplanets.util.Deobf;

/**
 * StacktraceDeobfuscator from VanillaFix
 * https://github.com/DimensionalDevelopment/VanillaFix/blob/master/src/main/java/org/dimdev/vanillafix/crashes/StacktraceDeobfuscator.java
 * 
 * Modified to work with custom mappings
 * 
 * @author Runemoro
 */
public final class StacktraceDeobfuscator {
	private static final boolean DEBUG_IN_DEV = Deobf.isDeobfuscated(); // Makes this MCP -> SRG for testing in dev. Don't forget to set to false when
	// done!
	private static HashMap<String, String> srgMcpMethodMap = null;

	/**
	 * If the file does not exits, downloads latest method mappings and saves them
	 * to it. Initializes a HashMap between obfuscated and deobfuscated names from
	 * that file.
	 */
	public static void init(File mappings) {
		if (srgMcpMethodMap != null)
			return;

		// Download the file if necessary
		if (mappings.exists()) {
			if (!mappings.getName().contains("20171003")) {
				HttpURLConnection connection = null;
				try {
					// URL mappingsURL = new
					// URL("http://export.mcpbot.bspk.rs/mcp_stable_nodoc/39-1.12/mcp_stable_nodoc-39-1.12.zip");
					URL mappingsURL = new URL("http://export.mcpbot.bspk.rs/mcp_snapshot_nodoc/20171003-1.12/mcp_snapshot_nodoc-20171003-1.12.zip");
					connection = (HttpURLConnection) mappingsURL.openConnection();
					connection.setDoInput(true);
					connection.connect();
					try (InputStream inputStream = connection.getInputStream()) {
						ZipInputStream zipInputStream = new ZipInputStream(inputStream);
						ZipEntry entry;
						while ((entry = zipInputStream.getNextEntry()) != null) {
							if (entry.getName().equals("methods.csv")) {
								try (FileOutputStream out = new FileOutputStream(mappings)) {
									byte[] buffer = new byte[2048];
									int len;
									while ((len = zipInputStream.read(buffer)) > 0) {
										out.write(buffer, 0, len);
									}
								}
								break;
							}
						}
						if (entry == null) {
							throw new RuntimeException("Downloaded zip did not contain methods.csv");
						}
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				} finally {
					if (connection != null)
						connection.disconnect();
				}
			}
		}

		// Read the mapping
		HashMap<String, String> srgMcpMethodMap = new HashMap<>();
		try (Scanner scanner = new Scanner(mappings)) {
			scanner.nextLine(); // Skip CSV header
			while (scanner.hasNext()) {
				String mappingLine = scanner.nextLine();
				int commaIndex = mappingLine.indexOf(',');
				String srgName = mappingLine.substring(0, commaIndex);
				String mcpName = mappingLine.substring(commaIndex + 1, commaIndex + 1 + mappingLine.substring(commaIndex + 1).indexOf(','));

				// System.out.println(srgName + " <=> " + mcpName);
				if (!DEBUG_IN_DEV) {
					srgMcpMethodMap.put(srgName, mcpName);
				} else {
					srgMcpMethodMap.put(mcpName, srgName);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Set the map only if it's successful, to make sure that it's complete
		StacktraceDeobfuscator.srgMcpMethodMap = srgMcpMethodMap;
	}

	public static void deobfuscateThrowable(Throwable t) {
		Deque<Throwable> queue = new ArrayDeque<>();
		queue.add(t);
		while (!queue.isEmpty()) {
			t = queue.remove();
			t.setStackTrace(deobfuscateStacktrace(t.getStackTrace()));
			if (t.getCause() != null)
				queue.add(t.getCause());
			Collections.addAll(queue, t.getSuppressed());
		}
	}

	public static StackTraceElement[] deobfuscateStacktrace(StackTraceElement[] stackTrace) {
		int index = 0;
		for (StackTraceElement el : stackTrace) {
			stackTrace[index++] = new StackTraceElement(el.getClassName(), deobfuscateMethodName(el.getMethodName()), el.getFileName(), el.getLineNumber());
		}
		return stackTrace;
	}

	public static String deobfuscateMethodName(String srgName) {
		if (srgMcpMethodMap == null) {
			return srgName; // Not initialized
		}

		String mcpName = srgMcpMethodMap.get(srgName);
		// log.debug(srgName + " <=> " + mcpName != null ? mcpName : "?"); // Can't do
		// this, it would be a recursive call to log appender
		return mcpName != null ? mcpName : srgName;
	}

	public static void main(String[] args) {
		init(new File("methods.csv"));
		for (Map.Entry<String, String> entry : srgMcpMethodMap.entrySet()) {
			System.out.println(entry.getKey() + " <=> " + entry.getValue());
		}
	}
}