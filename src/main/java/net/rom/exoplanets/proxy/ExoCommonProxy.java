package net.rom.exoplanets.proxy;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rom.core.autoreg.StellarRegistry;
import net.rom.core.proxy.IProxy;
import net.rom.exoplanets.fluids.IFluid;

public class ExoCommonProxy implements IProxy {
	
	private static List<IFluid> modelRegisters = new ArrayList();

	@Override
	public void preInit(StellarRegistry registry, FMLPreInitializationEvent event) {
		registry.preInit(event);		
	}

	@Override
	public void init(StellarRegistry registry, FMLInitializationEvent event) {
		registry.init(event);
		
	}

	@Override
	public void postInit(StellarRegistry registry, FMLPostInitializationEvent event) {
		registry.postInit(event);
		
	}
	
    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }
	
    public boolean isClientPlayerHoldingDebugItem() {
		return false;
//        EntityPlayer player = getClientPlayer();
//        if (player == null) {
//            return false;
//        }
//
//        ItemStack mainhand = player.getHeldItemMainhand();
//        ItemStack offhand = player.getHeldItemOffhand();
//        return mainhand.getItem() == ModItems.debugItem || offhand.getItem() == ModItems.debugItem;
    }
    
    public boolean addIModelRegister(IFluid model) {
		return false;
    	
    }
}
