package net.rom.exoplanets.client.render;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProvider;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;

public class BlockHandler {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void registerBlockColourHandlers(ColorHandlerEvent.Block event) {
		final BlockColors blockColors = event.getBlockColors();

		// Use the grass colour of the biome or the default grass colour
		final IBlockColor grassColourHandler = (state, blockAccess, pos, tintIndex) -> {

			if (blockAccess != null && pos != null) {
				World world = FMLClientHandler.instance().getWorldClient();

				if (world != null && world.provider instanceof WE_WorldProvider) {
					WE_ChunkProvider chunk = ((WE_WorldProvider) world.provider).chunk_provider;
					if (chunk != null)
						return WE_Biome.getBiomeAt(chunk, (long) pos.getX(), (long) pos.getZ()).biomeBlockGrassColor;
				}
				return BiomeColorHelper.getGrassColorAtPos(blockAccess, pos);
			}
			return ColorizerGrass.getGrassColor(0.5D, 1.0D);
		};

		blockColors.registerBlockColorHandler(grassColourHandler, Blocks.GRASS, TrappistBlocks.TrappistE.trap1e_grass);

		final IBlockColor waterColourHandler = (state, blockAccess, pos, tintIndex) -> {

			if (blockAccess != null && pos != null) {
				World world = FMLClientHandler.instance().getWorldClient();

				if (world != null && world.provider instanceof WE_WorldProvider) {
					WE_ChunkProvider chunk = ((WE_WorldProvider) world.provider).chunk_provider;

					if (chunk != null)
						return WE_Biome.getBiomeAt(chunk, (long) pos.getX(), (long) pos.getZ()).biomeBlockWaterColor;
				}

				return BiomeColorHelper.getWaterColorAtPos(blockAccess, pos);
			}

			return ColorizerGrass.getGrassColor(0.5D, 1.0D);
		};

		blockColors.registerBlockColorHandler(waterColourHandler, Blocks.WATER, Blocks.FLOWING_WATER);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void registerItemColourHandlers(ColorHandlerEvent.Item event) {
		final BlockColors blockColors = event.getBlockColors();
		final ItemColors itemColors = event.getItemColors();

		// Use the Block's colour handler for an ItemBlock
		final IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
			final IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
			return blockColors.colorMultiplier(state, null, null, tintIndex);
		};

		itemColors.registerItemColorHandler(itemBlockColourHandler, TrappistBlocks.TrappistE.trap1e_grass);

	}

}
