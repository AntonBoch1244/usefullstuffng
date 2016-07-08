package ru.anton.usefullstuffng.commands;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import ru.anton.usefullstuffng.advenced.IBlockScan;

import javax.annotation.Nullable;
import java.util.List;

public class commandBlockScan implements ICommand {
    @Override
    public String getCommandName() {
        return "blockscan";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "blockscan <radius=[0;128]> <blockName1> [blockNameN] - blocks scanning in the player radius by name";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        try {
            EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(p_71515_1_);
            ChunkCoordinates pc = iPlayer.getPlayerCoordinates();
            int radius = Integer.valueOf(p_71515_2_[0]);
            if (radius > 128) {
                throw new Exception("RADIUS > MORE THAN CAN USE");
            }
            for (int blockInt = 1; blockInt <= p_71515_2_.length-1; ++blockInt) {
                int blockCount = 0;
                for (int x = pc.posX - radius; x <= pc.posX + radius; ++x)
                    for (int y = 0; y <= iPlayer.getServerForPlayer().getHeight(); ++y)
                        for (int z = pc.posZ - radius; z <= pc.posZ + radius; ++z)
                                if (IBlockScan.data(p_71515_1_, x, y, z, p_71515_2_[blockInt]))
                                    ++blockCount;
                try {
                    p_71515_1_.addChatMessage(new ChatComponentText(Block.getBlockFromName(p_71515_2_[blockInt]).getLocalizedName() + ": " + blockCount));
                }
                catch (Exception exc) {
                    p_71515_1_.addChatMessage(new ChatComponentText("Block is not found in game!"));
                }
            }
        }
        catch (Exception exc) {
            p_71515_1_.addChatMessage(new ChatComponentText(getCommandUsage(p_71515_1_)));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return (p_71516_2_.length >= 1 ? CommandBase.getListOfStringsFromIterableMatchingLastWord(p_71516_2_, Block.blockRegistry.getKeys()) : null);
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
