package net.rom.exoplanets.util.version;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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
			VersionChecker.onlineVersion = r.readLine();
			//ExoplanetsMod.logger.bigInfo("%s", VersionChecker.onlineVersion);
			r.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		VersionChecker.doneChecking = true;
	}
}
