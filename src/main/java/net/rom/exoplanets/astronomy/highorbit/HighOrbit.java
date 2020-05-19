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
