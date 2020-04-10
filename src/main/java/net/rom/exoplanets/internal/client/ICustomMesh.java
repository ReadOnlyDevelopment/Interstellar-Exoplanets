package net.rom.exoplanets.internal.client;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomMesh {

	@SideOnly(Side.CLIENT)
	default ResourceLocation[] getVariants() {
		return new ModelResourceLocation[0];
	}

	@SideOnly(Side.CLIENT)
	ItemMeshDefinition getCustomMesh();
}
