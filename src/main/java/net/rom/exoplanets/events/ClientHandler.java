package net.rom.exoplanets.events;

import java.io.File;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.client.render.ItemModelRocket;
import net.rom.exoplanets.client.screen.EnumScreenAnchor;
import net.rom.exoplanets.internal.MCUtil;
import net.rom.exoplanets.util.FileUtil;
import net.rom.exoplanets.util.RGB;

public class ClientHandler {

	public Minecraft mc = FMLClientHandler.instance().getClient();

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onModelBakeEvent(ModelBakeEvent event) {
		replaceModelDefault(event, "twopersonrocket", "twopersonrocket.obj", ImmutableList.of("Base"), ItemModelRocket.class, TRSRTransformation.identity());
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre event) {

		registerTexture(event, "rocket");

	}

	private static boolean isEpGuiEnabled() {
		return FileUtil.find(new File("ExtraPlanets.cfg"), "B:\"Use Custom Galaxy Map/Celestaial Selection Screen\"=true");
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	@SideOnly(Side.CLIENT)
	public void onRenderTick(RenderTickEvent event) {
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;

		if (event.phase == Phase.END) {
			if (player != null) {

				if (minecraft.inGameHasFocus && !minecraft.gameSettings.hideGUI && MCUtil.isDeobfuscated()) {

					long t1 = player.world.provider instanceof WorldProviderSpace ? ((WorldProviderSpace) player.world.provider).getDayLength() : 24000;
					long time = player.world.getWorldTime() % (t1 > 0 ? t1 : 1);

					float temp = player.world.provider instanceof IGalacticraftWorldProvider ? ((IGalacticraftWorldProvider) player.world.provider).getCelestialBody().atmosphere.thermalLevel() : 1.0F;

					String dev = "ReadOnlyDev | Developer Environment";
					String version = ExoInfo.NAME + " " + ExoInfo.VERSION;

        			String[] s = { 
        					"Minecraft Version: 1.12.2",
        					"Celestial Body: " + ((player.getEntityWorld().provider instanceof IGalacticraftWorldProvider) ? ((IGalacticraftWorldProvider)player.getEntityWorld().provider).getCelestialBody().getLocalizedName() : "Overworld"),
        					"",
        					"Player Data:",
        					"X: " + (int) player.posX + " " + "Y: " + (int) player.posY + " " + "Z: " + (int) player.posZ,
        					"Current Item: " + (player.inventory.getCurrentItem() != null ? Item.REGISTRY.getNameForObject(player.inventory.getCurrentItem().getItem()) + ":" +  player.inventory.getCurrentItem().getItemDamage(): "None"),
        					
        					"",
        					"World Data:",
        					"Dimension: " + player.world.provider.getDimensionType().getName() + " (ID: " + player.world.provider.getDimensionType().getId() + ")",
        					"Temperature: " + temp + "F",
        					"Biome: " + player.world.getBiomeForCoordsBody(new BlockPos((int)player.posX, (int)player.posY, (int)player.posZ)).getBiomeName(),
        					"Current Time: " + time + " | Day Length: " + (player.world.provider instanceof WorldProviderSpace ? ((WorldProviderSpace) player.world.provider).getDayLength() : "24000") + " | Total Time: " + player.world.getWorldTime(),
        					"Moon Phase: " + player.world.getMoonPhase(),
        					"Chunk Pos: x" + player.getEntityWorld().getChunkFromBlockCoords(player.getPosition()).x + " z" + player.getEntityWorld().getChunkFromBlockCoords(player.getPosition()).z,
        					"",
        					"Is Galacticraft Provider: " + ((player.getEntityWorld().provider instanceof IGalacticraftWorldProvider) ? "Yes" : "No")
        					
        			};

					int center = EnumScreenAnchor.TOP_CENTER.getX(mc.displayWidth / 2);
					GL11.glPushMatrix();
					minecraft.fontRenderer.drawStringWithShadow(dev, center - 75, 10, RGB.GOLD.getColor());
					minecraft.fontRenderer.drawStringWithShadow(version, center - 59, 20, RGB.LIME.getColor());
					for (int i = 0; i < s.length; i++)
						minecraft.fontRenderer.drawStringWithShadow(s[i], 10, 28 + i * 10, ColorUtil.to32BitColor(255, 255, 255, 255));
					GL11.glPopMatrix();

				}
			}
		}
	}

	public void registerTexture(TextureStitchEvent.Pre event, String texture) {
		event.getMap().registerSprite(new ResourceLocation(ExoInfo.MODID, "model/" + texture));
	}

	private void replaceModelDefault(ModelBakeEvent event, String resLoc, String objLoc, List<String> visibleGroups, Class<? extends ModelTransformWrapper> clazz, IModelState parentState, String... variants) {
		MCUtil.replaceModel(ExoInfo.MODID, event, resLoc, objLoc, visibleGroups, clazz, parentState, variants);
	}

}
