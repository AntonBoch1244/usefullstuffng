package ru.anton.usefullstuffng.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;

import javax.annotation.Nullable;
import java.util.List;

public class commandSpawn implements ICommand {

    @Override public String getCommandName() {
        return "spawn";
    }

    @Override public String getCommandUsage(ICommandSender sender) {
        return "spawn - teleport player to spawn point at 0 dimension";
    }

    @Override public List getCommandAliases() {
        return null;
    }

    @Override public void processCommand(ICommandSender sender, String[] args) {
        ChunkCoordinates sp = sender.getEntityWorld().getSpawnPoint();
        EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(sender);
        if (iPlayer.dimension == 1) {
            iPlayer.addChatMessage(new ChatComponentText(
                    "You are in The End dimension and server can\'t extract you from them!"));
        }
        else {
            if (iPlayer.dimension != 0) {
                iPlayer.playerNetServerHandler.playerEntity.travelToDimension(0);
            }
            int gsy = sender.getEntityWorld().getHeight();
            while (sender.getEntityWorld().getBlock(sp.posX, gsy, sp.posZ) == Blocks.air) {
                --gsy;
            }
            gsy++;
            gsy++;
            iPlayer.setPositionAndUpdate(sp.posX+0.5d, gsy, sp.posZ+0.5d);
        }
    }

    @Override public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override public boolean isUsernameIndex(String[] usernameList, int index) {
        return false;
    }

    @Override public int compareTo(@Nullable Object o) {
        return 0;
    }
}
