package net.rom.exoplanets.content.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.api.research.ResearchStack;

public class ItemResearchPaper extends ItemBase {
	
    public ItemResearchPaper(String name) {
        super(name);
    }

    public ResearchStack getResearchStack(ItemStack itemStack) {
        if (itemStack.getTagCompound() != null) {
            return ResearchStack.loadFromNBT(itemStack.getTagCompound());
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void addDetails(ItemStack itemstack, EntityPlayer player, @Nullable World worldIn, List<String> infos) {
    	// TODO
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
    	// TODO
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        if (itemStack.getTagCompound() != null) {
        	ResearchStack questStack = ResearchStack.loadFromNBT(itemStack.getTagCompound());
            return questStack.getTitle();
        }
        return super.getItemStackDisplayName(itemStack);
    }

    @SuppressWarnings("null")
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn = playerIn.getHeldItem(handIn);
        if (worldIn.isRemote) {
            openGui(itemStackIn);
            return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
        } else {
            ResearchStack researchStack = getResearchStack(itemStackIn);
            if (researchStack == null) {
            	// TODO
                NBTTagCompound questTag = new NBTTagCompound();
                researchStack.writeToNBT(questTag);
                itemStackIn.setTagCompound(questTag);
                return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
            }
        }
        return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
    }

    @SuppressWarnings("unused")
	@SideOnly(Side.CLIENT)
    private void openGui(ItemStack stack) {
    	ResearchStack questStack = getResearchStack(stack);
        	// TODO
    }
}
