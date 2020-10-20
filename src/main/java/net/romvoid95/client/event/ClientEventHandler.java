package net.romvoid95.client.event;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;

import net.romvoid95.api.space.prefab.ExoSystem;
import net.romvoid95.api.world.weather.MultiCloudProvider;
import net.romvoid95.client.gui.screen.GuiBeta;
import net.romvoid95.client.model.ModelUtil;
import net.romvoid95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.romvoid95.common.config.ConfigCore;
import net.romvoid95.common.constants.ModelNames;
import net.romvoid95.common.utility.mc.MCUtil;
import net.romvoid95.core.ExoInfo;
import net.romvoid95.core.initialization.SolarSystems;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public class ClientEventHandler {

	private List<String> groups = ImmutableList.of("Base");

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {

		for (ModelNames m : ModelNames.getModels()) {
			ModelUtil.replace(event, m.modelName(), m.objFile(), groups, m.modelClass(), TRSRTransformation.identity());
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void loadTextures(TextureStitchEvent.Pre event) {

		for (ModelNames m : ModelNames.getModels()) {
			ModelUtil.registerTexture(event, m.modelName());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onTick(ClientTickEvent e) {
		World world = FMLClientHandler.instance().getClient().world;
		
		if (world != null && !FMLClientHandler.instance().getClient().isGamePaused()) {
			if(world.provider.getCloudRenderer() instanceof MultiCloudProvider)
			{
				MultiCloudProvider clouds = (MultiCloudProvider) world.provider.getCloudRenderer();
				
				clouds.cloudTicksPrev = clouds.cloudTicks;
				clouds.cloudTicks += clouds.getCloudMovementSpeed(world);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent) {

		ExoSystem system;

		if (renderEvent.celestialBody.equals(SolarSystems.yzCeti.getMainStar())) {
			system = SolarSystems.yzCeti;
			this.RingRender(renderEvent, system);
		}
		if (renderEvent.celestialBody.equals(SolarSystems.wolf1061.getMainStar())) {
			system = SolarSystems.wolf1061;
			this.RingRender(renderEvent, system);
		}
		if (renderEvent.celestialBody.equals(SolarSystems.kepler1649.getMainStar())) {
			system = SolarSystems.kepler1649;
			this.RingRender(renderEvent, system);
		}
		if (renderEvent.celestialBody.equals(SolarSystems.trappist1.getMainStar())) {
			system = SolarSystems.trappist1;
			this.RingRender(renderEvent, system);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		GuiScreen gui = event.getGui();
		if (ConfigCore.warnBetaBuild && (gui instanceof GuiMainMenu)) {

			event.setGui(new GuiBeta((GuiMainMenu) gui));
			ConfigCore.warnBetaBuild = false;
			MinecraftForge.EVENT_BUS.post(
					new ConfigChangedEvent.OnConfigChangedEvent(ExoInfo.MODID, ExoInfo.NAME, false, false));
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onRenderFogDensity(EntityViewRenderEvent.FogDensity event) {
		if (event.getEntity().world.provider instanceof WorldProviderTrappist1D) {
			event.setDensity(0.09f);
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			event.setCanceled(true);
		}
	}

	public void RingRender(CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre renderEvent, ExoSystem solarSystem) {

		Vector3f mapPos = solarSystem.getMapPosition().toVector3f();

		float xX = mapPos.x;
		float yY = mapPos.y;

		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection) {
			GL11.glColor4f(0.0F, 0.9F, 0.1F, 0.5F);
		} else {
			GL11.glColor4f(0.3F, 0.1F, 0.1F, 0.0F);
		}

		renderEvent.setCanceled(true);

		final float theta = (float) ((2 * Math.PI) / 90);
		final float cos = (float) Math.cos(theta);
		final float sin = (float) Math.sin(theta);

		float min = solarSystem.getHabitableZoneStart();
		float max = solarSystem.getHabitableZoneEnd();

		float x = max;
		float y = 0;

		float temp;
		// OUTER LINE
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i < 90; i++) {

			GL11.glVertex2f(x + xX, y + yY);

			temp = x;
			x = (cos * x) - (sin * y);
			y = (sin * temp) + (cos * y);

		}

		GL11.glEnd();

		GL11.glBegin(GL11.GL_LINE_LOOP);
		x = min;
		y = 0;

		for (int i = 0; i < 90; i++) {
			GL11.glVertex2f(x + xX, y + yY);

			temp = x;
			x = (cos * x) - (sin * y);
			y = (sin * temp) + (cos * y);
		}
		GL11.glEnd();
		GL11.glColor4f(0.0F, 0.9F, 0.1F, 0.1F);

		GL11.glBegin(GL11.GL_QUADS);
		x = min;
		y = 0;
		float x2 = max;
		float y2 = 0;

		for (int i = 0; i < 90; i++) {

			GL11.glVertex2f(x2 + xX, y2 + yY);
			GL11.glVertex2f(x + xX, y + yY);

			temp = x;
			x = (cos * x) - (sin * y);
			y = (sin * temp) + (cos * y);
			temp = x2;
			x2 = (cos * x2) - (sin * y2);
			y2 = (sin * temp) + (cos * y2);

			GL11.glVertex2f(x + xX, y + yY);
			GL11.glVertex2f(x2 + xX, y2 + yY);
		}
		GL11.glEnd();

	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		World world = living.world;
		if (living instanceof EntityPlayerMP) {
			if((world.provider instanceof IGalacticraftWorldProvider) && MCUtil.isDeobfuscated()) {
				if(living.getActivePotionEffect(MobEffects.NIGHT_VISION) == null) {
					living.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 9999, 0, false, false));
				}
			}
		}
	}

	//	@SubscribeEvent
	//	@SideOnly(Side.CLIENT)
	//	public void onGetFogColour(FogColors event) {
	//		if (event.getEntity() instanceof EntityPlayer) {
	//			EntityPlayer player = (EntityPlayer) event.getEntity();
	//			Vec3d mixedColor;
	//			mixedColor = handleVision(player, event.getRed(), event.getGreen(), event.getBlue(),
	//					event.getRenderPartialTicks());
	//			event.setRed((float) mixedColor.x);
	//			event.setGreen((float) mixedColor.y);
	//			event.setBlue((float) mixedColor.z);
	//		}
	//	}
	//
	//	private Vec3d handleVision(EntityLivingBase player, float r, float g, float b, double renderPartialTicks) {
	//		if (player.isPotionActive(RegisterPotion.DEV_VISION)) {
	//			float brightness = 1 + (MathHelper.sin((float) (renderPartialTicks * Math.PI * 0.2f)) * 0.3f);
	//			float scale = 1 / r;
	//			scale = Math.min(scale, 1 / g);
	//			scale = Math.min(scale, 1 / b);
	//			r = (r * (1 - brightness)) + (r * scale * brightness);
	//			g = (g * (1 - brightness)) + (g * scale * brightness);
	//			b = (b * (1 - brightness)) + (b * scale * brightness);
	//		}
	//		if (Minecraft.getMinecraft().gameSettings.anaglyph) {
	//			float aR = ((r * 30) + (g * 59) + (b * 11)) / 100;
	//			float aG = ((r * 30) + (g * 70)) / 100;
	//			float aB = ((r * 30) + (b * 70)) / 100;
	//			r = aR;
	//			g = aG;
	//			b = aB;
	//		}
	//		return new Vec3d(r, g, b);
	//	}
}
