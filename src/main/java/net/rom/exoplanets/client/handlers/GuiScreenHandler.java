package net.rom.exoplanets.client.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.client.gui.GuiBeta;
import net.rom.exoplanets.conf.SConfigCore;;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public class GuiScreenHandler {

    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {
        GuiScreen gui = event.getGui();

        if (SConfigCore.warnBetaBuild && gui instanceof GuiMainMenu) {
            event.setGui(new GuiBeta((GuiMainMenu) gui));
            SConfigCore.warnBetaBuild = false;
            MinecraftForge.EVENT_BUS.post(new ConfigChangedEvent.OnConfigChangedEvent(ExoInfo.MODID, ExoInfo.NAME, false, false));
        }
    }
}