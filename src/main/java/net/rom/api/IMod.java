package net.rom.api;

import net.rom.exoplanets.util.MCUtil;

public interface IMod {
	
	String getModId();
	
	String getModName();
	
	String getVersion();
	
    int getBuildNum();

    default boolean isDevBuild() {
        return 0 == getBuildNum() || MCUtil.isDeobfuscated();
    }
}
