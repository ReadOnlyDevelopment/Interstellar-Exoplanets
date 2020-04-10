package net.rom.exoplanets.internal.block;

import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockExoCore extends BlockOre {
    private final Item droppedItem;
    private final int quantityMin, quantityMax;
    private final int xpMin, xpMax;

    public BlockExoCore(Item droppedItem, int harvestLevel, int quantityMin, int quantityMax, int xpMin, int xpMax) {
        this.droppedItem = droppedItem;
        this.quantityMin = quantityMin;
        this.quantityMax = quantityMax;
        this.xpMin = xpMin;
        this.xpMax = xpMax;

        setHardness(3f);
        setResistance(15f);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", harvestLevel);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return droppedItem;
    }

    @Override
    public int quantityDropped(Random random) {
        return quantityMin + random.nextInt(quantityMax - quantityMin + 1);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        int quantity = quantityDropped(random);
        if (fortune > 0 && getItemDropped(getDefaultState(), random, fortune) != Item.getItemFromBlock(this)) {
            float bonus = bonusAmount(fortune, random);
            if (bonus < 0) bonus = 0;
            quantity = Math.round(quantity * (bonus + 1));
        }
        return MathHelper.clamp(quantity, 0, 64);
    }

    public float bonusAmount(int fortune, Random random) {
        return random.nextInt(fortune + 2) - 1;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Item item = getItemDropped(world.getBlockState(pos), RANDOM, fortune);
        return item != Item.getItemFromBlock(this) ? xpMin + RANDOM.nextInt(xpMax - xpMin + 1) : 0;
    }
}
