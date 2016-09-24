package ru.anton.usefullstuffng.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import ru.anton.usefullstuffng.chunkloading.chunkManager;

import java.util.List;

/*
* Not Fully implemented yet
* */

public class commandChunkManage implements ICommand {
    @Override
    public String getCommandName() {
        return "chunk";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "chunk load <blockX> <blockZ> - to enforce load Chunk.\n" +
               "chunk unload <blockX> <blockZ> - to enforce unload Chunk.\n";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args[0].equals("load")) {
            chunkManager.requestChunkLoad(sender.getEntityWorld(), sender, Integer.valueOf(args[1]), Integer.valueOf(args[2]));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
