package ru.anton.usefullstuffng.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class commandHardstop implements ICommand {

    @Override
    public String getCommandName() {
        return "hardstop";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "hardstop - is kill-switch for server";
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList("hs", "killserver");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        new FMLCommonHandler().exitJava(0, true);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender.getCommandSenderName().equals("Server");
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] usernameList, int index) {
        return false;
    }

    @Override
    public int compareTo(@Nullable Object o) {
        return 0;
    }
}
