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

package net.rom.exoplanets.astronomy.highorbit;

import java.util.UUID;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;

public class HighOrbit extends CelestialBody {

    protected int mothershipId;


    // the backslash should definitely not be valid for unlocalizedName
    public static final String nameSeparator = "\\";

    public HighOrbit(int id, UUID owner) {
        super("mothership_"+id);
        mothershipId = id;
        this.setBodyIcon(new ResourceLocation(ExoInfo.RESOURCE_PREFIX, "textures/gui/mothership_icons/0.png"));
        this.setRelativeOrbitTime(5);
    }

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUnlocalizedNamePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

}
