package net.rom.exoplanets.internal.block;

import net.minecraft.block.material.Material;
import net.rom.exoplanets.internal.IAddRecipe;

public class BlockExoOre extends BlockMetaSubtypes implements IAddRecipe {

    public final int maxMeta;

    public BlockExoOre(int subBlockCount) {
        super(Material.ROCK, subBlockCount);
        this.maxMeta = subBlockCount;
    }
}
