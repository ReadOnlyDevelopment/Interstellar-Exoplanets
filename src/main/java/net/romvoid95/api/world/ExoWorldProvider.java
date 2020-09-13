package net.romvoid95.api.world;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProviderSpace;
import net.minecraft.util.ResourceLocation;

public abstract class ExoWorldProvider extends WE_WorldProviderSpace implements IExoProvider {

	@Override
	public int getDungeonSpacing () {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		// TODO Auto-generated method stub
		return null;
	}

}
