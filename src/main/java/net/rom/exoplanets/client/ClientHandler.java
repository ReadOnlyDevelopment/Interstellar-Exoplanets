package net.rom.exoplanets.client;

import org.lwjgl.opengl.GL11;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.client.screen.EnumScreenAnchor;
import net.rom.exoplanets.internal.MCUtil;
import net.rom.exoplanets.util.RGB;

public class ClientHandler {
	
	public Minecraft mc = FMLClientHandler.instance().getClient();
	
		@SubscribeEvent(priority=EventPriority.LOW)
		@SideOnly(Side.CLIENT)
		public void onRenderTick(RenderTickEvent event)
		{
	        final Minecraft minecraft = FMLClientHandler.instance().getClient();        
	        final EntityPlayerSP player = minecraft.player;

	        if (event.phase == Phase.END)
	        {
	        	if (player != null)
	            {        		
	        		
	        		if(minecraft.inGameHasFocus && !minecraft.gameSettings.hideGUI && MCUtil.isDeobfuscated())
	        		{
	        			
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
	        			for(int i = 0; i < s.length; i++)
	        				minecraft.fontRenderer.drawStringWithShadow(s[i], 10, 28 + i*10, ColorUtil.to32BitColor(255, 255, 255, 255));
	        			GL11.glPopMatrix();

	        		}
	            }
	        }
		}
}
