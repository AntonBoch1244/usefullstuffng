package ru.anton.usefullstuffng.advenced;

import net.minecraft.command.ICommandSender;

/**
 * IBlockScan implement detection functionality of scanning blocks command
 */
public class IBlockScan {
    /**
     * This function is implementation of getting information of block in the world
     * @param sender                is you
     * @param x                     your x position
     * @param y                     your y position
     * @param z                     your z position
     * @param blockUnlocalizedName  searching block
     * @return                      is equals or false if detected error
     */
    static boolean checkBlock(ICommandSender sender, int x, int y, int z, String blockUnlocalizedName) {
        try {
            return sender.getEntityWorld().getBlock(x, y, z).getUnlocalizedName().equals(blockUnlocalizedName);
        }
        catch (Exception e){ return false; }
    }
}
