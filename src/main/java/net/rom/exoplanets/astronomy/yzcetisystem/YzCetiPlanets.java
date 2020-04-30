package net.rom.exoplanets.astronomy.yzcetisystem;

import net.rom.api.stellar.impl.planet.ExoPlanet;
import net.rom.exoplanets.init.InitPlanets;

public class YzCetiPlanets {

    /**
     * Float array holding each planets orbit in AU from it's parent star
     *
     * YzCeti B = yzCetiAu[0]
     * YzCeti C = yzCetiAu[1]
     * YzCeti D = yzCetiAu[2]
     */
    public static ExoPlanet yzcetid = InitPlanets.yzcetib;
    public static ExoPlanet yzcetic = InitPlanets.yzcetic;
    public static ExoPlanet yzcetib = InitPlanets.yzcetib;

    public static String yzb = "yz_ceti_b";
    public static String yzc = "yz_ceti_c";
    public static String yzd = "yz_ceti_d";

}
