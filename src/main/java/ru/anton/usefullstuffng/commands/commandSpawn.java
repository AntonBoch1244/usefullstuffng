package ru.anton.usefullstuffng.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class commandSpawn implements ICommand {

    private EntityPlayerMP iPlayer;

    @Override public String getCommandName() {
        return "spawn";
    }

    @Override public String getCommandUsage(ICommandSender p_71518_1_) {
        return "spawn - teleport player to spawnpoint";
    }

    @Override public List getCommandAliases() {
        return Arrays.asList(new String[] {});
    }

    @Override public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        ChunkCoordinates sp = p_71515_1_.getEntityWorld().getSpawnPoint();
        iPlayer = CommandBase.getCommandSenderAsPlayer(p_71515_1_);
        if (iPlayer.dimension == 1) {
            iPlayer.addChatMessage(new ChatComponentText("You are in The End dimension and server can\'t extract you from them!"));
        }
        else {
            if (iPlayer.dimension != 0) {
                iPlayer.playerNetServerHandler.playerEntity.travelToDimension(0);
            }
            int gsy = p_71515_1_.getEntityWorld().getHeight();
            while (p_71515_1_.getEntityWorld().getBlock(sp.posX, gsy, sp.posZ) == Blocks.air) {
                --gsy;
            }
            gsy++;
            gsy++;
            iPlayer.setPositionAndUpdate(sp.posX+0.5d, gsy, sp.posZ+0.5d);
        }
    }

    @Override public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override public int compareTo(@Nullable Object o) {
        return 0;
    }
}
