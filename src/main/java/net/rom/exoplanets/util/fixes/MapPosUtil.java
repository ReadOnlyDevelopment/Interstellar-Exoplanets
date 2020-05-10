package net.rom.exoplanets.util.fixes;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;

import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;

public class MapPosUtil {
	
    static HashMap<String, SolarSystem> solarSystems = Maps.newHashMap();
    static TreeMap<String, Vector3> vector3s = Maps.newTreeMap();
    static HashMap<SolarSystem, List<Vector3>> solarSystemVectors = Maps.newHashMap();
    
    public static void getMaps() {
//        for (SolarSystem systems : GalaxyRegistry.getRegisteredSolarSystems().values())
//        {
//            Vector3 v3 = systems.getMapPosition(); 
//            List<Vector3> listofVectors = ;
//            if (listOfMoons == null)
//            {
//                listOfMoons = new ArrayList<Moon>();
//            }
//            listOfMoons.add(moon);
//            GalaxyRegistry.moonList.put(planet, listOfMoons);
//        }
    }
    
	public static void scale(float scale) {
		GL11.glScalef(scale, scale, scale);
	}
}
