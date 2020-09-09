package net.romvoid95.common.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.romvoid95.common.version.DownloadUpdate;
import net.romvoid95.common.version.VersionChecker;

public class CommandDownloadUpdate extends CommandExo {

	@Override
	public String getName () {
		return "download-latest-exoplanets";
	}

	@Override
	public String getUsage (ICommandSender sender) {
		return "/download-latest-exoplanets";
	}

	@Override
	public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
		return server.isSinglePlayer() || super.checkPermission(server, sender);
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			if (VersionChecker.downloadedFile) {
				sender.sendMessage(new TextComponentTranslation("exoplanets.versions.downloadedAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			}
			else if (VersionChecker.startedDownload) {
				sender.sendMessage(new TextComponentTranslation("exoplanets.versions.downloadingAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			}
			else {
				new DownloadUpdate();
			}
		}
		else {

		}
	}
}
