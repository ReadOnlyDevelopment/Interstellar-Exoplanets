package net.rom.exoplanets.block.decoration;

import java.util.Locale;

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
import net.rom.exoplanets.Exoplanets;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.internal.client.ICustomModel;
import net.rom.exoplanets.internal.item.ItemBlockMetaSubtypes;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockDeco extends BlockMetaSubtypes implements ICustomModel {

	    public enum Type implements IStringSerializable {
	    	GRATING, GRATING_STRIPE;

	        @Override
	        public String getName() {
	            return name().toLowerCase(Locale.ROOT);
	        }

	        public int getMetadata() {
	            return ordinal();
	        }
	    }
	    private static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

	    public BlockDeco() {
	        super(Material.IRON, Type.values().length);
	        setHardness(3.0f);
	        setResistance(30.0f);
	        setDefaultState(blockState.getBaseState().withProperty(VARIANT, Type.GRATING));
			setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	    }

	    public ItemStack getStack(Type type, int count) {
	        return new ItemStack(this, count, type.ordinal());
	    }

	    @Override
	    public IBlockState getStateFromMeta(int meta) {

	        meta = MathHelper.clamp(meta, 0, Type.values().length - 1);
	        return this.getDefaultState().withProperty(VARIANT, Type.values()[meta]);
	    }

	    @Override
	    public int getMetaFromState(IBlockState state) {
	        return state.getValue(VARIANT).ordinal();
	    }

	    @Override
	    protected BlockStateContainer createBlockState() {
	        return new BlockStateContainer(this, VARIANT);
	    }

	    @Override
	    public void registerModels() {
	        Item item = Item.getItemFromBlock(this);
	        String fullName = Exoplanets.RESOURCE_PREFIX + "deco";
	        for (Type type : Type.values()) {
	            ModelResourceLocation model = new ModelResourceLocation(fullName, "type=" + type.getName());
	            ModelLoader.setCustomModelResourceLocation(item, type.ordinal(), model);
	        }
	    }

	    public static class ItemBlock extends ItemBlockMetaSubtypes {
	        public ItemBlock(BlockDeco block) {
	            super(block);
	        }
	    }

}
