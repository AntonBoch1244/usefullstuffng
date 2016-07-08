package ru.anton.usefullstuffng.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class commandGetChunk implements ICommand {

    @Override
    public String getCommandName() {
        return "getchunk";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "getchunk - output chunk of player";
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList("gc", "chunk");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(sender);
        ChatComponentText message = new ChatComponentText("Chunk: " +
                String.valueOf(
                        sender.getEntityWorld().getChunkFromBlockCoords(
                                iPlayer.getPlayerCoordinates().posX,
                                iPlayer.getPlayerCoordinates().posZ
                        ).xPosition
                ) + ", " +
                String.valueOf(
                        sender.getEntityWorld().getChunkFromBlockCoords(
                                iPlayer.getPlayerCoordinates().posX,
                                iPlayer.getPlayerCoordinates().posZ
                        ).zPosition
                ));
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
