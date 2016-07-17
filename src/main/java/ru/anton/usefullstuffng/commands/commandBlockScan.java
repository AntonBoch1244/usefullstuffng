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
    public String getCommandUsage(ICommandSender sender) {
        return "blockscan <radius=[0;128]> <blockName1> [blockNameN] - blocks scanning in the player square radius by name of block";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        try {
            EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(sender);
            ChunkCoordinates pc = iPlayer.getPlayerCoordinates();
            int radius = Integer.valueOf(args[0]);
            if (radius > 128) {
                throw new Exception("RADIUS > MORE THAN CAN USE");
            }
            //TODO: Improve Calculation Algorithm of Block Detection
            // MoreBlocks = Less time | For some reason it works!
            for (int blockInt = 1; blockInt <= args.length-1; ++blockInt) {
                int blockCount = 0;
                try {
                    String blockName = Block.getBlockFromName(args[blockInt]).getUnlocalizedName();
                    for (int x = pc.posX - radius; x <= pc.posX + radius; ++x)
                        for (int y = 0; y <= iPlayer.getServerForPlayer().getHeight(); ++y)
                            for (int z = pc.posZ - radius; z <= pc.posZ + radius; ++z)
                                if (IBlockScan.checkBlock(sender, x, y, z, blockName))
                                    ++blockCount;
                    try {
                        sender.addChatMessage(new ChatComponentText(Block.getBlockFromName(args[blockInt]).getLocalizedName() + ": " + blockCount));
                    }
                    catch (Exception exc) {}
                }
                catch (Exception exc) {
                    sender.addChatMessage(new ChatComponentText("Block is not found in game! called as " + args[blockInt]));
                }
            }
        }
        catch (Exception exc) {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return (args.length >= 1 ? CommandBase.getListOfStringsFromIterableMatchingLastWord(args, Block.blockRegistry.getKeys()) : null);
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
