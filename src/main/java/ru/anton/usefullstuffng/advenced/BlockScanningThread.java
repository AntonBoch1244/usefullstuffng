package ru.anton.usefullstuffng.advenced;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import ru.anton.usefullstuffng.commands.commandBlockScan;

public class BlockScanningThread {

    private ICommandSender sender;
    private String[] args;
    public void setAllArgs(ICommandSender field_0, String[] field_1) {
        this.sender = field_0;
        this.args = field_1;
    }

    public Runnable scanThread = new Runnable() {
        public void run() {
            commandBlockScan command = new commandBlockScan();
            try {
                if (args.length==0) throw new IndexOutOfBoundsException("NO RADIUS AND BLOCKS");
                EntityPlayerMP iPlayer = CommandBase.getCommandSenderAsPlayer(sender);
                ChunkCoordinates pc = iPlayer.getCommandSenderPosition();
                int radius = Integer.valueOf(args[0]);
                if (radius < 0) {
                    radius = radius*(-1);
                }
                if (args.length==1) throw new IndexOutOfBoundsException("NO BLOCKS");
                // TODO: Improve Calculation Algorithm of Block Detection
                // MoreBlocks = Less time | For some reason it works!
                for (int blockInt = 1; blockInt <= args.length-1; ++blockInt) {
                    int blockCount = 0;
                    try {
                        String blockName = Block.getBlockFromName(args[blockInt]).getUnlocalizedName();
                        for (int x = pc.posX - radius; x <= pc.posX + radius; ++x)
                            for (int y = 0; y <= iPlayer.getServerForPlayer().getHeight(); ++y)
                                for (int z = pc.posZ - radius; z <= pc.posZ + radius; ++z)
                                    if (IBlockScan.checkBlock(sender, x, y, z, blockName)) {
                                        ++blockCount;
                                        Thread.sleep(0, 20);
                                    }
                        sender.addChatMessage(new ChatComponentText(Block.getBlockFromName(args[blockInt]).getLocalizedName() + ": " + blockCount));
                    } catch (InterruptedException e) {
                        e.getCause().printStackTrace();
                    }
                    catch (NullPointerException exc) {
                        sender.addChatMessage(new ChatComponentText("Block called as " + args[blockInt] + " is not found in game!" ));
                    }
                }
            }
            catch (IndexOutOfBoundsException e) {
                sender.addChatMessage(new ChatComponentText(command.getCommandUsage(sender)));
            }
        }
    };
}
