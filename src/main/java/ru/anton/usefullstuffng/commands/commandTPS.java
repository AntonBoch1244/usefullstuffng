package ru.anton.usefullstuffng.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class commandTPS implements ICommand {
    @Override
    public String getCommandName() {
        return "tps";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "tps - check tps overall server";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        double tt = 0;
        for (Long i : (this.getServer().tickTimeArray)) {
            tt = tt+i;
        }
        double tps = Math.min(1000.0/(tt * 1.0E-6D), 20);
        sender.addChatMessage(new ChatComponentText("TPS: " + new DecimalFormat("########0.000").format(tps)));
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
    public boolean isUsernameIndex(String[] usernameList, int index) {
        return false;
    }

    @Override
    public int compareTo(@Nullable Object o) {
        return 0;
    }

    MinecraftServer getServer() {
        return MinecraftServer.getServer();
    }
}
