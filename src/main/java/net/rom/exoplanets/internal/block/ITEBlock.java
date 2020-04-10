package net.rom.exoplanets.internal.block;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ITEBlock {

    /**
     * Gets the class of the tile entity associated with this block
     *
     * @return The TileEntity class
     */
    Class<? extends TileEntity> getTileEntityClass();

    /**
     * Gets a TESR for the tile entity, or null if it does not have one
     *
     * @return The TESR to bind, or null if nothing should be done.
     */
    @SideOnly(Side.CLIENT)
    @Nullable
    default TileEntitySpecialRenderer<?> getTileRenderer() {
        return null;
    }
}
