package net.rom95.common.command;

import com.therandomlabs.curseapi.CurseException;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.rom95.common.version.DownloadUpdate;
import net.rom95.common.version.VersionChecker;

public class CommandDownloadUpdate extends CommandExo {

	@Override
	public String getName () {
		return "download-latest-exoplanets";
	}

	@Override
	public String getUsage (ICommandSender sender) {
		return "/download-latest-exoplanets <version>";
	}

	@Override
	public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
		return server.isSinglePlayer() || super.checkPermission(server, sender);
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 1)
			if (VersionChecker.downloadedFile)
				sender.sendMessage(new TextComponentTranslation("exoplanets.versions.downloadedAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			else if (VersionChecker.startedDownload)
				sender.sendMessage(new TextComponentTranslation("exoplanets.versions.downloadingAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			else
				try {
					new DownloadUpdate(args[0]);
				}
				catch (CurseException e) {
					e.printStackTrace();
				}

	}

}
