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
package com.readonlydev.common.version;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.readonlydev.ExoInfo;

import net.minecraftforge.common.MinecraftForge;

public class ThreadVersionChecker extends Thread {

	public ThreadVersionChecker() {
		setName("Version Checker Thread");
		setDaemon(true);
		start();
	}

	@Override
	public void run () {
		try {
			URL            url = new URL("https://raw.githubusercontent.com/ReadOnly-Mods/ReadOnly-Versions/master/"
					+ MinecraftForge.MC_VERSION + ".txt");
			BufferedReader r   = new BufferedReader(new InputStreamReader(url.openStream()));
			String         line;
			while ((line = r.readLine()) != null) {
				if (line.contains(ExoInfo.MODID)) {
					VersionChecker.onlineVersion = "0.3.0:3021311:exoplanets";
				}
			}
			r.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		VersionChecker.doneChecking = true;
	}
}
