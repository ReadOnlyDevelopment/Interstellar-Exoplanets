package net.rom.exoplanets.astronomy.trappist1.c;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;
import net.rom.exoplanets.astronomy.trappist1.c.worldegnine.Trappist1C_Mountains;

public class BiomeDecoratorTrappist1C extends BiomeDecoratorSpace {

	private World currentWorld;

	private WorldGenerator dirtGen, gravelGen;

	public BiomeDecoratorTrappist1C() {

		dirtGen = new WorldGenMinableMeta(TrappistBlocks.SharedTerrain.HOT_GROUND_2, 30, 0, true, TrappistBlocks.TrappistC.T1C_TOP, 0);
		gravelGen = new WorldGenMinableMeta(TrappistBlocks.SharedTerrain.HOT_GROUND_1, 30, 0, true, TrappistBlocks.TrappistC.T1C_TOP, 0);
	}

	@Override
	protected void setCurrentWorld(World world) {
		this.currentWorld = world;
	}

	@Override
	protected World getCurrentWorld() {
		return this.currentWorld;
	}

	@Override
	protected void decorate() {

		this.generateOre(40, dirtGen, 5, 180);
		this.generateOre(60, gravelGen, 5, 180);
	}

	static class TrappistStonePredicate implements Predicate<IBlockState> {
		List<IBlockState> states = new ArrayList<IBlockState>();

		private TrappistStonePredicate() {
			states.add(TrappistBlocks.SharedTerrain.HOT_GROUND_1.getDefaultState());
		}

		public boolean apply(IBlockState state) {
			if (state != null && states.contains(state)) {
				return true;
			}
			return false;
		}
	}

}
