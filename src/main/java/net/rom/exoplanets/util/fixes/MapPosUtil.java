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
