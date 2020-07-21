package net.rom.exoplanets.content.block.ore;

import java.util.Locale;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.block.BlockExoOre;
import net.rom.exoplanets.content.block.BlockGeneral;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.inerf.ICustomModel;
import net.rom.exoplanets.internal.inerf.item.ItemBlockMetaSubtypes;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockOrePlanet extends BlockGeneral {
	
	public BlockOrePlanet(int level) {
		super(Material.IRON);
        this.setHardness(3.0f);
        this.setResistance(30.0f);
        this.setHarvestLevel("pickaxe", level);
        this.setSoundType(SoundType.STONE);
		this.setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	}
	
    public ItemStack getStack(int count) {
        return new ItemStack(this, count);
    }

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		// TODO Auto-generated method stub
		
	}


}
