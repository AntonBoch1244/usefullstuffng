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

/**
 * Command blockScan is implementation of ICommand
 */

public class commandBlockScan implements ICommand {
    /**
     * This function return command name
     * @return blockscan
     */
    @Override
    public String getCommandName() {
        return "blockscan";
    }

    /**
     * This function return usage of command
     * @param sender    you
     * @return          blockscan &lt;radius=[0;128]&gt; &lt;blockName1&gt; [blockNameN] - blocks scanning in the player radius by name
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "blockscan <radius=[0;128]> <blockName1> [blockNameN] - blocks scanning in the player radius by name";
    }

    /**
     * This function return list of aliases
     * @return null
     * <b>Because this command not required aliases</b>
     */
    @Override
    public List getCommandAliases() {
        return null;
    }

    /**
     * This method trying calculate number of named by sender blocks[1;Inf) in radius args[0] of action
     * Searching algorithm is (need improve)
     * <ol>
     *     <li>Get Player as entity for start searching
     *     <li>Get Player coordinates in the world
     *     <li>Get Radius of searching
     *     <li>If error in syntax of command or radius is big then 128 then returned usage of command <br>
     *     In cycle: <ol>
     *          <li>Get name or id of block in block names array
     *          <li>Get x coordinate of start searching
     *          <li>Get y coordinate of start searching
     *          <li>Get z coordinate of start searching
     *          <li>Checking block
     *          <li>If block unlocalized name is equals unlocalized name of block parameter then add that block to counter else not
     *          <li>Increase z and try again to moment z is go out of radius
     *          <li>Increase y and try again to moment y is go out of maximal height of world
     *          <li>Increase x and try again to moment x is go out of radius
     *          <li>If block name or id is not found in game then return no block message else send to sender localized name of counted blocks and count
     *          <li>Get next block name or id in array and try again to moment end of block names array
     *     </ol>
     * </ol>
     * @param sender    you
     * @param args      radius and block list
     */
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
                for (int x = pc.posX - radius; x <= pc.posX + radius; ++x)
                    for (int y = 0; y <= iPlayer.getServerForPlayer().getHeight(); ++y)
                        for (int z = pc.posZ - radius; z <= pc.posZ + radius; ++z)
                                if (IBlockScan.checkBlock(sender, x, y, z, args[blockInt]))
                                    ++blockCount;
                try {
                    sender.addChatMessage(new ChatComponentText(Block.getBlockFromName(args[blockInt]).getLocalizedName() + ": " + blockCount));
                }
                catch (Exception exc) {
                    sender.addChatMessage(new ChatComponentText("Block is not found in game!"));
                }
            }
        }
        catch (Exception exc) {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
        }
    }

    /**
     * This function need for get can or not can use command on server
     * @param sender    you
     * @return          true
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    /**
     * This function need for tab-completion of blocks
     * @param sender    you
     * @param args      blocks array
     * @return          list of blocks array
     */
    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return (args.length >= 1 ? CommandBase.getListOfStringsFromIterableMatchingLastWord(args, Block.blockRegistry.getKeys()) : null);
    }

    /**
     * This function need for indexing players
     * @param usernameList  username list in game
     * @param index     index
     * @return          false
     */
    @Override
    public boolean isUsernameIndex(String[] usernameList, int index) {
        return false;
    }

    /**
     * This function is comparing to object
     * @param o abstract object
     * @return  0
     */
    @Override
    public int compareTo(@Nullable Object o) {
        return 0;
    }
}
