package net.rom.exoplanets.command;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.util.RegistryUtil;

public class CommandData extends CommandExo {
	
	enum SubCommand implements IStringSerializable {
		
		BLOCKS(RegistryUtil::listBlocks),
		ITEMS(RegistryUtil::listItems);
		
        private final Runnable command;

        SubCommand(Runnable command) {
            this.command = command;
        }
		
        void execute() {
            this.command.run();
        }

        @Override
        public String getName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
	}

	@Override
	public String getName() {
		return ExoInfo.MODID + "_data";
	}
	
    @Override
    public List<String> getAliases() {
        return ImmutableList.of("exo_data");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        StringBuilder subcommands = new StringBuilder();
        for (SubCommand sub : SubCommand.values()) {
            if (subcommands.length() > 0) subcommands.append("|");
            subcommands.append(sub.getName());
        }
        return String.format("%sUsage: /%s <%s>", TextFormatting.RED, getName(), subcommands.toString());
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(new TextComponentString(getUsage(sender)));
            return;
        }
        getSubCommand(args[0]).ifPresent(subCommand -> {
            subCommand.execute();
            String line = String.format("Printed %s list to log", subCommand.getName());
            sender.sendMessage(new TextComponentString(line));
        });
    }

    private Optional<SubCommand> getSubCommand(String arg) {
        for (SubCommand subCommand : SubCommand.values())
            if (subCommand.name().equalsIgnoreCase(arg))
                return Optional.of(subCommand);
        return Optional.empty();
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, Arrays.stream(SubCommand.values())
                    .map(SubCommand::getName)
                    .collect(ImmutableList.toImmutableList()));
        else
            return ImmutableList.of();
    }
}
