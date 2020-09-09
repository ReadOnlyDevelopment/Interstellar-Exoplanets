package net.romvoid95.common.version;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraftforge.common.MinecraftForge;
import net.romvoid95.core.ExoInfo;

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
