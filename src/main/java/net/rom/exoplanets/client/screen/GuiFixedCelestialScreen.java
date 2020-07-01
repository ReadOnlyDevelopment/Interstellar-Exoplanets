package net.rom.exoplanets.client.screen;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import asmodeuscore.AsmodeusCore;
import asmodeuscore.api.dimension.IAdvancedSpace;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.api.space.IExBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import asmodeuscore.core.configs.AsmodeusConfig;
import asmodeuscore.core.utils.Utils;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.recipe.SpaceStationRecipe;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.api.world.SpaceStationType;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiTeleporting;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.rom.api.space.ExoRegistry;
import net.rom.api.space.RelayStation;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.compat.PlanetProgressionCompat;

public class GuiFixedCelestialScreen extends GuiCelestialSelection {

	protected enum EnumView {
		PREVIEW,
		PROFILE,
		GS
	}

	protected EnumView viewState = EnumView.GS;

	// GS ADDS
	private String				galaxy			= GalacticraftCore.planetOverworld.getParentSolarSystem().getUnlocalizedParentGalaxyName();
	private List<String>		galaxylist		= new ArrayList<String>();
	private List<SolarSystem>	starlist		= new ArrayList<SolarSystem>();
	private boolean				showStarList	= false;
	private boolean				showGalaxyList	= false;

	private double	isometx	= AsmodeusConfig.enable2DGalaxyMap ? 0 : 55;
	private double	isometz	= 45;

	private double mindistance = 900.0;

	private boolean	hideInfo	= false;
	private int		currenttier	= 0;
	private int		tierneed	= -1;

	private boolean canTravel = true;

	private boolean		small_mode	= Minecraft.getMinecraft().gameSettings.guiScale == 0;
	private int			small_page	= 0;

	private int	fuelSet			= 0;
	private int	fuelRocketLevel	= 0;

	protected static final int	LIGHTBLUE	= ColorUtil.to32BitColor(255, 0, 255, 255);
	protected static final int	YELLOW		= ColorUtil.to32BitColor(255, 255, 255, 0);

	public static ResourceLocation guiMain2 = new ResourceLocation(AsmodeusCore.ASSET_PREFIX, "textures/gui/celestialselection2.png");

	public static ResourceLocation guiImg = new ResourceLocation(AsmodeusCore.ASSET_PREFIX, "textures/gui/galaxymap_1.png");

	private double	xImgOffset	= 0, yImgOffset = 0;
	private int		scroll		= 0, max_scroll = 8;

	private Vector3	nebula_color	= new Vector3(1.0F, 1.0F, 1.0F);
	private int		nebula_img		= 0;

	public GuiFixedCelestialScreen(boolean mapMode, List<CelestialBody> possibleBodies, boolean canCreateStations) {
		super(mapMode, possibleBodies, canCreateStations);

		int tier = 0;
		this.currenttier = tier;

		for (SolarSystem solarSystem : GalaxyRegistry.getRegisteredSolarSystems().values()) {
			if (!galaxylist.contains(solarSystem.getUnlocalizedParentGalaxyName()))
				galaxylist.add(solarSystem.getUnlocalizedParentGalaxyName());

			if (solarSystem.getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				starlist.add(solarSystem);
			}
		}

		Collections.sort(starlist, new Comparator<SolarSystem>() {
			@Override
			public int compare(SolarSystem lhs, SolarSystem rhs) {
				// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				return lhs.getName().compareTo(rhs.getName());
			}
		});

		Random rand = new Random();
		nebula_img = rand.nextInt(2);
		nebula_color = new Vector3(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());

	}

	@Override
	public void initGui() {
		GuiCelestialSelection.BORDER_SIZE = this.width / 65;
		GuiCelestialSelection.BORDER_EDGE_SIZE = GuiCelestialSelection.BORDER_SIZE / 4;

		refreshBodies();
	}

	private void refreshBodies() {

		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;

		bodiesToRender.clear();

		for (SolarSystem solarSystem : GalaxyRegistry.getRegisteredSolarSystems().values()) {
			if (solarSystem.getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				bodiesToRender.add(solarSystem.getMainStar());
			}
		}

		for (Planet planet : GalaxyRegistry.getRegisteredPlanets().values()) {
			if (planet.getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (PlanetProgressionCompat.isReasearched(player, planet)) {
						this.bodiesToRender.add(planet);
					}
				} else {
					this.bodiesToRender.add(planet);
				}
			}

		}

