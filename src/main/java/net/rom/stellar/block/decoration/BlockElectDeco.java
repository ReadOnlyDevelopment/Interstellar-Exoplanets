package net.rom.stellar.block.decoration;

import java.util.Locale;

import io.netty.handler.codec.http.HttpHeaders.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.rom.core.block.BlockMetaSubtypes;
import net.rom.core.client.ICustomModel;
import net.rom.core.items.ItemBlockMetaSubtypes;
import net.rom.stellar.Exoplanets;

public class BlockElectDeco extends BlockMetaSubtypes implements ICustomModel {

	    public enum Type implements IStringSerializable {
	    	RAID_0, RAID_1 , RAID_ARRAY_STACK, COMMUNICATION_RELAY, SYSTEM_CONTROLLER, ELECTRONIC_SCREEN;

	        @Override
	        public String getName() {
	            return name().toLowerCase(Locale.ROOT);
	        }

	        public int getMetadata() {
	            return ordinal();
	        }
	    }
	    private static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

	    public BlockElectDeco() {
	        super(Material.IRON, Type.values().length);
	        setHardness(3.0f);
	        setResistance(30.0f);
	        setDefaultState(blockState.getBaseState().withProperty(VARIANT, Type.RAID_0));
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
	        String fullName = Exoplanets.RESOURCE_PREFIX + "electricdeco";
	        for (Type type : Type.values()) {
	            ModelResourceLocation model = new ModelResourceLocation(fullName, "type=" + type.getName());
	            ModelLoader.setCustomModelResourceLocation(item, type.ordinal(), model);
	        }
	    }

	    public static class ItemBlock extends ItemBlockMetaSubtypes {
	        public ItemBlock(BlockElectDeco block) {
	            super(block);
	        }
	    }

}
