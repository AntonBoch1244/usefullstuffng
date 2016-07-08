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

    private EntityPlayerMP iPlayer;

    @Override
    public String getCommandName() {
        return "getchunk";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "getchunk - output chunk of player";
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList(new String[] {
            "gc",
            "chunk"
        });
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        iPlayer = CommandBase.getCommandSenderAsPlayer(p_71515_1_);
        ChatComponentText message = new ChatComponentText("Chunk: " + String.valueOf(p_71515_1_.getEntityWorld().getChunkFromBlockCoords(iPlayer.getPlayerCoordinates().posX, iPlayer.getPlayerCoordinates().posZ).xPosition) + ", " + String.valueOf(p_71515_1_.getEntityWorld().getChunkFromBlockCoords(iPlayer.getPlayerCoordinates().posX, iPlayer.getPlayerCoordinates().posZ).zPosition));
        p_71515_1_.addChatMessage(message);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(@Nullable Object o) {
        return 0;
    }
}
