package net.romvoid95.api.content.multiblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.crafting.IngredientStack;

public interface IMultiBlock {
	/**
	 * returns name of the Multiblock. This is used for the interdiction NBT system on the hammer, so this name /must/ be unique.
	 */
	String getName ();

	/**
	 * Check whether the given block can be used to trigger the structure creation of the multiblock.<br>
	 * Basically, a less resource-intensive preliminary check to avoid checking every structure.
	 */
	boolean isBlockTrigger (IBlockState state);

	/**
	 * This method checks the structure and sets the new one.
	 *
	 * @return if the structure was valid and transformed
	 */
	boolean createStructure (World world, BlockPos pos, EnumFacing side, EntityPlayer player);

	/**
	 * A three-dimensional array (height, length, width) of the structure to be rendered in the Engineers Manual
	 */
	ItemStack[][][] getStructureManual ();

	default IBlockState getBlockstateFromStack (int index, ItemStack stack) {
		if (!stack.isEmpty() && stack.getItem() instanceof ItemBlock)
			return ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getItemDamage());
		return null;
	}

	/**
	 * An array of ItemStacks that summarizes the total amount of materials needed for the structure. Will be rendered in the Engineer's Manual
	 */
	IngredientStack[] getTotalMaterials ();

	/**
	 * Use this to overwrite the rendering of a Multiblock's Component
	 */
	@SideOnly(Side.CLIENT)
	boolean overwriteBlockRender (ItemStack stack, int iterator);

	/**
	 * returns the scale modifier to be applied when rendering the structure in the IE manual
	 */
	float getManualScale ();

	/**
	 * returns true to add a button that will switch between the assembly of multiblocks and the finished render
	 */
	@SideOnly(Side.CLIENT)
	boolean canRenderFormedStructure ();

	/**
	 * use this function to render the complete multiblock
	 */
	@SideOnly(Side.CLIENT)
	void renderFormedStructure ();
}
