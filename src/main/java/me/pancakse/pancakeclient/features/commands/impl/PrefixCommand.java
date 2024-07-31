package me.pancakse.pancakeclient.features.commands.impl;

import me.pancakse.pancakeclient.PancakeClient;
import me.pancakse.pancakeclient.features.commands.Command;
import net.minecraft.util.Formatting;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(Formatting.GREEN + "Current prefix is " + PancakeClient.commandManager.getPrefix());
            return;
        }
        PancakeClient.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + Formatting.GRAY + commands[0]);
    }
}