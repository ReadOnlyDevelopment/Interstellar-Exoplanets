package net.rom95.common.astronomy.trappist1.d.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.rom95.api.world.StormProvider;
import net.rom95.client.gui.rendering.Texture;
import net.rom95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.rom95.common.utility.WorldUtils;
import net.rom95.core.ExoInfo;

@EventBusSubscriber
public class StormProviderTrappist1D extends StormProvider {

	@Override
	public void updateStorm (World world) {
		if (world != null && this.isStormActive(world)) {
			for (Object o : world.loadedEntityList.toArray()) {
				if (o instanceof Entity) {
					Entity entity = (Entity) o;

					if (entity.world.provider instanceof WorldProviderTrappist1D) {
						if (this.apply(entity) && WorldUtils.canSeeSky(new BlockPos(entity), world)) {
							entity.fallDistance = 0F;

						}
					}
				}
			}
		}
	}

	public static final Texture STORM_TEXTURE = new Texture(ExoInfo.MODID, "textures/enviroment/heavyrain.png");

	public boolean isStormActive (World world) {
		return true;
	}

	@Override
	public boolean isStormVisibleInBiome (Biome biome) {
		return true;
	}

	@Override
	public float getStormDownfallSpeed () {
		return 14.0F;
	}

	@Override
	public float getStormWindSpeed () {
		return 4.0F;
	}

	@Override
	public boolean doesLightingApply () {
		return false;
	}

	@Override
	public float getStormDirection () {
		return 90F;
	}

	@Override
	public Texture getStormTexture (World world, Biome biome) {
		return STORM_TEXTURE;
	}

	@Override
	public int getStormSize () {
		return 64;
	}

	@Override
	public boolean isStormApplicableTo (WorldProvider provider) {
		return provider instanceof WorldProviderTrappist1D;
	}

	@Override
	public boolean useGroundParticle () {
		return false;
	}
}
