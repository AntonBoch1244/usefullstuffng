package ru.anton.usefullstuffng.advenced;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;

public class IBlockScan {
    public static boolean data(ICommandSender sender, int x, int y, int z, String blockName) {
        try {
            if (sender.getEntityWorld().getBlock(x, y, z).getUnlocalizedName().equals(Block.getBlockFromName(blockName).getUnlocalizedName()))
                return true;
            else return false;
        }
        catch (Exception e){ return false; }
    }
}
