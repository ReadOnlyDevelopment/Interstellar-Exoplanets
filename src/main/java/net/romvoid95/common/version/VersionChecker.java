/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid95.common.version;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.romvoid95.api.versioning.Version;
import net.romvoid95.core.ExoInfo;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ExoInfo.MODID)
public final class VersionChecker {

	private VersionChecker() {}

	public static volatile boolean doneChecking      = false;
	public static volatile String  onlineVersion     = "";
	public static volatile String  fileId            = "";
	private static boolean         triedToWarnPlayer = false;
	public static volatile boolean startedDownload   = false;
	public static volatile boolean downloadedFile    = false;

	public static void init () {
		new ThreadVersionChecker();
	}

	@SubscribeEvent
	public static void onTick (ClientTickEvent event) {
		if (event.phase == Phase.END && Minecraft.getMinecraft().player != null && !triedToWarnPlayer && doneChecking) {
			if (!onlineVersion.isEmpty()) {
				EntityPlayer player = Minecraft.getMinecraft().player;
				Version      online = new Version(onlineVersion.split(":")[0]);
				Version      client = new Version(ExoInfo.FULL_VERSION);
				fileId = onlineVersion.split(":")[1];
				if (online.isGreaterThan(client)) {
					player.sendMessage(new TextComponentTranslation("exoplanets.versions.notify")
							.setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
					player.sendMessage(new TextComponentTranslation("exoplanets.versions.outdated0"));
					player.sendMessage(new TextComponentTranslation("exoplanets.versions.outdated1", client
							.toString()));
					player.sendMessage(new TextComponentTranslation("exoplanets.versions.outdated2", online
							.toString()));
					player.sendMessage(new TextComponentTranslation("exoplanets.versions.outdated0"));
					ITextComponent component = ITextComponent.Serializer
							.fromJsonLenient(I18n.translateToLocal("exoplanets.versions.updateMessage"));
					player.sendMessage(component);
				}
			}
			triedToWarnPlayer = true;
		}
	}
}
