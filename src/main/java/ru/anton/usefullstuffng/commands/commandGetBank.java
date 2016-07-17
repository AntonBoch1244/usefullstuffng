package ru.anton.usefullstuffng.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;


public class commandGetBank implements ICommand {

    @Override
    public String getCommandName() {
        return "getbank";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "getbank - output chunkBank of player";
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList("gb", "bank");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(sender);
        ChatComponentText message = new ChatComponentText("Bank: " +
                String.valueOf(
                        sender.getEntityWorld().getChunkFromBlockCoords(
                                iPlayer.getPlayerCoordinates().posX,
                                iPlayer.getPlayerCoordinates().posZ
                        ).xPosition /16
                ) + ", " +
                String.valueOf(
                        sender.getEntityWorld().getChunkFromBlockCoords(
                                iPlayer.getPlayerCoordinates().posX,
                                iPlayer.getPlayerCoordinates().posZ
                        ).zPosition /16)
        );
        sender.addChatMessage(message);
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
}
