package net.rom.exoplanets.content.block.ore;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.EnumMetal;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.client.ICustomModel;

public class BlockOreMetal extends BlockExoOre implements ICustomModel {
    public static final PropertyEnum<EnumMetal> METAL = PropertyEnum.create("metal", EnumMetal.class);

    public BlockOreMetal() {
        super(EnumMetal.values().length);
        setHardness(3.0f);
        setResistance(15.0f);
        setSoundType(SoundType.STONE);

        for (EnumMetal metal : EnumMetal.values()) {
            if (metal == EnumMetal.COPPER || metal == EnumMetal.TIN || metal == EnumMetal.ALUMINIUM) {
                setHarvestLevel("pickaxe", 1, getDefaultState().withProperty(METAL, metal));
            } else {
                setHarvestLevel("pickaxe", 2, getDefaultState().withProperty(METAL, metal));
            }
        }
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {
        for (EnumMetal metal : EnumMetal.values()) {
            ItemStack ore = new ItemStack(this, 1, metal.meta);
                ItemStack ingot = metal.getIngot();

                // Vanilla smelting
                    recipes.addSmelting(ore, ingot, 0.5f);
        }
    }

    @Override
    public void addOreDict() {
        for (EnumMetal metal : EnumMetal.values()) {
            ItemStack stack = new ItemStack(this, 1, metal.getMeta());
                OreDictionary.registerOre("ore" + metal.getMetalName(), stack);
                // Alternative spelling of aluminium
                if (metal == EnumMetal.ALUMINIUM)
                    OreDictionary.registerOre("oreAluminum", stack);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(METAL).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        Item item = Item.getItemFromBlock(this);
        for (EnumMetal metal : EnumMetal.values()) {
            ItemStack stack = new ItemStack(item, 1, metal.meta);
                list.add(stack);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(METAL, EnumMetal.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(METAL).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, METAL);
    }

    @Override
    public void registerModels() {
        Item item = Item.getItemFromBlock(this);
        for (EnumMetal metal : EnumMetal.values()) {
            ModelResourceLocation model = new ModelResourceLocation(ExoInfo.RESOURCE_PREFIX + "metalore", "metal=" + metal.getName());
            ModelLoader.setCustomModelResourceLocation(item, metal.getMeta(), model);
        }
    }
}