		for (Moon moon : GalaxyRegistry.getRegisteredMoons().values()) {
			if (moon.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (PlanetProgressionCompat.isReasearched(player, moon.getParentPlanet()) && PlanetProgressionCompat.isReasearched(player, moon)) {
						this.bodiesToRender.add(moon);
					}
				} else {
					this.bodiesToRender.add(moon);
				}
			}
		}

		for (Satellite satellite : GalaxyRegistry.getRegisteredSatellites().values()) {
			if (satellite.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (PlanetProgressionCompat.isReasearched(player, satellite.getParentPlanet())) {
						this.bodiesToRender.add(satellite);
					}
				} else {
					this.bodiesToRender.add(satellite);
				}
			}
		}

		for (RelayStation relayStation : ExoRegistry.getRegisteredRelayStations().values()) {
			if (relayStation.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy))
				bodiesToRender.add(relayStation);
		}
	}

	@Override
	public void drawButtons(int mousePosX, int mousePosY) {

		if (this.selectedBody != null) {
			double dist = 0;
			float idcel = 0;
			SolarSystem star = GalacticraftCore.solarSystemSol;
			this.canTravel = true;

			if (this.selectedBody instanceof Planet) {
				if (!(this.mc.player.world.provider instanceof WorldProviderSurface) && this.mc.player.world.provider instanceof IGalacticraftWorldProvider) {
					CelestialBody body = ((IGalacticraftWorldProvider) this.mc.player.world.provider).getCelestialBody();

					if (body instanceof Planet) {
						star = ((Planet) body).getParentSolarSystem();
						idcel = body.getRelativeDistanceFromCenter().unScaledDistance;
						dist = (Math.abs(Math.floor((idcel - this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100;

					}
					if (body instanceof RelayStation) {
						star = ((RelayStation) body).getParentPlanet().getParentSolarSystem();
						idcel = ((RelayStation) body).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance;
						dist = (Math.abs(Math.floor((idcel - this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100 + (body.getRelativeDistanceFromCenter().unScaledDistance / 100);

					}
					if (body instanceof IChildBody) {
						star = ((IChildBody) body).getParentPlanet().getParentSolarSystem();
						idcel = ((IChildBody) body).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance;
						dist = (Math.abs(Math.floor((idcel - this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100 + (body.getRelativeDistanceFromCenter().unScaledDistance / 100);

					}
				} else {
					idcel = GalacticraftCore.planetOverworld.getRelativeDistanceFromCenter().unScaledDistance;
					star = GalacticraftCore.solarSystemSol;
					dist = (Math.abs(Math.floor((idcel - this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100;

				}

				int fuelcount = (int) Math.round(this.fuelRocketLevel - (this.fuelRocketLevel * (dist / this.currenttier)));
				this.fuelSet = fuelcount;
			}

			if (this.selectedBody instanceof IChildBody) {
				if (!(this.mc.player.world.provider instanceof WorldProviderSurface) && this.mc.player.world.provider instanceof IGalacticraftWorldProvider) {
					CelestialBody body = ((WorldProviderSpace) this.mc.player.world.provider).getCelestialBody();
					idcel = 0;
					if (body instanceof Planet) {
						star = ((Planet) body).getParentSolarSystem();
						idcel = body.getRelativeDistanceFromCenter().unScaledDistance;

						dist = ((Math.abs(Math.floor((idcel - ((IChildBody) this.selectedBody).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100) + this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance / 100;
					} else if (body instanceof RelayStation){
						star = ((RelayStation) body).getParentPlanet().getParentSolarSystem();
						idcel = ((RelayStation) body).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance;

						dist = ((Math.abs(Math.floor((idcel - ((IChildBody) this.selectedBody).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100) + this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance / 100;
						
					} else if (body instanceof IChildBody) {
						star = ((IChildBody) body).getParentPlanet().getParentSolarSystem();

						idcel = ((IChildBody) body).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance;

						dist = ((Math.abs(Math.floor((idcel - ((IChildBody) this.selectedBody).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100) + this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance / 100;

					}
				} else {
					idcel = GalacticraftCore.planetOverworld.getRelativeDistanceFromCenter().unScaledDistance;
					star = GalacticraftCore.solarSystemSol;
					dist = ((Math.abs(Math.floor((idcel - ((IChildBody) this.selectedBody).getParentPlanet().getRelativeDistanceFromCenter().unScaledDistance) * 100))) / 100) + this.selectedBody.getRelativeDistanceFromCenter().unScaledDistance / 100;

				}

				int fuelcount = (int) Math.round(this.fuelRocketLevel - (this.fuelRocketLevel * (dist / this.currenttier)));
				this.fuelSet = fuelcount;
			}

			if (this.fuelSet < 0)
				canTravel = false;

			if (this.mc.player.capabilities.isCreativeMode)
				canTravel = true;
		}

		this.zLevel = 0.0F;
		boolean handledSliderPos = false;

		final int LHS = GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE;
		final int RHS = width - LHS;
		final int TOP = LHS;
		final int BOT = height - LHS;

		if (this.viewState == EnumView.GS) {
			String str;
			// Catalog:
			this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
			GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
			this.drawTexturedModalRect(LHS, TOP, 74, 11, 0, 392, 148, 22, false, false);
			str = GCCoreUtil.translate("gui.message.catalog.name").toUpperCase();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.fontRenderer.drawString(str, LHS + 40 - fontRenderer.getStringWidth(str) / 2, TOP + 1, WHITE);

			int scale = (int) Math.min(95, this.ticksSinceMenuOpenF * 12.0F);
			boolean planetZoomedNotMoon = this.isZoomed() && !(this.selectedParent instanceof Planet);

			// Parent frame:
			GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
			this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
			this.drawTexturedModalRect(LHS - 95 + scale, TOP + 12, 95, 41, 0, 436, 95, 41, false, false);

			GL11.glColor4f(1, 1, 0, 1);
			if (GalaxyRegistry.getRegisteredSolarSystems().size() > 1)
				this.drawTexturedModalRect(LHS + 2 - 13 + scale, TOP + 37, 5, 5, 110, 448, 5, 5, false, true);
			GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);

			str = planetZoomedNotMoon ? this.selectedBody.getLocalizedName() : this.getParentName();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.fontRenderer.drawString(str, LHS + 9 - 95 + scale, TOP + 34, YELLOW);
			GL11.glColor4f(1, 1, 0, 1);
			this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);

			// Grandparent frame:
			this.drawTexturedModalRect(LHS + 2 - 95 + scale, TOP + 14, 93, 17, 95, 436, 93, 17, false, false);
			if (galaxylist.size() > 1)
				this.drawTexturedModalRect(LHS + 2 - 13 + scale, TOP + 19, 5, 5, 110, 448, 5, 5, false, true);

			str = planetZoomedNotMoon ? this.getParentName() : this.getGrandparentName();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.fontRenderer.drawString(str, LHS + 7 - 95 + scale, TOP + 16, LIGHTBLUE);
			GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);

			if (this.showGalaxyList) {
				int y = 19;
				for (int i = 0; i < galaxylist.size(); i++) {
					ResourceLocation galaxy;

					if (BodiesHelper.getGalaxy(galaxylist.get(i).replace("galaxy.", "")) == null || BodiesHelper.getGalaxy(galaxylist.get(i).replace("galaxy.", "")).getImage() == null)
						galaxy = new ResourceLocation(AsmodeusCore.ASSET_PREFIX + ":" + "textures/gui/galaxy/" + galaxylist.get(i).toLowerCase() + ".png");
					else
						galaxy = BodiesHelper.getGalaxy(galaxylist.get(i).replace("galaxy.", "")).getImage();

					if (galaxy != null) {
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						this.mc.renderEngine.bindTexture(galaxy);
						this.drawTexturedModalRect(LHS + 18 + scale + 70 * i, TOP + 4, 37, 40, 0, 0, 100, 100, false, false, 100, 100);
					}

					this.mc.renderEngine.bindTexture(guiMain2);
					GL11.glColor4f(0.0F, 0.6F, 1.0F, 1.0F);
					this.drawTexturedModalRect(LHS + 15 + scale + 70 * i, TOP + 4, 43, 40, 270, 80, 85, 75, false, false);

					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
					GL11.glColor4f(0.0F, 0.6F, 1.0F, scale / 95.0F);
					this.drawTexturedModalRect(LHS + 5 + scale + 70 * i, TOP + 14 + 35, 43, 12, 95, 464, 43, 12, false, false);
					this.drawTexturedModalRect(LHS + 5 + scale + 43 + 70 * i, TOP + 14 + 35, 23, 12, 165, 464, 23, 12, false, false);

					GlStateManager.color(1, 1, 1, 1);

					str = GCCoreUtil.translate(galaxylist.get(i));
					this.fontRenderer.drawString(str, LHS + 10 + scale + 70 * i, TOP + 14 + 37, YELLOW);

				}
			}
			if (this.showStarList) {
				int l = 0;
				for (SolarSystem s : starlist) {
					int y = 33;

					this.mc.renderEngine.bindTexture(guiMain2);
					GL11.glColor4f(0.0F, 0.6F, 1.0F, scale / 95.0F);
					this.drawTexturedModalRect(LHS + 3 + scale, TOP + 30 * l + 8, 140, 30, 275, 160, 140, 30, false, false);
					GlStateManager.color(1, 1, 1, 1);
					ResourceLocation star = s.getMainStar().getBodyIcon();

					this.mc.renderEngine.bindTexture(star);
					this.drawTexturedModalRect(LHS + 14 + scale, TOP + 30 * l + 15, 16, 16, 0, 0, 16, 16, false, false, 16, 16);

					str = s.getLocalizedName();
					this.fontRenderer.drawString(str, LHS + 43 + scale, TOP + 30 * l + 19, YELLOW);
					l++;
				}
			}
			List<CelestialBody> children = this.getChildren(planetZoomedNotMoon ? this.selectedBody : this.selectedParent, scroll, max_scroll);
			drawChildren(children, 0, 0, true);

			if (this.mapMode) {
				this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
				GL11.glColor4f(1.0F, 0.0F, 0.0F, 1);
				this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
				this.drawTexturedModalRect(RHS - 74, TOP, 74, 11, 0, 392, 148, 22, true, false);
				str = GCCoreUtil.translate("gui.message.exit.name").toUpperCase();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				this.fontRenderer.drawString(str, RHS - 40 - fontRenderer.getStringWidth(str) / 2, TOP + 1, WHITE);
			}

			int menuTopLeft1 = BOT - 22;
			int posX1 = LHS - 53;

			// TODO: HIDE BUTTON
			GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
			this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
			this.drawTexturedModalRect(posX1 + 53, menuTopLeft1 - 5, 40, 12, 0, 456, 40, 12, false, false);
			this.drawTexturedModalRect(posX1 + 93, menuTopLeft1 - 5, 45, 12, 50, 456, 45, 12, false, false);
			str = this.hideInfo ? "Enable Info" : "Disable Info";
			GL11.glColor4f(1, 1, 0, 1);
			this.fontRenderer.drawString(str, LHS + 15, menuTopLeft1 - 3, ColorUtil.to32BitColor(255, 255, 255, 0));

			/////////////////
			// TODO: MODE BUTTON
			GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
			this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
			this.drawTexturedModalRect(posX1 + 53, menuTopLeft1 - 19, 40, 12, 0, 456, 40, 12, false, false);
			this.drawTexturedModalRect(posX1 + 93, menuTopLeft1 - 19, 45, 12, 50, 456, 45, 12, false, false);
			str = this.small_mode ? "Small Mode" : "Normal Mode";
			GL11.glColor4f(1, 1, 0, 1);
			this.fontRenderer.drawString(str, LHS + 15, menuTopLeft1 - 17, ColorUtil.to32BitColor(255, 255, 255, 0));

			/////////////////
			if (this.selectedBody != null) {
				// Right-hand bar (basic selectionState info)
				this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain1);
				GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);

				if (this.selectedBody instanceof Satellite) {
					Satellite selectedSatellite = (Satellite) this.selectedBody;
					int stationListSize = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).size();

					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain1);
					int max = Math.min((this.height / 2) / 14, stationListSize);
					this.drawTexturedModalRect(RHS - 95, TOP, 95, 53, this.selectedStationOwner.length() == 0 ? 95 : 0, 186, 95, 53, false, false);
					if (this.spaceStationListOffset <= 0) {
						GL11.glColor4f(0.65F, 0.65F, 0.65F, 1);
					} else {
						GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
					}
					this.drawTexturedModalRect(RHS - 85, TOP + 45, 61, 4, 0, 239, 61, 4, false, false);
					if (max + spaceStationListOffset >= stationListSize) {
						GL11.glColor4f(0.65F, 0.65F, 0.65F, 1);
					} else {
						GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
					}
					this.drawTexturedModalRect(RHS - 85, TOP + 49 + max * 14, 61, 4, 0, 239, 61, 4, false, true);
					GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);

					if (this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).get(this.selectedStationOwner) == null) {
						str = GCCoreUtil.translate("gui.message.select_ss.name");
						this.drawSplitString(str, RHS - 47, TOP + 20, 91, WHITE, false, false);
					} else {
						str = GCCoreUtil.translate("gui.message.ss_owner.name");
						this.fontRenderer.drawString(str, RHS - 85, TOP + 18, WHITE);
						str = this.selectedStationOwner;
						this.fontRenderer.drawString(str, RHS - 47 - this.fontRenderer.getStringWidth(str) / 2, TOP + 30, WHITE);
					}

					Iterator<Map.Entry<String, StationDataGUI>> it = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).entrySet().iterator();
					int i = 0;
					int j = 0;
					while (it.hasNext() && i < max) {
						Map.Entry<String, StationDataGUI> e = it.next();

						if (j >= this.spaceStationListOffset) {
							this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
							GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
							int xOffset = 0;

							if (e.getKey().equalsIgnoreCase(this.selectedStationOwner)) {
								xOffset -= 5;
							}

							this.drawTexturedModalRect(RHS - 95 + xOffset, TOP + 50 + i * 14, 93, 12, 95, 464, 93, 12, true, false);
							str = "";
							String str0 = e.getValue().getStationName();
							int point = 0;
							while (this.fontRenderer.getStringWidth(str) < 80 && point < str0.length()) {
								str = str + str0.substring(point, point + 1);
								point++;
							}
							if (this.fontRenderer.getStringWidth(str) >= 80) {
								str = str.substring(0, str.length() - 3);
								str = str + "...";
							}
							this.fontRenderer.drawString(str, RHS - 88 + xOffset, TOP + 52 + i * 14, WHITE);
							i++;
						}
						j++;
					}
				} else if (!this.hideInfo) {
					this.mc.renderEngine.bindTexture(GuiFixedCelestialScreen.guiMain2);

					GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
					int y = 100;
					int sliderPos = this.zoomTooltipPos;
					if (zoomTooltipPos != 125) {
						sliderPos = (int) Math.min(this.ticksSinceSelectionF * 8, 125);
						this.zoomTooltipPos = sliderPos;
					}

					int menuTopLeft = LHS + y;

					int posX = RHS - sliderPos;
					int posX2 = (int) (RHS - 18);
					if (!this.selectedBody.getReachable()) {
						this.drawTexturedModalRect(posX - 7, menuTopLeft + 12, 133, 31, 0, 0 + 79, 266, 62, true, false);
						this.drawTexturedModalRect(posX - 7, menuTopLeft + 43, 133, 37, 0, 442, 266, 70, true, false);
					} else if (!small_mode) {
						this.drawTexturedModalRect(posX - 7, menuTopLeft + 13, 133, 237, 0, 0 + 79, 266, 433, true, false);

					} else {
						this.drawTexturedModalRect(posX - 7, menuTopLeft + 13, 133, 80, 0, 0 + 79, 266, 142, true, false);
						this.drawTexturedModalRect(posX - 7, menuTopLeft + 93, 133, 17, 0, 472, 266, 40, true, false);

						this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
						this.drawTexturedModalRect(posX + 5, menuTopLeft + 112, 30, 11, 0, 414, 60, 22, false, false);
						this.drawTexturedModalRect(posX + 35, menuTopLeft + 112, 50, 11, 128, 414, 40, 22, false, false);
						this.drawTexturedModalRect(posX + 85, menuTopLeft + 112, 30, 11, 128, 414, 60, 22, false, false);

						this.fontRenderer.drawString("<-", posX + 13, menuTopLeft + 114, CYAN);
						this.fontRenderer.drawString("Page: " + (this.small_page + 1), posX + 45, menuTopLeft + 114, CYAN);
						this.fontRenderer.drawString("->", posX + 100, menuTopLeft + 114, CYAN);

						this.mc.renderEngine.bindTexture(GuiFixedCelestialScreen.guiMain2);
					}

					// TODO: Celestial Body - Info
					y = y + 15;
					str = GCCoreUtil.translate("gui.message.generalinformation");
					if (!small_mode)
						this.fontRenderer.drawString(str, posX, TOP + y, CYAN);
					else if (small_page == 0 || this.selectedBody instanceof Star)
						this.fontRenderer.drawString(str, posX, TOP + y, CYAN);

					if (this.selectedBody.getReachable()) {
						str = GCCoreUtil.translate("gui.message.physicalparameters");
						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 40, CYAN);
						else if (small_page == 0)
							this.fontRenderer.drawString(str, posX, TOP + y + 40, CYAN);

						str = GCCoreUtil.translate("gui.message.atmosphericparameters");
						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 82, CYAN);
						else if (small_page == 1)
							this.fontRenderer.drawString(str, posX, TOP + y, CYAN);

						str = GCCoreUtil.translate("gui.message.atmosphericcomponents");
						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 166, CYAN);
						else if (small_page == 2)
							this.fontRenderer.drawString(str, posX, TOP + y, CYAN);
					}

					BodiesData data = BodiesHelper.getData().get(this.selectedBody);

					if (this.selectedBody instanceof Star)
						str = GCCoreUtil.translate("gui.message.type") + " " + GCCoreUtil.translate("gui.info.star.name");
					if (this.selectedBody instanceof Planet)
						str = GCCoreUtil.translate("gui.message.type") + " " + GCCoreUtil.translate("gui.info.planet.name");
					if (this.selectedBody instanceof Moon)
						str = GCCoreUtil.translate("gui.message.type") + " " + GCCoreUtil.translate("gui.info.moon.name");
					if (this.selectedBody instanceof Satellite)
						str = GCCoreUtil.translate("gui.message.type") + " " + GCCoreUtil.translate("gui.info.satellite.name");
					if (this.selectedBody.getTierRequirement() == -2)
						str = GCCoreUtil.translate("gui.message.type") + " " + GCCoreUtil.translate("gui.info.blackhole.name");

					if (data != null && data.getTypePlanet() != null)
						str = GCCoreUtil.translate("gui.message.type") + " " + BodiesHelper.getLocale(data.getTypePlanet());

					if (!small_mode)
						this.fontRenderer.drawString(str, posX, TOP + y + 10, WHITE);
					else if (small_page == 0 || this.selectedBody instanceof Star)
						this.fontRenderer.drawString(str, posX, TOP + y + 10, WHITE);

					if (this.selectedBody instanceof Planet)
						this.galaxy = ((Planet) this.selectedBody).getParentSolarSystem().getUnlocalizedParentGalaxyName();
					else if (this.selectedBody instanceof IChildBody)
						this.galaxy = ((IChildBody) this.selectedBody).getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName();

					WorldProvider dim = WorldUtil.getProviderForDimensionClient(this.selectedBody.getDimensionID());

					str = GCCoreUtil.translate("gui.message.class") + " " + BodiesHelper.classPlanet(this.selectedBody, dim);

					if (!small_mode)
						this.fontRenderer.drawString(str, posX, TOP + y + 20, WHITE);
					else if (small_page == 0 || this.selectedBody instanceof Star)
						this.fontRenderer.drawString(str, posX, TOP + y + 20, WHITE);

					if (this.selectedBody.getReachable()) {

						str = GCCoreUtil.translate("gui.message.hasdungeons") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (this.selectedBody.getReachable() && this.selectedBody != GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.hasdungeons") + " " + this.localeBoolean(((IGalacticraftWorldProvider) dim).getDungeonSpacing() > 0);
						if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.hasdungeons") + " " + this.localeBoolean(true);

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 29, WHITE);
						else if (small_page == 0)
							this.fontRenderer.drawString(str, posX, TOP + y + 29, WHITE);

						str = GCCoreUtil.translate("gui.message.gravity") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (this.selectedBody.getReachable() && this.selectedBody != GalacticraftCore.planetOverworld) {
							float grav = (0.1F - ((IGalacticraftWorldProvider) dim).getGravity()) * 1000;
							if (((IGalacticraftWorldProvider) dim).getGravity() == 0.0F)
								grav = 0.0F;

							str = GCCoreUtil.translate("gui.message.gravity") + " " + (int) grav + "%";

						} else if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.gravity") + " 100%";

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 38 + 12, WHITE);
						else if (small_page == 0)
							this.fontRenderer.drawString(str, posX, TOP + y + 38 + 12, WHITE);

						str = GCCoreUtil.translate("gui.message.daylength") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (this.selectedBody.getReachable() && this.selectedBody != GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.daylength") + " " + ((WorldProviderSpace) dim).getDayLength() / 1000 + "h " + ((WorldProviderSpace) dim).getDayLength() % 100 + "m";
						else if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.daylength") + " " + "24h 0m";

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 38 + 11 * 2, WHITE);
						else if (small_page == 0)
							this.fontRenderer.drawString(str, posX, TOP + y + 38 + 11 * 2, WHITE);

						str = GCCoreUtil.translate("gui.message.atmopressure") + " " + GCCoreUtil.translate("gui.message.unknown");

						if (data != null) {
							str = GCCoreUtil.translate("gui.message.atmopressure") + " " + data.getPressurePlanet() + " P";
						}

						if (this.selectedBody instanceof IExBody)
							str = GCCoreUtil.translate("gui.message.atmopressure") + " " + ((IExBody) this.selectedBody).getAtmosphericPressure() + " P";

						else if (dim instanceof IAdvancedSpace)
							str = GCCoreUtil.translate("gui.message.atmopressure") + " P " + ((IAdvancedSpace) dim).AtmosphericPressure();
						else if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.atmopressure") + " P 1";

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 78 + 14, WHITE);
						else if (small_page == 1)
							this.fontRenderer.drawString(str, posX, TOP + y + 10, WHITE);

						// TEMPERATURE
						str = GCCoreUtil.translate("gui.message.temperature") + " " + GCCoreUtil.translate("gui.message.unknown");

						float temp = 0.0F;
						try {
							temp = ((IGalacticraftWorldProvider) dim).getCelestialBody().atmosphere.thermalLevel();
						} catch (Exception e) {
						}

						temp = temp == 0 ? BodiesHelper.getDefaultDergrees : temp * BodiesHelper.getDefaultDergrees;
						if (this.selectedBody.getReachable())
							str = GCCoreUtil.translate("gui.message.temperature") + " " + temp + " C";

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 78 + 24, WHITE);
						else if (small_page == 1)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 2, WHITE);
						/////////////////////////////

						str = GCCoreUtil.translate("gui.message.windenergy") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (this.selectedBody.getReachable() && this.selectedBody != GalacticraftCore.planetOverworld && dim instanceof IAdvancedSpace)
							str = GCCoreUtil.translate("gui.message.windenergy") + " " + (Math.round(((IAdvancedSpace) dim).getSolarWindMultiplier() * 1000)) / 1000F;
						else if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.windenergy") + " " + 1.0;

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 76 + 12 * 3, WHITE);
						else if (small_page == 1)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 3, WHITE);

						str = GCCoreUtil.translate("gui.message.solarenergy") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (dim instanceof ISolarLevel) {
							float boost = Math.round((((ISolarLevel) dim).getSolarEnergyMultiplier() - 1) * 1000) / 10.0F;
							str = GCCoreUtil.translate("gui.message.solarenergy") + " " + boost + "%";
						}
						if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.solarenergy") + " 100" + "%";

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 76 + 12 * 4, WHITE);
						else if (small_page == 1)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 4, WHITE);

						str = GCCoreUtil.translate("gui.message.windspeed") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (dim instanceof IGalacticraftWorldProvider)
							str = GCCoreUtil.translate("gui.message.windspeed") + " " + ((IGalacticraftWorldProvider) dim).getWindLevel() + " W/l";
						if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.windspeed") + " 1.0 W/l";

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 74 + 12 * 5, WHITE);
						else if (small_page == 1)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 5, WHITE);

						str = GCCoreUtil.translate("gui.message.solarradiation") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (data != null)
							str = GCCoreUtil.translate("gui.message.solarradiation") + " " + this.localeBoolean(data.getSolarRadiationPlanet());

						if (this.selectedBody instanceof IExBody)
							str = GCCoreUtil.translate("gui.message.solarradiation") + " " + ((IExBody) this.selectedBody).isSolarRadiation() + " P";

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 78 + 11 * 6, WHITE);
						else if (small_page == 1)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 6, WHITE);

						str = GCCoreUtil.translate("gui.message.breathableatmo") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (this.selectedBody.getReachable() && this.selectedBody != GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.breathableatmo") + " " + this.localeBoolean(this.selectedBody.atmosphere.isBreathable());
						if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.breathableatmo") + " " + this.localeBoolean(true);

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 77 + 11 * 9, WHITE);
						else if (small_page == 2)
							this.fontRenderer.drawString(str, posX, TOP + y + 10, WHITE);

						str = GCCoreUtil.translate("gui.message.corrosiveatmo") + " " + GCCoreUtil.translate("gui.message.unknown");
						if (this.selectedBody.getReachable() && this.selectedBody != GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.corrosiveatmo") + " " + this.localeBoolean(this.selectedBody.atmosphere.isCorrosive());
						if (this.selectedBody == GalacticraftCore.planetOverworld)
							str = GCCoreUtil.translate("gui.message.corrosiveatmo") + " " + this.localeBoolean(false);

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 76 + 11 * 10, WHITE);
						else if (small_page == 2)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 2, WHITE);

						if (this.selectedBody.getReachable() && !this.selectedBody.atmosphere.hasNoGases())
							str = this.selectedBody.atmosphere.composition.toString();
						else
							str = GCCoreUtil.translate("gui.message.noatmosphere");

						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 75 + 11 * 11, WHITE);
						else if (small_page == 2)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 3, WHITE);

					}
					if (this.selectedBody.getReachable())
						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 75 + 11 * 13, ColorUtil.to32BitColor(255, 255, 255, 255));
						else if (small_page == 2)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 8, ColorUtil.to32BitColor(255, 255, 255, 255));

					if (this.selectedBody instanceof Star) {
						int x = 0;
						for (CelestialBody body : this.getChildren(this.selectedParent)) {
							if (body.getTierRequirement() >= 0)
								x++;
						}
						str = GCCoreUtil.translate("gui.message.planets") + " " + x;
						this.fontRenderer.drawString(str, posX, TOP + y + 29 + 11, WHITE);

					} else if (this.selectedBody instanceof Planet && data != null && data.getTypePlanet() != TypeBody.STAR) {
						str = GCCoreUtil.translate("gui.message.moons") + " " + this.getChildren(this.selectedBody).size();
						ExoplanetsMod.logger.formatted_Info("Planet Children Count: %s" + getChildren(this.selectedBody).size());
						if (!this.selectedBody.getReachable())
							this.fontRenderer.drawString(str, posX, TOP + y + 29 + 11, WHITE);
						else {
							if (!small_mode)
								this.fontRenderer.drawString(str, posX, TOP + y + 76 + 11 * 12, WHITE);
							else if (small_page == 2)
								this.fontRenderer.drawString(str, posX, TOP + y + 10 * 7, WHITE);
						}
					}

					str = GCCoreUtil.translate("gui.message.fuelset") + " " + this.fuelSet;
					if (this.selectedBody.getTierRequirement() != -1 && this.selectedBody.getReachable() && !mapMode)
						if (!small_mode)
							this.fontRenderer.drawString(str, posX, TOP + y + 80 + 11 * 13, this.fuelSet < 0 ? RED : WHITE);
						else if (small_page == 2)
							this.fontRenderer.drawString(str, posX, TOP + y + 10 * 4, this.fuelSet < 0 ? RED : WHITE);

					if (zoomTooltipPos != 38) {
						sliderPos = (int) Math.min(this.ticksSinceSelectionF * 2, 38);
						this.zoomTooltipPos = sliderPos;
					}

//					if (!(this.selectedBody instanceof Star)) {
//						GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
//						this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
//						this.drawTexturedModalRect(RHS - 230, GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE + sliderPos - 39, 120, 38, 512 - 166, 512 - 76, 166, 76, false, true);
//						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1);
//
//						str = GCCoreUtil.translate("gui.message.engine");
//						this.fontRenderer.drawString(str, RHS - 200, GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE + sliderPos - 35, GREY4);
//
//							str = GCCoreUtil.translate("gui.engine_type." + Engine_Type.SUBLIGHT_ENGINE.getName().toLowerCase());
//						int color = this.engine.getID() >= Engine_Type.SUBLIGHT_ENGINE.getID() || this.mc.player.capabilities.isCreativeMode ? GREEN : RED;
//						this.fontRenderer.drawString(str, RHS - 206, GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE + sliderPos - 20, color);
//
//					}
				}
				if (this.canCreateSpaceStation(this.selectedBody) && (!(this.selectedBody instanceof Satellite))) {
					GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain1);
					int canCreateLength = Math.max(0, this.drawSplitString(GCCoreUtil.translate("gui.message.can_create_space_station.name"), 0, 0, 91, 0, true, true) - 2);
					canCreateOffset = canCreateLength * this.fontRenderer.FONT_HEIGHT;

					this.drawTexturedModalRect(RHS - 95, TOP + 16, 93, 4, 159, 102, 93, 4, false, false);
					for (int barY = 0; barY < canCreateLength; ++barY) {
						this.drawTexturedModalRect(RHS - 95, TOP + 20 + barY * this.fontRenderer.FONT_HEIGHT, 93, this.fontRenderer.FONT_HEIGHT, 159, 106, 93, this.fontRenderer.FONT_HEIGHT, false, false);
					}
					this.drawTexturedModalRect(RHS - 69, TOP + 12, 61, 4, 0, 170, 61, 4, false, false);

					SpaceStationRecipe recipe = WorldUtil.getSpaceStationRecipe(this.selectedBody.getDimensionID());
					int h = 1;
					if (recipe != null)
						h = Math.round((float) Math.ceil((float) recipe.getRecipeSize() / 4) - 1) * 25;

					this.drawTexturedModalRect(RHS - 95, TOP + 20 + canCreateOffset, 93, 43 + (h), 159, 106, 93, 43, false, false);

					if (recipe != null) {
						GL11.glColor4f(0.0F, 1.0F, 0.1F, 1);
						boolean validInputMaterials = true;

						int i = 0;
						int yOff = 0;
						for (Map.Entry<Object, Integer> e : recipe.getInput().entrySet()) {
							Object next = e.getKey();
							int xPos = (int) (RHS - 95 + (i % 4) * 93 / 4 + 5);

							int yPos = TOP + 35 + canCreateOffset + (24 * (i >= 8 ? 2 : i >= 4 ? 1 : 0));

							if (next instanceof ItemStack) {
								int amount = getAmountInInventory((ItemStack) next);
								RenderHelper.enableGUIStandardItemLighting();
								ItemStack toRender = ((ItemStack) next).copy();
								this.itemRender.renderItemAndEffectIntoGUI(toRender, xPos, yPos);
								this.itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, toRender, xPos, yPos, null);
								RenderHelper.disableStandardItemLighting();
								GL11.glEnable(GL11.GL_BLEND);

								if (mousePosX >= xPos && mousePosX <= xPos + 16 && mousePosY >= yPos && mousePosY <= yPos + 16) {
									GL11.glDepthMask(true);
									GL11.glEnable(GL11.GL_DEPTH_TEST);
									GL11.glPushMatrix();
									GL11.glTranslatef(0, 0, 300);
									int k = this.fontRenderer.getStringWidth(((ItemStack) next).getDisplayName());
									int j2 = mousePosX - k / 2;
									int k2 = mousePosY - 12;
									int i1 = 8;

									if (j2 + k > this.width) {
										j2 -= (j2 - this.width + k);
									}

									if (k2 + i1 + 6 > this.height) {
										k2 = this.height - i1 - 6;
									}

									int j1 = ColorUtil.to32BitColor(190, 0, 153, 255);
									this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
									this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
									this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
									this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
									this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
									int k1 = ColorUtil.to32BitColor(170, 0, 153, 255);
									int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
									this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
									this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
									this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
									this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

									this.fontRenderer.drawString(((ItemStack) next).getDisplayName(), j2, k2, WHITE);

									GL11.glPopMatrix();
								}

								str = "" + e.getValue();
								boolean valid = amount >= e.getValue();
								if (!valid && validInputMaterials) {
									validInputMaterials = false;
								}
								int color = valid | this.mc.player.capabilities.isCreativeMode ? GREEN : RED;
								this.fontRenderer.drawString(str, xPos + 8 - this.fontRenderer.getStringWidth(str) / 2, yPos + 15, color);
							} else if (next instanceof Collection) {
								Collection<ItemStack> items = (Collection<ItemStack>) next;

								int amount = 0;

								for (ItemStack stack : items) {
									amount += getAmountInInventory(stack);
								}

								RenderHelper.enableGUIStandardItemLighting();

								Iterator<ItemStack> it = items.iterator();
								int count = 0;
								int toRenderIndex = (int) ((this.ticksSinceSelectionF / 20) % items.size());
								ItemStack toRender = null;
								while (it.hasNext()) {
									ItemStack stack = it.next();
									if (count == toRenderIndex) {
										toRender = stack;
										break;
									}
									count++;
								}

								if (toRender == null) {
									continue;
								}

								this.itemRender.renderItemAndEffectIntoGUI(toRender, xPos, yPos);
								this.itemRender.renderItemOverlayIntoGUI(mc.fontRenderer, toRender, xPos, yPos, null);
								RenderHelper.disableStandardItemLighting();
								GL11.glEnable(GL11.GL_BLEND);

								if (mousePosX >= xPos && mousePosX <= xPos + 16 && mousePosY >= yPos && mousePosY <= yPos + 16) {
									GL11.glDepthMask(true);
									GL11.glEnable(GL11.GL_DEPTH_TEST);
									GL11.glPushMatrix();
									GL11.glTranslatef(0, 0, 300);
									int k = this.fontRenderer.getStringWidth(toRender.getDisplayName());
									int j2 = mousePosX - k / 2;
									int k2 = mousePosY - 12;
									int i1 = 8;

									if (j2 + k > this.width) {
										j2 -= (j2 - this.width + k);
									}

									if (k2 + i1 + 6 > this.height) {
										k2 = this.height - i1 - 6;
									}

									int j1 = ColorUtil.to32BitColor(190, 0, 153, 255);
									this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
									this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
									this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
									this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
									this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
									int k1 = ColorUtil.to32BitColor(170, 0, 153, 255);
									int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
									this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
									this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
									this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
									this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

									this.fontRenderer.drawString(toRender.getDisplayName(), j2, k2, WHITE);

									GL11.glPopMatrix();
								}

								str = "" + e.getValue();
								boolean valid = amount >= e.getValue();
								if (!valid && validInputMaterials) {
									validInputMaterials = false;
								}
								int color = valid | this.mc.player.capabilities.isCreativeMode ? GREEN : RED;
								this.fontRenderer.drawString(str, xPos + 8 - this.fontRenderer.getStringWidth(str) / 2, yPos + 15, color);
							}
							i++;
						}

						if (validInputMaterials || this.mc.player.capabilities.isCreativeMode) {
							GL11.glColor4f(0.0F, 1.0F, 0.1F, 1);
						} else {
							GL11.glColor4f(1.0F, 0.0F, 0.0F, 1);
						}

						this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain1);

						canCreateOffset += Math.round(Math.ceil((float) recipe.getRecipeSize() / 4) - 1) * 25 + 1;

						if (!this.mapMode) {
							if (mousePosX >= RHS - 95 && mousePosX <= RHS && mousePosY >= TOP + 62 + canCreateOffset && mousePosY <= TOP + 62 + 12 + canCreateOffset) {
								this.drawTexturedModalRect(RHS - 95, TOP + 62 + canCreateOffset, 93, 12, 0, 174, 93, 12, false, false);
							}
						}

						this.drawTexturedModalRect(RHS - 95, TOP + 62 + canCreateOffset, 93, 12, 0, 174, 93, 12, false, false);

						int color = (int) ((Math.sin(this.ticksSinceSelectionF / 5.0) * 0.5 + 0.5) * 255);
						this.drawSplitString(GCCoreUtil.translate("gui.message.can_create_space_station.name"), RHS - 48, TOP + 18, 91, ColorUtil.to32BitColor(255, color, 255, color), true, false);

						if (!mapMode) {
							this.drawSplitString(GCCoreUtil.translate("gui.message.create_ss.name").toUpperCase(), RHS - 48, TOP + 65 + canCreateOffset, 91, WHITE, false, false);
						}
					} else {
						this.drawSplitString(GCCoreUtil.translate("gui.message.cannot_create_space_station.name"), RHS - 48, TOP + 65, 91, WHITE, true, false);
					}
				}

				// Catalog overlay
				this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F - Math.min(0.3F, this.ticksSinceSelectionF / 50.0F));
				this.drawTexturedModalRect(LHS, TOP, 74, 11, 0, 392, 148, 22, false, false);
				str = GCCoreUtil.translate("gui.message.catalog.name").toUpperCase();
				this.fontRenderer.drawString(str, LHS + 40 - fontRenderer.getStringWidth(str) / 2, TOP + 1, WHITE);

				// Top bar title:
				this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
				GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
				if (this.selectedBody instanceof Satellite) {
					if (this.selectedStationOwner.length() == 0 || !this.selectedStationOwner.equalsIgnoreCase(PlayerUtil.getName(this.mc.player))) {
						GL11.glColor4f(1.0F, 0.0F, 0.0F, 1);
					} else {
						GL11.glColor4f(0.0F, 1.0F, 0.0F, 1);
					}
					this.drawTexturedModalRect(width / 2 - 47, TOP, 94, 11, 0, 414, 188, 22, false, false);
				} else {
					this.drawTexturedModalRect(width / 2 - 47, TOP, 94, 11, 0, 414, 188, 22, false, false);
				}
				if (this.selectedBody.getTierRequirement() >= 0 && (!(this.selectedBody instanceof Satellite))) {
					boolean canReach;

					if (!this.selectedBody.getReachable() || (this.possibleBodies != null && !this.possibleBodies.contains(this.selectedBody))) {
						canReach = false;
						GL11.glColor4f(1.0F, 0.0F, 0.0F, 1);
					} else {
						canReach = true;
						GL11.glColor4f(0.0F, 1.0F, 0.0F, 1);
					}
					this.drawTexturedModalRect(width / 2 - 30, TOP + 11, 30, 11, 0, 414, 60, 22, false, false);
					this.drawTexturedModalRect(width / 2, TOP + 11, 30, 11, 128, 414, 60, 22, false, false);

					str = GCCoreUtil.translateWithFormat("gui.message.tier.name", this.selectedBody.getTierRequirement() == 0 ? "?" : this.selectedBody.getTierRequirement());

					this.fontRenderer.drawString(str, width / 2 - this.fontRenderer.getStringWidth(str) / 2, TOP + 13, canReach ? GREY4 : RED3);
				}
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1);
				str = this.selectedBody.getLocalizedName();
				if (this.selectedBody instanceof Satellite) {
					str = GCCoreUtil.translate("gui.message.rename.name").toUpperCase();
				}

				this.fontRenderer.drawString(str, width / 2 - this.fontRenderer.getStringWidth(str) / 2, TOP + 2, WHITE);

				// Catalog wedge:
				this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
				GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
				this.drawTexturedModalRect(LHS + 4, TOP, 83, 12, 0, 477, 83, 12, false, false);

				if (!this.mapMode) {
					if (!this.canTravel || !this.selectedBody.getReachable() || (this.possibleBodies != null && !this.possibleBodies.contains(this.selectedBody)) || (this.selectedBody instanceof Satellite && this.selectedStationOwner.equals(""))) {
						GL11.glColor4f(1.0F, 0.0F, 0.0F, 1);
					} else {
						GL11.glColor4f(0.0F, 1.0F, 0.0F, 1);
					}

					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
					this.drawTexturedModalRect(RHS - 74, TOP, 74, 11, 0, 392, 148, 22, true, false);
					str = GCCoreUtil.translate("gui.message.launch.name").toUpperCase();
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					this.fontRenderer.drawString(str, RHS - 40 - fontRenderer.getStringWidth(str) / 2, TOP + 2, WHITE);
				}

				if (this.selectionState == EnumSelection.SELECTED && !(this.selectedBody instanceof Satellite)) {
					handledSliderPos = true;

					int sliderPos = this.zoomTooltipPos;
					if (zoomTooltipPos != 38) {
						sliderPos = (int) Math.min(this.ticksSinceSelectionF * 2, 38);
						this.zoomTooltipPos = sliderPos;
					}

					int xOffset = 222;
					GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
					this.drawTexturedModalRect(RHS - xOffset, height - GuiCelestialSelection.BORDER_SIZE - GuiCelestialSelection.BORDER_EDGE_SIZE - sliderPos, 83, 38, 512 - 166, 512 - 76, 166, 76, true, false);

					boolean flag0 = GalaxyRegistry.getSatellitesForCelestialBody(this.selectedBody).size() > 0;
					boolean flag1 = this.selectedBody instanceof Planet && GalaxyRegistry.getMoonsForPlanet((Planet) this.selectedBody).size() > 0;
					if (flag0 && flag1) {
						this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.0.name"), RHS - xOffset + 41, height - GuiCelestialSelection.BORDER_SIZE - GuiCelestialSelection.BORDER_EDGE_SIZE + 2 - sliderPos, 79, GREY5, false, false);
					} else if (!flag0 && flag1) {
						this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.1.name"), RHS - xOffset + 41, height - GuiCelestialSelection.BORDER_SIZE - GuiCelestialSelection.BORDER_EDGE_SIZE + 6 - sliderPos, 79, GREY5, false, false);
					} else if (flag0) {
						this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.2.name"), RHS - xOffset + 41, height - GuiCelestialSelection.BORDER_SIZE - GuiCelestialSelection.BORDER_EDGE_SIZE + 6 - sliderPos, 79, GREY5, false, false);
					} else {
						this.drawSplitString(GCCoreUtil.translate("gui.message.click_again.3.name"), RHS - xOffset + 41, height - GuiCelestialSelection.BORDER_SIZE - GuiCelestialSelection.BORDER_EDGE_SIZE + 11 - sliderPos, 79, GREY5, false, false);
					}

				}

				if (this.selectedBody instanceof Satellite && renamingSpaceStation) {
					this.drawDefaultBackground();
					GL11.glColor4f(0.0F, 0.6F, 1.0F, 1);
					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain1);
					this.drawTexturedModalRect(width / 2 - 90, this.height / 2 - 38, 179, 67, 159, 0, 179, 67, false, false);
					this.drawTexturedModalRect(width / 2 - 90 + 4, this.height / 2 - 38 + 2, 171, 10, 159, 92, 171, 10, false, false);
					this.drawTexturedModalRect(width / 2 - 90 + 8, this.height / 2 - 38 + 18, 161, 13, 159, 67, 161, 13, false, false);
					this.drawTexturedModalRect(width / 2 - 90 + 17, this.height / 2 - 38 + 59, 72, 12, 159, 80, 72, 12, true, false);
					this.drawTexturedModalRect(width / 2, this.height / 2 - 38 + 59, 72, 12, 159, 80, 72, 12, false, false);
					str = GCCoreUtil.translate("gui.message.assign_name.name");
					this.fontRenderer.drawString(str, width / 2 - this.fontRenderer.getStringWidth(str) / 2, this.height / 2 - 35, WHITE);
					str = GCCoreUtil.translate("gui.message.apply.name");
					this.fontRenderer.drawString(str, width / 2 - this.fontRenderer.getStringWidth(str) / 2 - 36, this.height / 2 + 23, WHITE);
					str = GCCoreUtil.translate("gui.message.cancel.name");
					this.fontRenderer.drawString(str, width / 2 + 36 - this.fontRenderer.getStringWidth(str) / 2, this.height / 2 + 23, WHITE);

					if (this.renamingString == null) {
						Satellite selectedSatellite = (Satellite) this.selectedBody;
						String playerName = PlayerUtil.getName(this.mc.player);
						this.renamingString = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).get(playerName).getStationName();
						if (this.renamingString == null) {
							this.renamingString = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).get(playerName.toLowerCase()).getStationName();
						}
						if (this.renamingString == null) {
							this.renamingString = "";
						}
					}

					str = this.renamingString;
					String str0 = this.renamingString;

					if ((this.ticksSinceSelectionF / 10) % 2 == 0) {
						str0 += "_";
					}

					this.fontRenderer.drawString(str0, width / 2 - this.fontRenderer.getStringWidth(str) / 2, this.height / 2 - 17, WHITE);
				}
			}
			str = EnumColor.ORANGE + "Exoplanet Celestial Map";
			this.fontRenderer.drawString(str, LHS + 5, height - 20, ColorUtil.to32BitColor(255, 255, 255, 255));

			if (!handledSliderPos) {
				this.zoomTooltipPos = 0;
			}
		}
	}

	protected int drawChildren(List<CelestialBody> children, int xOffsetBase, int yOffsetPrior, boolean recursive) {
		xOffsetBase += GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE;
		final int yOffsetBase = GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE + 50 + yOffsetPrior;
		int yOffset = 0;

		int size = children.size();
		for (int i = 0; i < size; i++) {
			CelestialBody child = children.get(i);
			int xOffset = xOffsetBase + (child.equals(this.selectedBody) ? 5 : 0);
			final int scale = (int) Math.min(95.0F, Math.max(0.0F, (this.ticksSinceMenuOpenF * 25.0F) - 95 * i));

			this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
			float brightness = child.equals(this.selectedBody) ? 0.2F : 0.0F;
			if (child.getReachable()) {
				if (child.equals(this.selectedBody))
					GL11.glColor4f(0.0F, 1.0F, 1.0F, scale / 95.0F);
				else
					GL11.glColor4f(0.0F, 0.6F + brightness, 0.0F, scale / 95.0F);
			} else {
				boolean checked = false;

				if (child instanceof Planet) {
					for (Moon moon : GalaxyRegistry.getMoonsForPlanet((Planet) child)) {
						if (moon.getReachable()) {
							checked = true;
							break;
						}
					}
					for (RelayStation relayStation : ExoRegistry.getRelayStationsForCelestialBody((Planet) child)) {
						if (relayStation.getReachable()) {
							checked = true;
							break;
						}
					}
				}
				if (child instanceof Planet && checked)
					GL11.glColor4f(0.6F + brightness, 0.6F, 0.0F, scale / 95.0F);
				else
					GL11.glColor4f(0.6F + brightness, 0.0F, 0.0F, scale / 95.0F);
			}

			this.drawTexturedModalRect(3 + xOffset, yOffsetBase + yOffset + 1, 86, 10, 0, 489, 86, 10, false, false);
//            GL11.glColor4f(5 * brightness, 0.6F + 2 * brightness, 1.0F - 4 * brightness, scale / 95.0F);
			GL11.glColor4f(3 * brightness, 0.6F + 2 * brightness, 1.0F, scale / 95.0F);
			this.drawTexturedModalRect(2 + xOffset, yOffsetBase + yOffset, 93, 12, 95, 464, 93, 12, false, false);

			if (scale > 0) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				int color = 14737632;
				this.fontRenderer.drawString(child.getLocalizedName(), 7 + xOffset, yOffsetBase + yOffset + 2, color);
			}

			yOffset += 14;
			if (recursive && child.equals(this.selectedBody)) {
				List<CelestialBody> grandchildren = this.getChildren(child);
				if (grandchildren.size() > 0) {
					if (this.animateGrandchildren == 14 * grandchildren.size()) {
						yOffset += drawChildren(grandchildren, 10, yOffset, false);
					} else {
						if (this.animateGrandchildren >= 14) {
							List<CelestialBody> partial = new LinkedList<>();
							for (int j = 0; j < this.animateGrandchildren / 14; j++) {
								partial.add(grandchildren.get(j));
							}
							drawChildren(partial, 10, yOffset, false);
						}
						yOffset += this.animateGrandchildren;
						this.animateGrandchildren += 2;
					}
				}
			}
		}
		return yOffset;
	}

	@Override
	protected void mouseClicked(int x, int y, int button) {
		// super.mouseClicked(x, y, button);
		boolean clickHandled = false;

		final int LHS = GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE;
		final int RHS = width - LHS;
		final int TOP = LHS;

		if (x > LHS && x < LHS + 84 && y > height - LHS - 26 && y < height - LHS - 16) {
			this.hideInfo = !this.hideInfo;
		}

		if (x > LHS && x < LHS + 84 && y > height - LHS - 42 && y < height - LHS - 30) {
			this.small_mode = !this.small_mode;
		}

		if (x > LHS + 6 && x < LHS + 93 && y > TOP + 12 && y < TOP + 26) {
			if (this.galaxylist.size() > 1)
				this.showGalaxyList = !this.showGalaxyList;
			if (this.showStarList)
				this.showStarList = false;
		}

		if (x > LHS + 6 && x < LHS + 93 && y > TOP + 32 && y < TOP + 43) {
			if (GalaxyRegistry.getRegisteredSolarSystems().size() > 1)
				this.showStarList = !this.showStarList;
			if (this.showGalaxyList)
				this.showGalaxyList = false;
		}

		if (this.showStarList) {
			int l = 0;
			// for (Map.Entry<SolarSystem, Integer> entry : starlist.entrySet()) {
			for (SolarSystem s : starlist) {
				// int l = (int) entry.getValue();
				if (x > LHS + 100 && x < LHS + 193

						&& y > TOP + 30 * l + 8 && y < TOP + 30 * l + 37)

				// this.drawTexturedModalRect(LHS + 3 + scale, TOP + 30 * l + 8, 140, 30, 275, 160, 140, 30, false, false);

				{

					this.selectedParent = (SolarSystem) s;
					this.showStarList = false;
					this.selectedBody = s.getMainStar();
					this.selectionState = EnumSelection.SELECTED;
					this.zoom = -0.2F;
					this.position = new Vector2f(this.getCelestialBodyPosition(selectedBody).x, this.getCelestialBodyPosition(selectedBody).y);
					clickHandled = true;

					if (this.selectedBody instanceof IChildBody)
						this.galaxy = ((IChildBody) this.selectedBody).getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName();
					if (this.selectedBody instanceof Planet)
						this.galaxy = ((Planet) this.selectedBody).getParentSolarSystem().getUnlocalizedParentGalaxyName();
					if (this.selectedBody instanceof Star)
						this.galaxy = ((Star) this.selectedBody).getParentSolarSystem().getUnlocalizedParentGalaxyName();
				}
				l++;

			}
		}

		if (this.showGalaxyList) {
			for (int i = 0; i < galaxylist.size(); i++) {

				if (x > LHS + 100 + 70 * i && x < LHS + 193 + 70 * i

						&& y > TOP + 4 && y < TOP + 60) {
					this.galaxy = galaxylist.get(i);
					this.zoom = -1.0F;
					this.showGalaxyList = false;

					System.out.println(this.galaxy);
					starlist.clear();
					refreshBodies();

					for (SolarSystem solarSystem : GalaxyRegistry.getRegisteredSolarSystems().values())
						if (solarSystem.getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
							starlist.add(solarSystem);
						}

					Collections.sort(starlist, new Comparator<SolarSystem>() {
						@Override
						public int compare(SolarSystem lhs, SolarSystem rhs) {
							// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
							return lhs.getName().compareTo(rhs.getName());
						}
					});

					for (SolarSystem s : starlist) {
						this.selectedBody = s.getMainStar();
						this.selectionState = EnumSelection.SELECTED;
						this.zoom = -0.2F;
						this.position = new Vector2f(this.getCelestialBodyPosition(selectedBody).x, this.getCelestialBodyPosition(selectedBody).y);
						clickHandled = true;
						break;
					}

				}
			}
		}

		if (this.selectedBody != null && x > LHS && x < LHS + 88 && y > TOP && y < TOP + 13) {
			this.unselectCelestialBody();
			return;
		}

		if (!this.mapMode) {
			if (x >= RHS - 95 && x < RHS && y > TOP + 62 + canCreateOffset && y < TOP + 62 + 12 + canCreateOffset) {
				if (this.selectedBody != null) {
					SpaceStationRecipe recipe = WorldUtil.getSpaceStationRecipe(this.selectedBody.getDimensionID());
					if (recipe != null && this.canCreateSpaceStation(this.selectedBody)) {
						if (recipe.matches(this.mc.player, false) || this.mc.player.capabilities.isCreativeMode) {
							GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_BIND_SPACE_STATION_ID, GCCoreUtil.getDimensionID(this.mc.world), new Object[] { this.selectedBody.getDimensionID() }));
							// Zoom in on Overworld to show the new SpaceStation if not already zoomed
							if (!this.isZoomed()) {
								this.selectionState = EnumSelection.ZOOMED;
								this.preSelectZoom = this.zoom;
								this.preSelectPosition = this.position;
								this.ticksSinceSelectionF = 0;
								this.ticksSinceSelection = 0;
								this.doneZooming = false;
							}
							return;
						}

						clickHandled = true;
					}
				}
			}
		}

		if (this.mapMode) {
			if (x > RHS - 88 && x < RHS && y > TOP && y < TOP + 13) {
				this.mc.displayGuiScreen(null);
				this.mc.setIngameFocus();
				clickHandled = true;
			}
		}

		if (this.selectedBody != null && !this.mapMode) {
			if (x > RHS - 88 && x < RHS && y > TOP && y < TOP + 13) {
				if (!(this.selectedBody instanceof Satellite) || !this.selectedStationOwner.equals("")) {
					if (!mc.player.capabilities.isCreativeMode && this.currenttier >= this.tierneed)
						this.teleportToSelectedBody();
				}

				clickHandled = true;
			}
		}

		// Need unscaled mouse coords
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY() * -1 + Minecraft.getMinecraft().displayHeight - 1;

		if (this.selectedBody instanceof Satellite) {
			if (this.renamingSpaceStation) {
				if (x >= width / 2 - 90 && x <= width / 2 + 90 && y >= this.height / 2 - 38 && y <= this.height / 2 + 38) {
					// Apply
					if (x >= width / 2 - 90 + 17 && x <= width / 2 - 90 + 17 + 72 && y >= this.height / 2 - 38 + 59 && y <= this.height / 2 - 38 + 59 + 12) {
						String strName = PlayerUtil.getName(this.mc.player);
//                        Integer spacestationID = this.spaceStationIDs.get(strName);
//                        if (spacestationID == null) spacestationID = this.spaceStationIDs.get(strName.toLowerCase());
						Satellite selectedSatellite = (Satellite) this.selectedBody;
						Integer spacestationID = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).get(strName).getStationDimensionID();
						if (spacestationID == null) {
							spacestationID = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).get(strName.toLowerCase()).getStationDimensionID();
						}
						if (spacestationID != null) {
							this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).get(strName).setStationName(this.renamingString);
//	                    	this.spaceStationNames.put(strName, this.renamingString);
							GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_RENAME_SPACE_STATION, GCCoreUtil.getDimensionID(this.mc.world), new Object[] { this.renamingString, spacestationID }));
						}
						this.renamingSpaceStation = false;
					}
					// Cancel
					if (x >= width / 2 && x <= width / 2 + 72 && y >= this.height / 2 - 38 + 59 && y <= this.height / 2 - 38 + 59 + 12) {
						this.renamingSpaceStation = false;
					}
					clickHandled = true;
				}
			} else {
				this.drawTexturedModalRect(width / 2 - 47, TOP, 94, 11, 0, 414, 188, 22, false, false);

				if (x >= width / 2 - 47 && x <= width / 2 - 47 + 94 && y >= TOP && y <= TOP + 11) {
					if (this.selectedStationOwner.length() != 0 && this.selectedStationOwner.equalsIgnoreCase(PlayerUtil.getName(this.mc.player))) {
						this.renamingSpaceStation = true;
						this.renamingString = null;
						clickHandled = true;
					}
				}

				Satellite selectedSatellite = (Satellite) this.selectedBody;
				int stationListSize = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).size();
				int max = Math.min((this.height / 2) / 14, stationListSize);

				int xPos;
				int yPos;

				// Up button
				xPos = RHS - 85;
				yPos = TOP + 45;

				if (x >= xPos && x <= xPos + 61 && y >= yPos && y <= yPos + 4) {
					if (this.spaceStationListOffset > 0) {
						this.spaceStationListOffset--;
					}
					clickHandled = true;
				}

				// Down button
				xPos = RHS - 85;
				yPos = TOP + 49 + max * 14;

				if (x >= xPos && x <= xPos + 61 && y >= yPos && y <= yPos + 4) {
					if (max + spaceStationListOffset < stationListSize) {
						this.spaceStationListOffset++;
					}
					clickHandled = true;
				}

				Iterator<Map.Entry<String, StationDataGUI>> it = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).entrySet().iterator();
				int i = 0;
				int j = 0;
				while (it.hasNext() && i < max) {
					Map.Entry<String, StationDataGUI> e = it.next();
					if (j >= this.spaceStationListOffset) {
						int xOffset = 0;

						if (e.getKey().equalsIgnoreCase(this.selectedStationOwner)) {
							xOffset -= 5;
						}

						xPos = RHS - 95 + xOffset;
						yPos = TOP + 50 + i * 14;

						if (x >= xPos && x <= xPos + 93 && y >= yPos && y <= yPos + 12) {
							this.selectedStationOwner = e.getKey();
							clickHandled = true;
						}
						i++;
					}
					j++;
				}
			}
		}

		int xPos = LHS + 2;
		int yPos = TOP + 10;

		boolean planetZoomedMoon = this.isZoomed() && this.selectedParent instanceof Planet;

		// Top yellow button e.g. Sol
		if (x >= xPos && x <= xPos + 93 && y >= yPos && y <= yPos + 12 && this.selectedParent instanceof CelestialBody) {
			if (this.selectedBody == null) {
				this.preSelectZoom = this.zoom;
				this.preSelectPosition = this.position;
			}

			EnumSelection selectionCountOld = this.selectionState;

			if (this.isSelected()) {
				this.unselectCelestialBody();
			}

			if (selectionCountOld == EnumSelection.ZOOMED) {
				this.selectionState = EnumSelection.SELECTED;
			}

			this.selectedBody = (CelestialBody) this.selectedParent;
			this.ticksSinceSelectionF = 0;
			this.ticksSinceSelection = 0;
			this.selectionState = EnumSelection.values()[this.selectionState.ordinal() + 1];
			if (this.isZoomed() && !planetZoomedMoon) {
				this.ticksSinceMenuOpenF = 0;
				this.ticksSinceMenuOpen = 0;
			}
			clickHandled = true;
		}

		yPos += 22;

		// First blue button - normally the Selected Body (but it's the parent planet if this is a moon)
		if (x >= xPos && x <= xPos + 93 && y >= yPos && y <= yPos + 12) {
			if (planetZoomedMoon) {
				if (this.selectedBody == null) {
					this.preSelectZoom = this.zoom;
					this.preSelectPosition = this.position;
				}

				EnumSelection selectionCountOld = this.selectionState;
				if (this.isSelected()) {
					this.unselectCelestialBody();
				}
				if (selectionCountOld == EnumSelection.ZOOMED) {
					this.selectionState = EnumSelection.SELECTED;
				}

				this.selectedBody = (CelestialBody) this.selectedParent;
				this.ticksSinceSelectionF = 0;
				this.ticksSinceSelection = 0;
				this.selectionState = EnumSelection.values()[this.selectionState.ordinal() + 1];
			}
			clickHandled = true;
		}

		if (!clickHandled) {
			List<CelestialBody> children = this.getChildren(this.isZoomed() ? this.selectedBody : this.selectedParent, scroll, max_scroll);

			yPos = TOP + 50;
			for (CelestialBody child : children) {

				clickHandled = this.testClicked(child, child.equals(this.selectedBody) ? 5 : 0, yPos, x, y, false);
				yPos += 14;

				if (!clickHandled && !this.isZoomed() && child.equals(this.selectedBody)) {
					List<CelestialBody> grandchildren = this.getChildren(child);
					int gOffset = 0;
					for (CelestialBody grandchild : grandchildren) {
						if (gOffset + 14 > this.animateGrandchildren) {
							break;
						}
						clickHandled = this.testClicked(grandchild, 10, yPos, x, y, true);
						yPos += 14;
						gOffset += 14;
						if (clickHandled)
							break;
					}
					yPos += this.animateGrandchildren - gOffset;
				}

				if (clickHandled)
					break;

			}
		}

		if (!clickHandled) {
            for (Map.Entry<CelestialBody, Vector3f> e : this.planetPosMap.entrySet())
            {
                CelestialBody bodyClicked = e.getKey();
                if (this.selectedBody == null && bodyClicked instanceof IChildBody)
                {
                    continue;
                }

                float iconSize = e.getValue().z; // Z value holds size on-screen

                if (mouseX >= e.getValue().x - iconSize && mouseX <= e.getValue().x + iconSize && mouseY >= e.getValue().y - iconSize && mouseY <= e.getValue().y + iconSize)
                {
                    if (this.selectedBody != bodyClicked || !this.isZoomed())
                    {
                        if (this.isSelected() && this.selectedBody != bodyClicked)
                        {
                            /*if (!(this.selectedBody instanceof IChildBody) || ((IChildBody) this.selectedBody).getParentPlanet() != bodyClicked)
                            {
//                                this.unselectCelestialBody();
                            }
                            else */if (this.isZoomed())
                            {
                                this.selectionState = EnumSelection.SELECTED;
                            }
                        }

                        if (bodyClicked != this.selectedBody)
                        {
                            this.lastSelectedBody = this.selectedBody;
                            this.animateGrandchildren = 0;
                            if (!(this.selectedBody instanceof IChildBody) || ((IChildBody) this.selectedBody).getParentPlanet() != bodyClicked)
                            {
                                // Only unzoom if the new selected body is not the child of the previously selected body
                                this.selectionState = EnumSelection.UNSELECTED;
                            }
                        }
                        else
                        {
                            this.doneZooming = false;
                            this.planetZoom = 0.0F;
                        }

                        this.selectedBody = bodyClicked;
                        this.ticksSinceSelectionF = 0;
                        this.ticksSinceSelection = 0;
                        this.selectionState = EnumSelection.values()[this.selectionState.ordinal() + 1];

                        if (bodyClicked instanceof IChildBody)
                        {
                            this.selectionState = EnumSelection.ZOOMED;
                        }

                        if (this.isZoomed())
                        {
                            this.ticksSinceMenuOpenF = 0;
                            this.ticksSinceMenuOpen = 0;
                        }

                        //Auto select if it's a spacestation and there is only a single entry
                        if (this.selectedBody instanceof Satellite && this.spaceStationMap.get(this.getSatelliteParentID((Satellite) this.selectedBody)).size() == 1)
                        {
                            Iterator<Map.Entry<String, StationDataGUI>> it = this.spaceStationMap.get(this.getSatelliteParentID((Satellite) this.selectedBody)).entrySet().iterator();
                            this.selectedStationOwner = it.next().getKey();
                        }

                        clickHandled = true;
                        break;
					}
				}
			}
		}

		if (!clickHandled) {
			if (this.selectedBody != null) {
				if (x > RHS - 35 && x < RHS - 10 && y > TOP + 210 && y < TOP + 220) {
					//BodiesData data = BodiesHelper.getData().get(this.selectedBody);
					int k = 2;
					// if(data != null && data.getItemStacks().size() > 0) k = 3;

					if (small_page < k)
						small_page++;
				} else if (x > RHS - 120 && x < RHS - 90 && y > TOP + 210 && y < TOP + 220) {
					if (small_page > 0)
						small_page--;
				} else
					this.unselectCelestialBody();
				this.planetZoom = 0.0F;
			}

			mouseDragging = true;
		}

		Object selectedParent = this.selectedParent;

		if (this.selectedBody instanceof IChildBody) {
			selectedParent = ((IChildBody) this.selectedBody).getParentPlanet();
		} else if (this.selectedBody instanceof Planet) {
			selectedParent = ((Planet) this.selectedBody).getParentSolarSystem();
		} else if (this.selectedBody instanceof Star) {
			selectedParent = ((Star) this.selectedBody).getParentSolarSystem();
		} else if (this.selectedBody == null) {
			if (this.lastSelectedBody instanceof Planet)
				selectedParent = ((Planet) this.lastSelectedBody).getParentSolarSystem();

			if (this.lastSelectedBody instanceof IChildBody)
				selectedParent = ((IChildBody) this.lastSelectedBody).getParentPlanet().getParentSolarSystem();
		}

		if (this.selectedParent != selectedParent) {
			this.selectedParent = selectedParent;
			this.ticksSinceMenuOpenF = 0;
			this.ticksSinceMenuOpen = 0;
		}
	}

	protected boolean testClicked(CelestialBody body, int xOffset, int yPos, int x, int y, boolean grandchild) {
		int xPos = GuiCelestialSelection.BORDER_SIZE + GuiCelestialSelection.BORDER_EDGE_SIZE + 2 + xOffset;
		if (x >= xPos && x <= xPos + 93 && y >= yPos && y <= yPos + 12) {
			if (this.selectedBody != body || !this.isZoomed()) {
				if (this.selectedBody == null) {
					this.preSelectZoom = this.zoom;
					this.preSelectPosition = this.position;
				}

				EnumSelection selectionCountOld = this.selectionState;
				/*
				 * //FIX if(!this.enableTraveltime && this.selectionState == EnumSelection.SELECTED && this.selectedBody != body) { this.unselectCelestialBody(); }
				 */
				if (selectionCountOld == EnumSelection.ZOOMED) {
					this.selectionState = EnumSelection.SELECTED;
				}

				this.doneZooming = false;
				this.planetZoom = 0.0F;

				if (body != this.selectedBody) {
					// Selecting a different body
					this.lastSelectedBody = this.selectedBody;
					this.selectionState = EnumSelection.SELECTED;
					this.selectedBody = body;
					this.ticksSinceSelectionF = 0;
					this.ticksSinceSelection = 0;
					this.small_page = 0;
					if (grandchild)
						this.selectionState = EnumSelection.ZOOMED;
				}
				if (this.isZoomed()) {
					this.ticksSinceMenuOpenF = 0;
					this.ticksSinceMenuOpen = 0;
				}
				this.animateGrandchildren = 0;

				return true;
			}
		}
		return false;
	}

	@Override
	public void drawScreen(int mousePosX, int mousePosY, float partialTicks) {
		this.ticksSinceMenuOpenF += partialTicks;
		this.ticksTotalF += partialTicks;

		if (this.selectedBody != null) {
			this.ticksSinceSelectionF += partialTicks;
		}

		if (this.selectedBody == null && this.ticksSinceUnselectionF >= 0) {
			this.ticksSinceUnselectionF += partialTicks;
		}

		if (Mouse.hasWheel()) {
			float wheel = Mouse.getDWheel() / (this.selectedBody == null ? 500.0F : 250.0F);

			if (wheel != 0) {
				if (mousePosX < 105 && small_mode) {
					boolean planetZoomedNotMoon = this.isZoomed() && !(this.selectedParent instanceof Planet);

					if (wheel > 0 && scroll > 0)
						scroll--;
					else if (wheel < 0 && scroll < this.getChildren(planetZoomedNotMoon ? this.selectedBody : this.selectedParent).size() - max_scroll)
						scroll++;
				} else {
					if (this.selectedBody == null || (this.viewState == EnumView.PREVIEW && !this.isZoomed())) {
						// Minimum zoom increased from 0.55F to 1F to allow zoom out to see other solar systems
						this.zoom = Math.min(Math.max(this.zoom + wheel * ((this.zoom + 2.0F)) / 10.0F, -1.0F), 3);
					} else {
						this.planetZoom = Math.min(Math.max(this.planetZoom + wheel, -4.9F), 5);
					}
				}
			}

		}

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);

		Matrix4f camMatrix = new Matrix4f();
		Matrix4f.translate(new Vector3f(0.0F, 0.0F, -9000.0F), camMatrix, camMatrix); // See EntityRenderer.java:setupOverlayRendering
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.m00 = 2.0F / width;
		viewMatrix.m11 = 2.0F / -height;
		viewMatrix.m22 = -2.0F / 9000.0F;
		viewMatrix.m30 = -1.0F;
		viewMatrix.m31 = 1.0F;
		viewMatrix.m32 = -2.0F;

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		FloatBuffer fb = BufferUtils.createFloatBuffer(16 * Float.SIZE);
		fb.rewind();
		viewMatrix.store(fb);
		fb.flip();
		GL11.glMultMatrix(fb);
		fb.clear();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		fb.rewind();
		camMatrix.store(fb);
		fb.flip();
		fb.clear();
		GL11.glMultMatrix(fb);

		this.setBlackBackground();
		// ResourceLocation galaxy;
		/*
		 * if(BodiesHelper.galaxies.get(this.galaxy.replace("galaxy.", "")) == null) galaxy = new ResourceLocation(AsmodeusCore.ASSET_PREFIX + ":" + "textures/gui/galaxy/" + this.galaxy.toLowerCase() + ".png"); else galaxy = new ResourceLocation(BodiesHelper.galaxies.get(this.galaxy.replace("galaxy.", "")));
		 */
		boolean drawGrid = true;
		if (AsmodeusConfig.enableImgOnGalaxyMap) {
			GL11.glPushMatrix();
			/*
			 * try { if(Minecraft.getMinecraft().getResourceManager().getResource(galaxy) != null) { this.setImgBackground(galaxy); drawGrid = false; } } catch (IOException e) {}
			 */

			this.setImgBackground(guiImg);
			drawGrid = false;
			GL11.glPopMatrix();
		}

		GL11.glPushMatrix();
		Matrix4f worldMatrix = this.setIsometric(partialTicks);
		float gridSize = 7000F; // 194.4F;
		// TODO: Add dynamic map sizing, to allow the map to be small by default and expand when more distant solar systems are added.
		if (drawGrid)
			this.drawGrid(gridSize, height / 3 / 3.5F);
		// this.drawGyperLine();
		this.drawCircles();

		GL11.glPopMatrix();

		HashMap<CelestialBody, Matrix4f> matrixMap = this.drawCelestialBodies(worldMatrix);

		this.planetPosMap.clear();

		for (Map.Entry<CelestialBody, Matrix4f> e : matrixMap.entrySet()) {
			Matrix4f planetMatrix = e.getValue();
			Matrix4f matrix0 = Matrix4f.mul(viewMatrix, planetMatrix, planetMatrix);
			int x = (int) Math.floor((matrix0.m30 * 0.5 + 0.5) * Minecraft.getMinecraft().displayWidth);
			int y = (int) Math.floor(Minecraft.getMinecraft().displayHeight - (matrix0.m31 * 0.5 + 0.5) * Minecraft.getMinecraft().displayHeight);
			Vector2f vec = new Vector2f(x, y);

			Matrix4f scaleVec = new Matrix4f();
			scaleVec.m00 = matrix0.m00;
			scaleVec.m11 = matrix0.m11;
			scaleVec.m22 = matrix0.m22;
			Vector4f newVec = Matrix4f.transform(scaleVec, new Vector4f(2, -2, 0, 0), null);
			float iconSize = (newVec.y * (Minecraft.getMinecraft().displayHeight / 2.0F)) * (e.getKey() instanceof Star ? 2 : 1) * (e.getKey() == this.selectedBody ? 1.5F : 1.0F);

			this.planetPosMap.put(e.getKey(), new Vector3f(vec.x, vec.y, iconSize)); // Store size on-screen in Z-value for ease
		}

		this.drawSelectionCursor(fb, worldMatrix);

		try {
			this.drawButtons(mousePosX, mousePosY);
		} catch (Exception e) {
			if (!this.errorLogged) {
				this.errorLogged = true;
				GCLog.severe("Problem identifying planet or dimension in an add on for Galacticraft!");
				GCLog.severe("(The problem is likely caused by a dimension ID conflict.  Check configs for dimension clashes.  You can also try disabling Mars space station in configs.)");
				e.printStackTrace();
			}
		}

		this.drawBorder();
		GL11.glPopMatrix();

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	@Override
	protected void drawSelectionCursor(FloatBuffer fb, Matrix4f worldMatrix) {
		GL11.glPushMatrix();
		switch (this.selectionState) {
			case SELECTED:
				if (this.selectedBody != null) {
					setupMatrix(this.selectedBody, worldMatrix, fb);
					fb.clear();
					GL11.glScalef(1 / 15.0F, 1 / 15.0F, 1);
					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
					float colMod = this.getZoomAdvanced() < 4.9F ? (float) (Math.sin(this.ticksSinceSelectionF / 2.0F) * 0.5F + 0.5F) : 1.0F;
					GL11.glColor4f(1.0F, 1.0F, 0.0F, 1 * colMod);
					int width = (int) Math.floor((getWidthForCelestialBody(this.selectedBody) / 2.0) * (this.selectedBody instanceof IChildBody ? 9.0 : 30.0));
					this.drawTexturedModalRect(-width, -width, width * 2, width * 2, 266, 29, 100, 100, false, false);

					GL11.glPushMatrix();
					float size = this.selectedBody instanceof IChildBody ? 2F : 12F;
					GL11.glScalef(size, size, size);
					String name = this.selectedBody.getLocalizedName();
					int white = Utils.getIntColor(255, 255, 255, 255);
					int yellow = Utils.getIntColor(255, 255, 0, 255);
					this.drawString(fontRenderer, name, 4 + this.getWidthForCelestialBody(this.selectedBody), -5, this.selectedBody instanceof Star ? yellow : white);
					GL11.glPopMatrix();
				}
				break;
			case ZOOMED:
				if (this.selectedBody != null) {
					setupMatrix(this.selectedBody, worldMatrix, fb);
					fb.clear();
					float div = (this.zoom + 1.0F - this.planetZoom);
					float scale = Math.max(0.3F, 1.5F / (this.ticksSinceSelectionF / 5.0F)) * 2.0F / div;
					GL11.glScalef(scale, scale, 1);
					this.mc.renderEngine.bindTexture(GuiCelestialSelection.guiMain0);
					float colMod = this.getZoomAdvanced() < 4.9F ? (float) (Math.sin(this.ticksSinceSelectionF / 1.0F) * 0.5F + 0.5F) : 1.0F;
					GL11.glColor4f(0.4F, 0.8F, 1.0F, 1 * colMod);
					int width = getWidthForCelestialBody(this.selectedBody) * 13;
					this.drawTexturedModalRect(-width, -width, width * 2, width * 2, 266, 29, 100, 100, false, false);
				}
				break;
			default:
				break;
		}
		GL11.glPopMatrix();
	}

	public int getWidthForCelestialBody(CelestialBody celestialBody) {
		float size = celestialBody.getRelativeSize();
		if (size < 0.9 && celestialBody instanceof IChildBody)
			size = 1.0F;
		if (size > 5.0F)
			size = 5.0F;

		boolean zoomed = celestialBody == this.selectedBody && this.selectionState == EnumSelection.SELECTED;
		return (int) (celestialBody instanceof Star ? (zoomed ? 12 * size : 8 * size) : celestialBody instanceof Planet ? (zoomed ? 6 * size : 4 * size) : celestialBody instanceof IChildBody ? (zoomed ? 6 * size : 4 * size) : 2);

	}

	public HashMap<CelestialBody, Matrix4f> drawCelestialBodies(Matrix4f worldMatrix) {
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		FloatBuffer fb = BufferUtils.createFloatBuffer(16 * Float.SIZE);
		HashMap<CelestialBody, Matrix4f> matrixMap = Maps.newHashMap();

		for (CelestialBody body : bodiesToRender) {
			boolean hasParent = body instanceof IChildBody;

			float alpha = getAlpha(body);

			if ((body instanceof Satellite && alpha > 0.0F) || (!(body instanceof Satellite) && alpha >= 0.0F)) {

				GlStateManager.pushMatrix();
				Matrix4f worldMatrixLocal = setupMatrix(body, worldMatrix, fb, hasParent ? 0.25F : 1.0F);

				CelestialBodyRenderEvent.Pre preEvent = new CelestialBodyRenderEvent.Pre(body, body.getBodyIcon(), 16);
				MinecraftForge.EVENT_BUS.post(preEvent);

				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				if (preEvent.celestialBodyTexture != null) {
					this.mc.renderEngine.bindTexture(preEvent.celestialBodyTexture);
				}

				if (!preEvent.isCanceled()) {
					int size = getWidthForCelestialBody(body);
					int xOffset = 0;// size / 4;
					int yOffset = 0;// size / 4;

					BodiesData data = BodiesHelper.getData().get(body);

					if (body instanceof Star || data != null && data.getTypePlanet() == TypeBody.STAR) {
						GL11.glPushMatrix();
						GL11.glShadeModel(GL11.GL_SMOOTH);
						// GL11.glDepthMask(false);
						GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
						// GL11.glEnable(GL11.GL_BLEND);
						GL11.glDisable(GL11.GL_TEXTURE_2D);
						// GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
						// GL11.glEnable(GL11.GL_ALPHA_TEST);
						// OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ZERO);

						float r = 1.0F, g = 1.0F, b = 0.8F, a = 0.5F;
						float f10 = size * 4;

						if (data != null && data.getTypePlanet() == TypeBody.STAR && data.getStarColor() != null) {
							/*
							 * if(data.getStarColor() == StarColor.BROWN) { r = 0.8F; g = 0.4F; b = 0.2F; a = 0.5F; } else if(data.getStarColor() == StarColor.RED) { r = 0.9F; g = 0.0F; b = 0.0F; a = 0.7F; } else if(data.getStarColor() == StarColor.ORANGE) { r = 1.0F; g = 0.4F; b = 0.0F; a = 0.7F; } else if(data.getStarColor() == StarColor.YELLOW) { r = 1.0F; g = 0.8F; b = 0.5F; a = 0.7F; } else if(data.getStarColor() == StarColor.WHITE) { r = 1.0F; g = 1.0F; b = 1.0F; a = 0.7F; } else
							 * if(data.getStarColor() == StarColor.LIGHTBLUE) { r = 0.4F; g = 0.7F; b = 1.0F; a = 0.7F; }
							 */

							r = data.getStarColor().getColor().floatX() / 255F;
							g = data.getStarColor().getColor().floatY() / 255F;
							b = data.getStarColor().getColor().floatZ() / 255F;
							a = 0.7F;
						}

						float xSize = size / 2 - 4, ySize = size / 2 - 4;
						xSize /= 4;
						ySize /= 4;
						GL11.glColor4f(r, g, b, a);
						GL11.glDisable(GL11.GL_CULL_FACE);

						GL11.glBegin(GL11.GL_TRIANGLE_FAN);

						GL11.glVertex2d(xSize, ySize);
						for (int angle = 0; angle <= 360; angle += 360 / 6) {
							if (angle % 120 == 0)
								GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.0F);

							double x = f10 * Math.cos(angle * Math.PI / 180);
							double y = f10 * Math.sin(angle * Math.PI / 180);
							GL11.glVertex2d(x + xSize, y + ySize);
						}

						GL11.glEnd();
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

						GL11.glDisable(GL11.GL_ALPHA_TEST);
						GL11.glDisable(GL11.GL_BLEND);

						// OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
						// GL11.glEnable(GL11.GL_TEXTURE_2D);
						GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
						GL11.glPopMatrix();

					}
					this.drawTexturedModalRect(xOffset + -size / 2, yOffset + -size / 2, size, size, 0, 0, preEvent.textureSize, preEvent.textureSize, false, false, preEvent.textureSize, preEvent.textureSize);

					matrixMap.put(body, worldMatrixLocal);
				}

				CelestialBodyRenderEvent.Post postEvent = new CelestialBodyRenderEvent.Post(body);
				MinecraftForge.EVENT_BUS.post(postEvent);
				GlStateManager.popMatrix();

			}
		}

		for (CelestialBody body : bodiesToRender) {
			boolean hasParent = body instanceof IChildBody;

			float alpha = getAlpha(body);
			if ((body instanceof Satellite && alpha > 0.0F) || (!(body instanceof Satellite) && alpha >= 0.0F)) {
				GlStateManager.pushMatrix();
				Matrix4f worldMatrixLocal = setupMatrix(body, worldMatrix, fb, hasParent ? 0.25F : 1.0F);

				if ((this.selectionState == EnumSelection.UNSELECTED && !hasParent) || (this.selectionState == EnumSelection.ZOOMED && hasParent)) {

					GL11.glScalef(0.5F, 0.5F, 0.5F);
					String name = body.getLocalizedName();
					int white = Utils.getIntColor(255, 255, 255, 255);
					int yellow = Utils.getIntColor(255, 255, 0, 255);
					this.drawString(fontRenderer, name, 4 + this.getWidthForCelestialBody(body), -5, body instanceof Star ? yellow : white);

					if (ClientProxyCore.playerHead != null) {
						if (this.selectionState == EnumSelection.UNSELECTED && mc.world.provider instanceof IGalacticraftWorldProvider && ((IGalacticraftWorldProvider) this.mc.world.provider).getCelestialBody() instanceof IChildBody) {
							if (body == ((IChildBody) ((IGalacticraftWorldProvider) this.mc.world.provider).getCelestialBody()).getParentPlanet()) {
								GlStateManager.enableBlend();
								GlStateManager.blendFunc(770, 771);
								GlStateManager.scale(0.25F, 0.25F, 0.25F);
								this.mc.renderEngine.bindTexture(ClientProxyCore.playerHead);
								this.drawTexturedModalRect(20 + (this.fontRenderer.getStringWidth(name) * 5), -18, 32, 32, 32, 32);
							}
						} else if (mc.world.provider instanceof IGalacticraftWorldProvider && body != GalacticraftCore.planetOverworld && ((IGalacticraftWorldProvider) this.mc.world.provider).getCelestialBody() == body) {
							GlStateManager.enableBlend();
							GlStateManager.blendFunc(770, 771);
							GlStateManager.scale(0.25F, 0.25F, 0.25F);
							this.mc.renderEngine.bindTexture(ClientProxyCore.playerHead);
							this.drawTexturedModalRect(20 + (this.fontRenderer.getStringWidth(name) * 5), -18, 32, 32, 32, 32);
						} else if (mc.world.provider instanceof WorldProviderSurface && body == GalacticraftCore.planetOverworld) {
							GlStateManager.enableBlend();
							GlStateManager.blendFunc(770, 771);
							GlStateManager.scale(0.25F, 0.25F, 0.25F);
							this.mc.renderEngine.bindTexture(ClientProxyCore.playerHead);
							this.drawTexturedModalRect(20 + (this.fontRenderer.getStringWidth(name) * 5), -18, 32, 32, 32, 32);
						}
					}
				}

				GlStateManager.popMatrix();
			}
		}

		return matrixMap;
	}

	@Override
	protected Matrix4f setupMatrix(CelestialBody body, Matrix4f worldMatrix, FloatBuffer fb, float scaleXZ) {
		Matrix4f worldMatrix0 = new Matrix4f(worldMatrix);
		Matrix4f.translate(this.getCelestialBodyPosition(body), worldMatrix0, worldMatrix0);
		Matrix4f worldMatrix1 = new Matrix4f();
		Matrix4f.rotate((float) Math.toRadians(isometz), new Vector3f(0, 0, 1), worldMatrix1, worldMatrix1);
		Matrix4f.rotate((float) Math.toRadians(-isometx), new Vector3f(1, 0, 0), worldMatrix1, worldMatrix1);
		if (scaleXZ != 1.0F) {
			Matrix4f.scale(new Vector3f(scaleXZ, scaleXZ, 1.0F), worldMatrix1, worldMatrix1);
		}
		worldMatrix1 = Matrix4f.mul(worldMatrix0, worldMatrix1, worldMatrix1);
		fb.rewind();
		worldMatrix1.store(fb);
		fb.flip();
		GL11.glMultMatrix(fb);

		return worldMatrix1;
	}

	@Override
	protected void mouseClickMove(int x, int y, int lastButtonClicked, long timeSinceMouseClick) {
		// super.mouseClickMove(x, y, lastButtonClicked, timeSinceMouseClick);

		if (mouseDragging && lastMovePosX != -1 && lastButtonClicked == 0) {
			int deltaX = x - lastMovePosX;
			int deltaY = y - lastMovePosY;
			float scollMultiplier = -Math.abs(this.zoom);

			if (this.zoom == -1.0F)
				scollMultiplier = -1.5F;

			else if (this.zoom >= -0.25F && this.zoom <= 0.15F)
				scollMultiplier = -0.2F;

			if (this.zoom >= 0.15F)
				scollMultiplier = -0.15F;

			translation.x += (deltaX - deltaY) * scollMultiplier * (ConfigManagerCore.invertMapMouseScroll ? -1.0F : 1.0F) * ConfigManagerCore.mapMouseScrollSensitivity * 0.2F;
			translation.y += (deltaY + deltaX) * scollMultiplier * (ConfigManagerCore.invertMapMouseScroll ? -1.0F : 1.0F) * ConfigManagerCore.mapMouseScrollSensitivity * 0.2F;

			if (AsmodeusConfig.enableDynamicImgOnGalaxyMap) {
				xImgOffset += deltaX * (scollMultiplier / 4);
				yImgOffset += deltaY * (scollMultiplier / 4);
			}
		}
		/*
		 * if (mouseDragging && lastMovePosX != -1 && lastButtonClicked == 1) { int deltaX = x - lastMovePosX; int deltaY = y - lastMovePosY;
		 * 
		 * isometz += (deltaX - deltaY) * -0.4F * (ConfigManagerCore.invertMapMouseScroll ? -1.0F : 1.0F) * ConfigManagerCore.mapMouseScrollSensitivity;
		 * 
		 * isometx += (deltaY + deltaX) * -0.4F * (ConfigManagerCore.invertMapMouseScroll ? -1.0F : 1.0F) * ConfigManagerCore.mapMouseScrollSensitivity; System.out.println(isometz); }
		 */
		lastMovePosX = x;
		lastMovePosY = y;

		if (xImgOffset > 20)
			xImgOffset = 20;
		if (xImgOffset < -150)
			xImgOffset = -150;

		if (yImgOffset > 20)
			yImgOffset = 20;
		if (yImgOffset < -80)
			yImgOffset = -80;
	}

	@Override
	protected void mouseReleased(int x, int y, int button) {
		super.mouseReleased(x, y, button);

		mouseDragging = false;
		lastMovePosX = -1;
		lastMovePosY = -1;
	}

	public void setImgBackground(ResourceLocation galaxy) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		// GL11.glScaled(2.0D, 2.0D, 0D);

		GL11.glTranslated(-40D, -40D, 0);
		GL11.glTranslated(xImgOffset, yImgOffset, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(galaxy);

		int width = Display.getWidth();
		int height = Display.getHeight();
		// System.out.println(width + " | " + height);
		this.drawTexturedModalRect(0, 0, width, height, 0, 0, width * 2, height * 2, false, false, 1024, 1024);

		ResourceLocation guiImg_2 = new ResourceLocation(AsmodeusCore.ASSET_PREFIX, "textures/gui/galaxymap_nebula_" + nebula_img + ".png");

		this.mc.renderEngine.bindTexture(guiImg_2);
		GL11.glTranslated(xImgOffset * 1.5D, yImgOffset * 1.5D, 0);
		// GL11.glColor4f(nebula_color.floatX(), nebula_color.floatY(), nebula_color.floatZ(), 0.5F);
		GL11.glColor4f(0.5F, 0.4F, nebula_color.floatZ(), 0.5F);
		this.drawTexturedModalRect(0, 0, width, height, 0, 0, 1024, 1024, false, false, 1024, 1024);

		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	public Matrix4f setIsometric(float partialTicks) {
		Matrix4f mat0 = new Matrix4f();
		Matrix4f.translate(new Vector3f(width / 2.0F, height / 2, 0), mat0, mat0);
		Matrix4f.rotate((float) Math.toRadians(isometx), new Vector3f(1, 0, 0), mat0, mat0);
		Matrix4f.rotate((float) Math.toRadians(-isometz), new Vector3f(0, 0, 1), mat0, mat0);
		float zoomLocal = this.getZoomAdvanced();
		this.zoom = zoomLocal;
		Matrix4f.scale(new Vector3f(1.1f + zoomLocal, 1.1F + zoomLocal, 1.1F + zoomLocal), mat0, mat0);
		Vector2f cBodyPos = this.getTranslationAdvanced(partialTicks);
		this.position = this.getTranslationAdvanced(partialTicks);
		Matrix4f.translate(new Vector3f(-cBodyPos.x, -cBodyPos.y, 0), mat0, mat0);
		FloatBuffer fb = BufferUtils.createFloatBuffer(16);
		fb.rewind();
		mat0.store(fb);
		fb.flip();
		GL11.glMultMatrix(fb);
		return mat0;
	}

	public float getAlpha(CelestialBody body) {
		float alpha = 1.0F;

		if (body instanceof IChildBody) {
			boolean selected = body == this.selectedBody || (((IChildBody) body).getParentPlanet() == this.selectedBody && this.selectionState != EnumSelection.SELECTED);
			boolean ready = this.lastSelectedBody != null || this.ticksSinceSelection > 35;
			boolean isSibling = getSiblings(this.selectedBody).contains(body);
			boolean isPossible = !(body instanceof Satellite) || (this.possibleBodies != null && this.possibleBodies.contains(body));
			if ((!selected && !isSibling) || !isPossible) {
				alpha = 0.0F;
			} else if (this.isZoomed() && ((!selected || !ready) && !isSibling)) {
				alpha = Math.min(Math.max((this.ticksSinceSelection - 30) / 15.0F, 0.0F), 1.0F);
			}
		} else {
			boolean isSelected = this.selectedBody == body;
			boolean isChildSelected = this.selectedBody instanceof IChildBody;
			boolean isOwnChildSelected = isChildSelected && ((IChildBody) this.selectedBody).getParentPlanet() == body;

			if (!isSelected && !isOwnChildSelected && (this.isZoomed() || isChildSelected)) {
				if (this.lastSelectedBody != null || this.selectedBody instanceof IChildBody) {
					alpha = 0.0F;
				} else {
					alpha = 0.4F - Math.min(this.ticksSinceSelection / 25.0F, 0.4F);
				}
			}
		}

		return alpha;
	}

	@Override
	public void drawCircles() {
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glLineWidth(2);
		int count = 0;

		final float theta = (float) (2 * Math.PI / 90);
		final float cos = (float) Math.cos(theta);
		final float sin = (float) Math.sin(theta);

		for (CelestialBody body : bodiesToRender) {
			Vector3f systemOffset = new Vector3f(0.0F, 0.0F, 0.0F);
			if (body instanceof IChildBody) {
				systemOffset = this.getCelestialBodyPosition(((IChildBody) body).getParentPlanet());
			} else if (body instanceof Planet) {
				systemOffset = this.getCelestialBodyPosition(((Planet) body).getParentSolarSystem().getMainStar());
			} else if (body instanceof Star) {
				systemOffset = this.getCelestialBodyPosition((Star) body);
			}

			float orbit_ext_x = body instanceof IExBody ? ((IExBody) body).getXOrbitEccentricity() : 1.0F;
			float orbit_ext_y = body instanceof IExBody ? ((IExBody) body).getYOrbitEccentricity() : 1.0F;
			float orbit_offset_x = body instanceof IExBody ? ((IExBody) body).getXOrbitOffset() : 0.0F;
			float orbit_offset_y = body instanceof IExBody ? ((IExBody) body).getYOrbitOffset() : 0.0F;

			float x = this.getScale(body);
			float y = 0;

			float alpha = getAlpha(body);

			if (alpha > 0.0F) {
				switch (count % 2) {
					case 0:
						// GL11.glColor4f(body.getRingColorR() / 1.4F, body.getRingColorG() / 1.4F, body.getRingColorB() / 1.4F, alpha / 1.4F);
						GL11.glColor4f(0.0F / 1.4F, 0.4F / 1.4F, 0.9F / 1.4F, alpha / 2.4F);
						break;
					case 1:
						// GL11.glColor4f(body.getRingColorR() / 1.4F, body.getRingColorG() / 1.4F, body.getRingColorB() / 1.4F, alpha / 1.4F);
						GL11.glColor4f(0.0F, 0.4F, 0.9F, alpha / 2.4F);
						break;
				}

				if (body.equals(this.selectedBody))
					GL11.glColor4f(0.0F, 0.4F, 0.9F, 1.0F);

				CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre preEvent = new CelestialBodyRenderEvent.CelestialRingRenderEvent.Pre(body, systemOffset);
				MinecraftForge.EVENT_BUS.post(preEvent);

				if (!preEvent.isCanceled()) {
					GL11.glTranslatef(systemOffset.x, systemOffset.y, systemOffset.z);

					GL11.glBegin(GL11.GL_LINE_LOOP);

					float temp;
					for (int i = 0; i < 90; i++) {
						GL11.glVertex2f((x + orbit_offset_x) * (orbit_ext_x > 0 ? orbit_ext_x : 1.0F), (y + orbit_offset_y) * (orbit_ext_y > 0 ? orbit_ext_y : 1.0F));

						temp = x;
						x = cos * x - sin * y;
						y = sin * temp + cos * y;
					}

					GL11.glEnd();

					GL11.glTranslatef(-systemOffset.x, -systemOffset.y, -systemOffset.z);

					count++;
				}

				CelestialBodyRenderEvent.CelestialRingRenderEvent.Post postEvent = new CelestialBodyRenderEvent.CelestialRingRenderEvent.Post(body);
				MinecraftForge.EVENT_BUS.post(postEvent);
			}
		}

		GL11.glLineWidth(1);
	}

	public void drawGyperLine() {

		Vector3 vec1 = new Vector3();
		Vector3 vec2 = new Vector3();

		if (this.selectedBody instanceof Star) {
			vec1 = ((Star) this.selectedBody).getParentSolarSystem().getMapPosition();
		} else if (this.selectedBody instanceof Planet) {
			vec1 = ((Planet) this.selectedBody).getParentSolarSystem().getMapPosition();
		} else if (this.selectedBody instanceof Moon) {
			vec1 = ((Moon) this.selectedBody).getParentPlanet().getParentSolarSystem().getMapPosition();
		}

		for (CelestialBody body : bodiesToRender) {
			if (this.mc.world.provider instanceof IGalacticraftWorldProvider && ((IGalacticraftWorldProvider) this.mc.world.provider).getCelestialBody() == body) {
				if (body instanceof Star) {
					vec2 = ((Star) body).getParentSolarSystem().getMapPosition();
				} else if (body instanceof Planet) {
					vec2 = ((Planet) body).getParentSolarSystem().getMapPosition();
				} else if (body instanceof Moon) {
					vec2 = ((Moon) body).getParentPlanet().getParentSolarSystem().getMapPosition();
				}
			} else if (this.mc.world.provider instanceof WorldProviderSurface) {
				vec2 = GalacticraftCore.solarSystemSol.getMapPosition();
			}
		}

		if (vec1 != null && vec2 != null && this.selectedBody != null) {

			GL11.glPushMatrix();
			GL11.glColor4f(0, 1, 1, 0.5F);
			GL11.glLineWidth(2);

			GL11.glBegin(GL11.GL_LINES);
			GL11.glVertex3f(vec1.floatX(), vec1.floatY(), vec1.floatZ());
			GL11.glVertex3f(vec2.floatX(), vec2.floatY(), vec2.floatZ());
			GL11.glEnd();
			GL11.glLineWidth(1);
			GL11.glPopMatrix();

		}

	}

	@Override
	protected Vector3f getCelestialBodyPosition(CelestialBody cBody) {
		if (cBody == null)
			return new Vector3f(0.0F, 0.0F, 0.0F);
		if (cBody instanceof Star) {
			if (cBody.getUnlocalizedName().equalsIgnoreCase("star.sol"))
			// Return zero vector for Sol, different location for other solar systems
			{
				return new Vector3f();
			}
			return ((Star) cBody).getParentSolarSystem().getMapPosition().toVector3f();
		}

		float timeScale = cBody instanceof Planet ? 200.0F : 2.0F;
		float orbit_ext_x = cBody instanceof IExBody ? ((IExBody) cBody).getXOrbitEccentricity() : 1.0F;
		float orbit_ext_y = cBody instanceof IExBody ? ((IExBody) cBody).getYOrbitEccentricity() : 1.0F;
		float orbit_offset_x = cBody instanceof IExBody ? ((IExBody) cBody).getXOrbitOffset() : 0.0F;
		float orbit_offset_y = cBody instanceof IExBody ? ((IExBody) cBody).getYOrbitOffset() : 0.0F;
		float distanceFromCenter = this.getScale(cBody);

		float x = (float) Math.sin(ticksTotalF / (timeScale * cBody.getRelativeOrbitTime()) + cBody.getPhaseShift()) * distanceFromCenter;
		float y = (float) Math.cos(ticksTotalF / (timeScale * cBody.getRelativeOrbitTime()) + cBody.getPhaseShift()) * distanceFromCenter;
		Vector3f cBodyPos = new Vector3f((x + orbit_offset_x) * (orbit_ext_x > 0 ? orbit_ext_x : 1.0F), (y + orbit_offset_y) * (orbit_ext_y > 0 ? orbit_ext_y : 1.0F), 0);

		if (cBody instanceof Planet) {
			Vector3f parentVec = this.getCelestialBodyPosition(((Planet) cBody).getParentSolarSystem().getMainStar());
			return Vector3f.add(cBodyPos, parentVec, null);
		}

		if (cBody instanceof IChildBody) {
			Vector3f parentVec = this.getCelestialBodyPosition(((IChildBody) cBody).getParentPlanet());
			return Vector3f.add(cBodyPos, parentVec, null);
		}

		return cBodyPos;
	}

	@Override
	protected void keyTyped(char keyChar, int keyID) throws IOException {
		// Override and do nothing, so it isn't possible to exit the GUI
		if (this.mapMode) {
			super.keyTyped(keyChar, keyID);
		}

		if (keyID == 1) {
			if (this.selectedBody != null) {
				this.unselectCelestialBody();
			}
		}

		if (this.renamingSpaceStation) {
			if (keyID == Keyboard.KEY_BACK) {
				if (this.renamingString != null && this.renamingString.length() > 0) {
					String toBeParsed = this.renamingString.substring(0, this.renamingString.length() - 1);

					if (this.isValid(toBeParsed)) {
						this.renamingString = toBeParsed;
//                        this.timeBackspacePressed = System.currentTimeMillis();
					} else {
						this.renamingString = "";
					}
				}
			} else if (keyChar == 22) {
				String pastestring = GuiScreen.getClipboardString();

				if (pastestring == null) {
					pastestring = "";
				}

				if (this.isValid(this.renamingString + pastestring)) {
					this.renamingString = this.renamingString + pastestring;
					this.renamingString = this.renamingString.substring(0, Math.min(String.valueOf(this.renamingString).length(), MAX_SPACE_STATION_NAME_LENGTH));
				}
			} else if (this.isValid(this.renamingString + keyChar)) {
				this.renamingString = this.renamingString + keyChar;
				this.renamingString = this.renamingString.substring(0, Math.min(this.renamingString.length(), MAX_SPACE_STATION_NAME_LENGTH));
			}

			return;
		}

		// Keyboard shortcut - teleport to dimension by pressing 'Enter'
		if (keyID == Keyboard.KEY_RETURN) {
			if (!mc.player.capabilities.isCreativeMode && this.currenttier >= this.tierneed)
				this.teleportToSelectedBody();
		}
	}

	@Override
	protected void teleportToSelectedBody() {
		if (this.selectedBody != null) {
			if (this.selectedBody.getReachable() && (this.possibleBodies != null && this.possibleBodies.contains(this.selectedBody))) {
				try {
					String dimension;
					int dimensionID;

					if (this.selectedBody instanceof Satellite) {
						if (this.spaceStationMap == null) {
							GCLog.severe("Please report as a BUG: spaceStationIDs was null.");
							return;
						}
						Satellite selectedSatellite = (Satellite) this.selectedBody;
						Integer mapping = this.spaceStationMap.get(getSatelliteParentID(selectedSatellite)).get(this.selectedStationOwner).getStationDimensionID();
						// No need to check lowercase as selectedStationOwner is taken from keys.
						if (mapping == null) {
							GCLog.severe("Problem matching player name in space station check: " + this.selectedStationOwner);
							return;
						}
						dimensionID = mapping;
						WorldProvider spacestation = WorldUtil.getProviderForDimensionClient(dimensionID);
						if (spacestation != null) {
							dimension = "Space Station " + mapping;
						} else {
							GCLog.severe("Failed to find a spacestation with dimension " + dimensionID);
							return;
						}
					} else {
						dimensionID = this.selectedBody.getDimensionID();
						dimension = WorldUtil.getDimensionName(WorldUtil.getProviderForDimensionClient(dimensionID));
					}

					if (dimension.contains("$")) {
						this.mc.gameSettings.thirdPersonView = 0;
					}
					GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(PacketSimple.EnumSimplePacket.S_TELEPORT_ENTITY, GCCoreUtil.getDimensionID(mc.world), new Object[] { dimensionID, this.fuelSet }));

					mc.displayGuiScreen(new GuiTeleporting(dimensionID));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void drawFullSizedTexturedRect(int x, int y, int width, int height) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder worldRenderer = tessellator.getBuffer();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		worldRenderer.pos(x, y + height, this.zLevel).tex(0, 0).endVertex();
		worldRenderer.pos(x + width, y + height, this.zLevel).tex(1, 0).endVertex();
		worldRenderer.pos(x + width, y, this.zLevel).tex(1, 1).endVertex();
		worldRenderer.pos(x, y, this.zLevel).tex(0, 1).endVertex();
		tessellator.draw();
	}

	protected boolean canCreateSpaceStation(CelestialBody atBody) {
		if (this.mapMode || ConfigManagerCore.disableSpaceStationCreation || !this.canCreateStations) {
			return false;
		}

		if (!atBody.getReachable() || (this.possibleBodies != null && !this.possibleBodies.contains(atBody))) {
			// If parent body is unreachable, the satellite is also unreachable
			return false;
		}

		boolean foundRecipe = false;
		for (SpaceStationType type : GalacticraftRegistry.getSpaceStationData()) {
			if (type.getWorldToOrbitID() == atBody.getDimensionID()) {
				foundRecipe = true;
			}
		}

		if (!foundRecipe) {
			return false;
		}

		if (!ClientProxyCore.clientSpaceStationID.containsKey(atBody.getDimensionID())) {
			return true;
		}

		int resultID = ClientProxyCore.clientSpaceStationID.get(atBody.getDimensionID());

		return !(resultID != 0 && resultID != -1);
	}

	private String localeBoolean(boolean bol) {
		return bol ? GCCoreUtil.translate("gui.message.yes") : GCCoreUtil.translate("gui.message.no");
	}

	protected List<CelestialBody> getChildren(Object object, int start, int size) {
		List<CelestialBody> bodyList = Lists.newArrayList();
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;

		if (object instanceof Planet) {
			List<Moon> moons = GalaxyRegistry.getMoonsForPlanet((Planet) object);
			List<RelayStation> relayStations = ExoRegistry.getRelayStationsForCelestialBody((Planet) object);
			if (Loader.isModLoaded("planetprogression")) {
				for (Moon moon : moons) {
					if (PlanetProgressionCompat.isReasearched(player, moon)) {
						bodyList.add(moon);
					}
				}
				for (RelayStation relayStation : relayStations) {
					if (PlanetProgressionCompat.isReasearched(player, relayStation)) {
						bodyList.add(relayStation);
					}
				}

			} else {
				bodyList.addAll(moons);
				bodyList.addAll(relayStations);
			}
		} else if (object instanceof SolarSystem) {
			List<Planet> planets = GalaxyRegistry.getPlanetsForSolarSystem((SolarSystem) object);
			if (Loader.isModLoaded("planetprogression")) {
				for (Planet planet : planets) {
					if (PlanetProgressionCompat.isReasearched(player, planet)) {
						bodyList.add(planet);
					}
				}
			} else {
				bodyList.addAll(planets);
			}
		}

		Collections.sort(bodyList);

		if (small_mode) {
			List<CelestialBody> doneList = Lists.newArrayList();
			int startPos = start;
			int getSize = size;
			if (startPos > bodyList.size())
				startPos = 0;
			if (getSize > bodyList.size())
				getSize = bodyList.size();

			for (int i = 0; i < getSize; i++)
				doneList.add(i, bodyList.get(i + startPos));

			return doneList;
		}
		return bodyList;
	}

}
