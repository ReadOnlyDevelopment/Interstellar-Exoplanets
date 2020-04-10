package net.rom.exoplanets.client;

import java.util.Set;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.exoplanets.Exoplanets;
import net.rom.exoplanets.conf.SConfigCore;
import net.rom.exoplanets.conf.SConfigDimensionID;
import net.rom.exoplanets.conf.SConfigSystems;

public class ExoplanetsConfigGuiFactory implements IModGuiFactory {

	public static class ExoCoreConfGui extends GuiConfig {

		public ExoCoreConfGui(GuiScreen parent) {
			super(parent, SConfigCore.getConfigElements(), Exoplanets.MODID, false, true,
					GCCoreUtil.translate("exoplanets.configgui.coretitle"));
			this.configElements.addAll(SConfigDimensionID.getConfigElements());
			this.configElements.addAll(SConfigSystems.getConfigElements());
		}
	}

	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ExoCoreConfGui(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

}
