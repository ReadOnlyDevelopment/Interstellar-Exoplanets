package com.readonlydev.common.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.readonlydev.common.config.ConfigCore;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommandFindBiome extends CommandExo {
	
	public static BlockPos spiralOutwardsLookingForBiome(ICommandSender sender, World world, Biome biomeToFind, double startX, double startZ, int timeout) {
		double a = 16 / Math.sqrt(Math.PI);
		double b = 2 * Math.sqrt(Math.PI);
		double x;
		double z;
		double dist = 0;
		int n;
		long start = System.currentTimeMillis();
		BlockPos.PooledMutableBlockPos pos = BlockPos.PooledMutableBlockPos.retain();
		int previous = 0;
		int i = 0;
		for (n = 0; dist < Integer.MAX_VALUE; ++n) {
			if ((System.currentTimeMillis() - start) > timeout) {
				return null;
			}
			double rootN = Math.sqrt(n);
			dist = a * rootN;
			x = startX + (dist * Math.sin(b * rootN));
			z = startZ + (dist * Math.cos(b * rootN));
			pos.setPos(x, 0, z);
			if (sender instanceof EntityPlayer) {
				if (previous == 3)
					previous = 0;
				String s = (previous == 0 ? "." : previous == 1 ? ".." : "...");
				((EntityPlayer) sender).sendStatusMessage(new TextComponentString("Scanning" + s), true);
				if (i == 1501) {
					previous++;
					i = 0;
				}
				i++;
			}
			if (world.getBiome(pos).equals(biomeToFind)) {
				pos.release();
				if (sender instanceof EntityPlayer)
					((EntityPlayer) sender).sendStatusMessage(new TextComponentString("Found Biome"), true);
				return new BlockPos((int) x, 0, (int) z);
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return "locatebiome";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/locatebiome <biome>";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		if(args.length==1) {
			List<String> strings = new ArrayList<>();
			for (Biome b : ForgeRegistries.BIOMES.getValues()) {
				String s = b.getRegistryName().toString();
				if (s.toLowerCase().contains(args[0].toLowerCase()))
					strings.add(s);
			}

			return strings;
		}
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		Biome biome = null;
		if (args.length == 0) {
			sender.sendMessage(new TextComponentString("No biome specified"));
			return;
		}
		for (Biome b : ForgeRegistries.BIOMES.getValues()) {
			String name = b.getRegistryName().toString().replaceAll(" ", "_").toLowerCase();
			if (args[0].equalsIgnoreCase(name)) {
				biome = b;
			}
		}
		if (biome == null) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' cannot be found!"));
			return;
		}
		long start = System.currentTimeMillis();
		final Biome finalBiome = biome;
		new Thread(() -> {
			BlockPos pos = spiralOutwardsLookingForBiome(sender, sender.getEntityWorld(), finalBiome, sender.getPosition().getX(), sender.getPosition().getZ(), ConfigCore.biomeCommandTimeout);
			if (pos == null) {
				server.addScheduledTask(() -> sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' could not be found after " + TextFormatting.GRAY + ConfigCore.biomeCommandTimeout + "ms" + TextFormatting.RED + ".")));
				return;
			}
			if (sender instanceof EntityPlayerMP) {
				server.addScheduledTask(() -> {
					EntityPlayerMP playerMP = (EntityPlayerMP) sender;
					playerMP.connection.setPlayerLocation(pos.getX(), 150, pos.getZ(), 0, 0);
					sender.sendMessage(new TextComponentString(TextFormatting.WHITE + "Found '" + finalBiome.getRegistryName().toString() + "' Biome! " + TextFormatting.GRAY + "(" + (System.currentTimeMillis() - start) + "ms)"));
				});
				return;
			}
			server.addScheduledTask(() -> sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! An unknown error occurred.")));
		}, "Biome Finder - Traverse").start();
	}
}
